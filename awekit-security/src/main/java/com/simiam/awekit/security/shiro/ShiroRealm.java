package com.simiam.awekit.security.shiro;

import com.simiam.awekit.security.entity.Permission;
import com.simiam.awekit.security.entity.Role;
import com.simiam.awekit.security.entity.User;
import com.simiam.awekit.security.service.SecurityService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>Title: ShiroRealm</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/12/18 3:36 下午</p>
 */
public class ShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    protected SecurityService securityService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        List<Role> roles = user.getRoleList();
        if (!CollectionUtils.isEmpty(roles)) {
            for (Role role : roles) {
                authorizationInfo.addRole(role.getName());
                List<Permission> permissions = role.getPermissionList();
                if (CollectionUtils.isEmpty(permissions)) {
                    continue;
                }
                for (Permission permission : permissions) {
                    authorizationInfo.addStringPermission(permission.getName());
                }
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        User user = securityService.getUserByName(userName);
        logger.debug("Do authenticate for user[{}]", user);
        if (user == null) {
            return null;
        }

        return new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                getName());
    }
}
