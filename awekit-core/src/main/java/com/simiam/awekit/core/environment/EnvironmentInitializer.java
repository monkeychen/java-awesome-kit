package com.simiam.awekit.core.environment;

import com.simiam.awekit.Constants;
import com.simiam.awekit.core.event.PropertiesLoadedEvent;
import com.simiam.awekit.core.eventbus.EventBusFactory;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.SmartLifecycle;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <br/>
 * Author:ChenZhian <br/>
 * Create at: 2017/5/31 11:56.
 */
@Component
public class EnvironmentInitializer implements SmartLifecycle, InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(EnvironmentInitializer.class);

    private PathMatchingResourcePatternResolver resolver;

    @Value("${awekit.debug-mode:false}")
    private boolean debugMode = false;

    private ScheduledExecutorService scheduledExecutorService;

    private String[] profileLocations = new String[]{
            ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "/env.properties",
            ResourceUtils.FILE_URL_PREFIX + "./env.properties"
    };

    private boolean isRunning = false;

    @Override
    public void afterPropertiesSet() throws Exception {
        resolver = new PathMatchingResourcePatternResolver();
        ThreadFactoryBuilder factoryBuilder = new ThreadFactoryBuilder();
        ThreadFactory threadFactory = factoryBuilder.setNameFormat("PropertiesScanThreadPool-%d").build();
        scheduledExecutorService = Executors.newScheduledThreadPool(2, threadFactory);
    }

    private void startProfileScanTask() {
        long initialDelay = 60000L;
        long period = 1000 * Long.parseLong(System.getProperty(Constants.ENV_PROFILE_SCAN_INTERVAL, "300"));

        // 启动配置文件定时扫描任务
        scheduledExecutorService.scheduleAtFixedRate(() -> {

            parseResources(profileLocations);

            // 通知关注该业务的监听器
            publishProfileLoadedEvent();

        }, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    private void parseResources(String... locations) {
        Assert.notEmpty(locations, "Not exist any element in locations array!");
        List<Resource> resourceList = Lists.newArrayList();
        for (String location : locations) {
            try {
                Resource[] resources = resolver.getResources(location);
                if (resources.length == 0) {
                    continue;
                }
                for (Resource resource : resources) {
                    String url = resource.getURL().toString();
                    if (!resource.exists()) {
                        logger.warn("The resource[{}] does not exist!", url);
                        continue;
                    }
                    logger.info("Success to load resource:[{}]!", url);
                    resourceList.add(resource);
                }
            } catch (IOException e) {
                logger.error("Fail to getResources[{}]!", location, e);
            }
        }
        parseResource(resourceList);
    }

    private void parseResource(Collection<Resource> resources) {
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
                parseResource(resource);
            } catch (Exception e) {
                logger.error("Fail to load properties resource[{}] !!!", urlPath);
            }
        }
        for (Resource fileResource : fileResources) {
            String urlPath = null;
            try {
                urlPath = fileResource.getURL().toString();
                if (debugMode && urlPath.contains("target/classes")) {
                    logger.info("Ignore properties resource[{}].", urlPath);
                    continue;
                }
                parseResource(fileResource);
            } catch (Exception e) {
                logger.error("Fail to load properties resource[{}] !!!", urlPath);
            }
        }
    }

    private void parseResource(Resource resource) {
        String urlPath = null;
        try {
            urlPath = resource.getURL().toString();
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            putAllToSystemEnv(properties);
            logger.info("Success to load properties resource[{}] !!!", urlPath);
        } catch (Exception e) {
            logger.error("Fail to load properties resource[{}] !!!", urlPath);
        }
    }

    private void putAllToSystemEnv(Properties properties) {
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            Object keyObj = entry.getKey();
            Object valObj = entry.getValue();
            if (keyObj == null || valObj == null) {
                logger.warn("keyObj[{}] is null or value[{}] is null", keyObj, valObj);
                continue;
            }
            String key = keyObj.toString();
            String value = valObj.toString();
            logger.info("Put into system environment:{} = {}", key, value);
            System.setProperty(key, value);
        }
    }

    private void publishProfileLoadedEvent() {
        // 基于事件总线机制对外发送配置信息加载成功事件
        EventBusFactory.getInstance().post(new PropertiesLoadedEvent());
    }

    @Override
    public synchronized void start() {
        if (isRunning) {
            logger.warn("EnvironmentInitializer has been startup, so it's not necessary to startup again.");
            return;
        }
        parseResources(profileLocations);
        startProfileScanTask();
        isRunning = true;
        logger.info("Success to startup the EnvironmentInitializer!");
    }

    @Override
    public void stop() {
        isRunning = false;
        scheduledExecutorService.shutdown();
        logger.info("Success to shutdown the EnvironmentInitializer!");
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        this.stop();
        callback.run();
    }

    @Override
    public int getPhase() {
        return 1;
    }
}
