package com.simiam.awekit.core.environment;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.turbo.TurboFilter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: PlatformEnvironmentPostProcessor</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/5/13 14:50</p>
 */
public class PlatformEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(PlatformEnvironmentPostProcessor.class);

    private boolean debugMode = false;

    private final YamlPropertySourceLoader yamlLoader = new YamlPropertySourceLoader();

    private final PropertiesPropertySourceLoader propertiesLoader = new PropertiesPropertySourceLoader();

    private PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    private String[] profileLocations = new String[]{
            ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "/env.properties",
            ResourceUtils.FILE_URL_PREFIX + "./env.properties",
            ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "/platform.yml",
            ResourceUtils.FILE_URL_PREFIX + "./platform.yml"
    };

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        // 解决环境准备阶段日志无法打印问题
        LoggerContext factory = (LoggerContext) StaticLoggerBinder.getSingleton().getLoggerFactory();
        List<TurboFilter> turboFilters = factory.getTurboFilterList();
        List<TurboFilter> tmpTurboFilters = Lists.newArrayList(factory.getTurboFilterList());
        turboFilters.clear();

        Assert.notEmpty(profileLocations, "Not exist any element in locations array!");
        debugMode = BooleanUtils.toBoolean(environment.getProperty("awekit.debug-mode"));
        List<Resource> resourceList = Lists.newArrayList();
        for (String location : profileLocations) {
            try {
                Resource[] resources = resolver.getResources(location);
                if (resources.length == 0) {
                    continue;
                }
                for (Resource resource : resources) {
                    String url = resource.getURL().toString();
                    if (!resource.exists()) {
                        logger.warn("The resource[" + url + "] does not exist!");
                        continue;
                    }
                    logger.info("Success to load resource:[" + url + "]!");
                    resourceList.add(resource);
                }
            } catch (IOException e) {
                logger.error("Fail to getResources[" + location + "]!", e);
            }
        }
        parseResource(environment, resourceList);

        factory.getTurboFilterList().addAll(tmpTurboFilters);
    }

    private void parseResource(ConfigurableEnvironment environment, Collection<Resource> resources) {
        List<Resource> fileResources = Lists.newArrayList();
        for (Resource resource : resources) {
            String urlPath = null;
            try {
                URL url = resource.getURL();
                urlPath = url.toString();
                if (ResourceUtils.isFileURL(url)) {
                    fileResources.add(resource);
                    continue;
                }
                parseResource(environment, resource);
            } catch (Exception e) {
                logger.error("Fail to load resource[" + urlPath + "] !!!", e);
            }
        }
        for (Resource fileResource : fileResources) {
            String urlPath = null;
            try {
                urlPath = fileResource.getURL().toString();
                if (debugMode && urlPath.contains("target/classes")) {
                    logger.info("Ignore resource[" + urlPath + "].");
                    continue;
                }
                parseResource(environment, fileResource);
            } catch (Exception e) {
                logger.error("Fail to load resource[" + urlPath + "] !!!", e);
            }
        }
    }

    private void parseResource(ConfigurableEnvironment environment, Resource resource) {
        String urlPath = null;
        try {
            PropertySource<?> propertySource = null;
            urlPath = resource.getURL().toString();
            if (!resource.exists()) {
                logger.warn("The resource[" + urlPath + "] does not exist!");
                return;
            }
            if (urlPath.endsWith("properties")) {
                propertySource = propertiesLoader.load(urlPath, resource).get(0);
            } else if (urlPath.endsWith("yml")) {
                propertySource = yamlLoader.load(urlPath, resource).get(0);
            }
            if (propertySource != null) {
                environment.getPropertySources().addLast(propertySource);
                putAllToSystemEnv(propertySource);
            }
            logger.info("Success to parse resource[" + urlPath + "] !!!");
        } catch (Exception e) {
            logger.error("Fail to parse resource[" + urlPath + "] !!!", e);
        }
    }

    private void putAllToSystemEnv(PropertySource<?> propertySource) {
        if (!(propertySource instanceof MapPropertySource)) {
            logger.warn("PropertySource[" + propertySource + " ] is not an instance of MapPropertySource!");
            return;
        }
        MapPropertySource mapPropertySource = (MapPropertySource) propertySource;
        for (Map.Entry<String, Object> entry : mapPropertySource.getSource().entrySet()) {
            Object keyObj = entry.getKey();
            Object valObj = entry.getValue();
            if (keyObj == null || valObj == null) {
                logger.warn("keyObj[" + keyObj + "] is null or value[" + valObj + "] is null");
                continue;
            }
            String key = keyObj.toString();
            String value = valObj.toString();
            logger.info("Put into system environment:" + key + " = " + value);
            System.setProperty(key, value);
        }
    }

    @Override
    public int getOrder() {
        return ConfigFileApplicationListener.DEFAULT_ORDER + 1;
    }
}
