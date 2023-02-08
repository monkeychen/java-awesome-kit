package com.simiam.awekit.security.shiro;

import com.simiam.awekit.Awekit;
import com.simiam.awekit.security.SecurityConstants;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.servlet.Filter;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: ShiroConfiguration</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/12/17 9:47 上午</p>
 */
@Configuration
public class ShiroConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    public static final String SECURITY_TOKEN = "SHIRO_TOKEN";

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filterMap = Maps.newHashMap();
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/api/logoutSuccess");
        filterMap.put("logout", logoutFilter);
        filterFactoryBean.setFilters(filterMap);

        Map<String, String> filterChainDefinitionMap = Maps.newLinkedHashMap();
        filterChainDefinitionMap.put("/api/login", "anon");
        filterChainDefinitionMap.put("/api/ssoTokenUrl", "anon");
        filterChainDefinitionMap.put("/api/ssologin", "anon");
        filterChainDefinitionMap.put("/api/portallogin", "anon");
        filterChainDefinitionMap.put("/api/user/register", "anon");
        filterChainDefinitionMap.put("/upload/images/**", "anon");
        filterChainDefinitionMap.put("/api/download/**", "anon");
        filterChainDefinitionMap.put("/api/debug/**", "anon");
        filterChainDefinitionMap.put("/ws/**", "anon");
        filterChainDefinitionMap.put("/ws-broker/**", "anon");
        filterChainDefinitionMap.put("/ssomock/**", "anon");
        filterChainDefinitionMap.put("/monitor/**", "anon");
        filterChainDefinitionMap.put("/unitest/**", "anon");
        filterChainDefinitionMap.put("/api/logoutSuccess", "anon");

        String patterns = Awekit.getEnvProperty(SecurityConstants.SHIRO_ANON_URL_PATTERN, "");
        List<String> noNeedAuthUrlList = Splitter.on("|").trimResults().splitToList(patterns);
        if (!CollectionUtils.isEmpty(noNeedAuthUrlList)) {
            for (String pattern : noNeedAuthUrlList) {
                if (filterChainDefinitionMap.containsKey(pattern)) {
                    logger.warn("Found the exist pattern[{}], so ignore it!", pattern);
                    continue;
                }
                filterChainDefinitionMap.put(pattern, "anon");
            }
        }

        filterChainDefinitionMap.put("/api/logout", "logout");
        filterChainDefinitionMap.put("/**", "authc");

        filterFactoryBean.setLoginUrl("/api/unauth");
        filterFactoryBean.setUnauthorizedUrl("/api/unauth");
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        logger.info("Success to create ShiroFilterFactoryBean instance.");
        return filterFactoryBean;
    }

    @Bean
    public MemoryConstrainedCacheManager cacheManager() {
        MemoryConstrainedCacheManager cacheManager = new MemoryConstrainedCacheManager();
        logger.info("Success to create MemoryConstrainedCacheManager instance.");
        return cacheManager;
    }

    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列的次数，比如散列一次相当于md5(""), 散列两次相当于md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(Awekit.getEnvProperty("security.hash.iteration.count", 1));
        return hashedCredentialsMatcher;
    }

    @Bean
    public PasswordRealm passwordRealm() {
        PasswordRealm passwordRealm = new PasswordRealm();
        passwordRealm.setCredentialsMatcher(simpleCredentialsMatcher());
        return passwordRealm;
    }

    public SimpleCredentialsMatcher simpleCredentialsMatcher() {
        return new SimpleCredentialsMatcher();
    }

    @Bean
    public SimpleCookie sessionIdCookie() {
        SimpleCookie simpleCookie = new SimpleCookie(Awekit.getEnvProperty("security.token", "jzxn-backend-token"));
        // 防止xss攻击，窃取cookie内容
        simpleCookie.setHttpOnly(true);
        return simpleCookie;
    }

    @Bean
    public SessionsSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        List<Realm> realmList = Lists.newArrayList();
        realmList.add(shiroRealm());
        realmList.add(passwordRealm());
        securityManager.setRealms(realmList);
        securityManager.setCacheManager(cacheManager());
        securityManager.setSessionManager(sessionManager());
        logger.info("Success to create DefaultWebSecurityManager instance.");
        return securityManager;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new ShiroSessionManager();
        // 会话超时时间，单位：毫秒
        sessionManager.setGlobalSessionTimeout(24 * 60 * 60 * 1000);
        // 定时清理失效会话, 清理用户直接关闭浏览器造成的孤立会话
        sessionManager.setSessionValidationInterval(24 * 60 * 60 * 1000);
        // 是否开启定时清理失效会话
        sessionManager.setSessionValidationSchedulerEnabled(true);
        // 是否允许将sessionId 放到 cookie 中
        sessionManager.setSessionIdCookieEnabled(true);
        // 指定sessionid
        sessionManager.setSessionIdCookie(sessionIdCookie());
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setDeleteInvalidSessions(true);
        return sessionManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
