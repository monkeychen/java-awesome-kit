package com.simiam.awekit.security.shiro;

import com.simiam.awekit.security.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: PasswordRealm</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2020</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2020/1/26 4:12 下午</p>
 */
public class PasswordRealm extends ShiroRealm {
    private static final Logger logger = LoggerFactory.getLogger(PasswordRealm.class);

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        User user = securityService.getUserByName(userName);
        logger.debug("Do password authenticate for user[{}]", user);
        if (user == null) {
            return null;
        }

        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
