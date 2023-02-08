package com.simiam.awekit.security.util;

import com.simiam.awekit.BaseConstants;
import com.simiam.awekit.security.entity.Role;
import com.simiam.awekit.security.entity.User;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <p>Title: SecurityUtils</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/12/17 9:28 上午</p>
 */
public abstract class SecurityUtils extends org.apache.shiro.SecurityUtils {
    private static final Logger logger = LoggerFactory.getLogger(SecurityUtils.class);

    public static String createSalt() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String encryptWithSalt(String password, String salt, int hashIterations) {
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        SimpleHash simpleHash = new Md5Hash(password, credentialsSalt, hashIterations);
        return simpleHash.toString();
    }

    public static String md5(String plainText) {
        return DigestUtils.md5DigestAsHex(plainText.getBytes());
    }

    public static Session getSession() {
        Subject subject = org.apache.shiro.SecurityUtils.getSubject();
        if (subject == null) {
            return null;
        }
        return subject.getSession();
    }

    public static User getAuthenticatedUserFromSession() {
        Subject subject = org.apache.shiro.SecurityUtils.getSubject();
        if (subject == null) {
            logger.info("There is not any login user in the session!");
            return null;
        }
        Object object = subject.getPrincipal();
        if (object == null || !subject.isAuthenticated()) {
            return null;
        }
        return (User) object;
    }

    public static void putUserInfoToResponseData(User user, Map<String, Object> dataMap) {
        Map<String, Object> userMap = Maps.newHashMap();
        List<Role> roleList = user.getRoleList();
        if (roleList != null) {
            userMap.put("roles", roleList.stream().map(role -> {
                assert role != null;
                return role.getName();
            }).collect(Collectors.toList()));
        } else {
            userMap.put("roles", Lists.newArrayList());
        }
        userMap.put("introduction", user.getIntroduction());
        userMap.put("avatar", user.getAvatar());
        userMap.put("name", user.getUsername());
        userMap.put("realName", user.getRealName());
        userMap.put("department", user.getDepartment());
        dataMap.put(BaseConstants.CURRENT_USER, userMap);
    }

    public static Session getSession(String sessionId, ServletRequest request, ServletResponse response) {
        SessionKey sessionKey = new WebSessionKey(sessionId, request, response);
        return org.apache.shiro.SecurityUtils.getSecurityManager().getSession(sessionKey);
    }

    public static boolean isAuthenticated(String sessionId, ServletRequest request, ServletResponse response) {
        Session session = getSession(sessionId, request, response);
        if (session == null) {
            return false;
        }
        Object obj = session.getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY);
        return obj != null && (Boolean) obj;
    }

    public static boolean isAuthenticated() {
        Subject subject = org.apache.shiro.SecurityUtils.getSubject();
        if (subject == null) {
            return false;
        }
        return subject.isAuthenticated();
    }

    public static User getCurrentUser() {
        return getAuthenticatedUserFromSession();
    }

    public static String getCurrentSessionId() {
        if (getSession() != null) {
            return getSession().getId().toString();
        }
        return null;
    }

    public static String getRequestToken(HttpServletRequest request) {
        String sessionId = request.getHeader(BaseConstants.AUTHORIZATION);
        if (!Strings.isNullOrEmpty(sessionId)) {
            return sessionId;
        }

        return request.getParameter("token");
    }

    public static String generateTicketId() {
        return generateId("TICKET");
    }

    public static String generateId(String moduleName) {
        return "SIMIAM-" + moduleName + "-" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
    }

    public static <E> E setProperty(E entity, String fieldName, Object fieldValue) {
        try {
            BeanUtils.setProperty(entity, fieldName, fieldValue);
        } catch (Exception e) {
            logger.error("Fail to set ticket property[name = {}, val = {}]", fieldName, fieldValue, e);
        }
        return entity;
    }
}
