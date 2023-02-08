package com.simiam.awekit.security.controller;

import com.alibaba.fastjson.JSONObject;
import com.simiam.awekit.Awekit;
import com.simiam.awekit.api.ResponseBody;
import com.simiam.awekit.api.ResponseCode;
import com.simiam.awekit.security.entity.Department;
import com.simiam.awekit.security.entity.User;
import com.simiam.awekit.security.service.SecurityService;
import com.simiam.awekit.web.service.SsoService;
import com.simiam.awekit.security.service.SystemService;
import com.simiam.awekit.security.util.SecurityUtils;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>Title: SecurityController</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/12/18 4:49 下午</p>
 */
@RestController
@RequestMapping(path = "/api")
public class SecurityController {
    private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SsoService ssoService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SystemService systemService;

    @RequestMapping(path = "/loginAuth", method = {RequestMethod.POST})
    public Map<String, Object> loginAuth(User user) {
        Map<String, Object> resultMap = Maps.newHashMap();
        Object exception = request.getAttribute("shiroLoginFailure");
        if (exception != null) {
            resultMap.put("status", "Login fail!");
        } else {
            resultMap.put("status", "Login successful!");
        }
        return resultMap;
    }

    @RequestMapping(path = "/logoutSuccess")
    public ResponseBody<String> logoutSuccess() {
        ResponseBody<String> responseBody = new ResponseBody<>(ResponseCode.CODE_LOGOUT_OK, ResponseCode.MSG_LOGOUT_OK);
        logger.info("Logout successful!");
        return responseBody;
    }

    @RequestMapping(path = "/login")
    public ResponseBody<String> login(@RequestBody User user) {
        ResponseBody<String> responseBody = new ResponseBody<>(ResponseCode.CODE_LOGIN_OK, ResponseCode.MSG_LOGIN_OK);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
            String sessionId = subject.getSession().getId().toString();
            responseBody.setToken(sessionId);
            logger.info("The user[{}] login successful!", user.getUsername());
        } catch (IncorrectCredentialsException e) {
            responseBody = new ResponseBody<>(ResponseCode.CODE_INVALID_PASSWORD, ResponseCode.MSG_INVALID_PASSWORD);
        } catch (AuthenticationException e) {
            responseBody = new ResponseBody<>(ResponseCode.CODE_USER_NOT_EXIST_OR_INVALID_PASSWORD, ResponseCode.MSG_USER_NOT_EXIST_OR_INVALID_PASSWORD);
        } catch (Exception e) {
            responseBody = new ResponseBody<>(ResponseCode.CODE_UNKNOWN, ResponseCode.MSG_UNKNOWN);
        }
        return responseBody;
    }

    @GetMapping(path = "/ssologin")
    public ResponseBody<String> ssologin(String sid) {
        ResponseBody<String> responseBody = new ResponseBody<>(ResponseCode.CODE_USER_NOT_EXIST, ResponseCode.MSG_USER_NOT_EXIST);
        JSONObject ssoResponse = ssoService.ssoAuth(request, sid);
        boolean authStatus = ssoResponse.getBooleanValue(SsoService.AUTH_SUCCESS);
        if (authStatus) {
            // 获取用户名，并到DSS数据查询用户是否存在，不存在则创建新用户（初始密码 + 短信通知）
            String userName = ssoResponse.getString("name");
            String userRealName = ssoResponse.getString("field_ldap_cn");
            String departmentInfo = ssoResponse.getString(Awekit.getEnvProperty("sso.department.key", "部门树"));
            if (!hasPermission(departmentInfo)) {
                return new ResponseBody<>(ResponseCode.CODE_NOT_ENOUGH_PERMISSION, ResponseCode.MSG_NOT_ENOUGH_PERMISSION);
            }
            // 处理部门信息
            List<Department> departmentList = extractDepartmentList(departmentInfo);

            User user = securityService.getUserByName(userName);
            if (user != null) {
                return login(user);
            }
            user = new User(userName);
            String initPwd = Awekit.getEnvProperty("sso.def.pwd", "dss@2020!!123");
            user.setPassword(SecurityUtils.md5(initPwd));
            user.setRealName(userRealName);
            if (!CollectionUtils.isEmpty(departmentList)) {
                Department department = departmentList.get(departmentList.size() - 1);
                user.setDepartmentName(department.getName());
                user.setDepartmentId(department.getId());
                user.setDepartment(department);
            }
            if (securityService.saveUser(user)) {
                return login(user);
            }
        }
        return responseBody;
    }

    @GetMapping(path = "/portallogin")
    public ResponseBody<String> portalLogin() {
        ResponseBody<String> responseBody = new ResponseBody<>(ResponseCode.CODE_USER_NOT_EXIST, ResponseCode.MSG_USER_NOT_EXIST);
        String loginUserName = request.getHeader("iv-user");
        if (!Strings.isNullOrEmpty(loginUserName)) {
            // 获取用户名，并到DSS数据查询用户是否存在，不存在则创建新用户（初始密码 + 短信通知）
            String dptName = "未分配";
            User user = securityService.getUserByName(loginUserName);
            if (user != null) {
                return login(user);
            }
            user = new User(loginUserName);
            String initPwd = Awekit.getEnvProperty("sso.def.pwd", "dss@2020!!123");
            user.setPassword(SecurityUtils.md5(initPwd));
            user.setRealName(loginUserName);
            Optional<Department> optional = systemService.getDepartment(dptName, "/福建公司");
            if (optional != null && optional.isPresent()) {
                Department department = optional.orElse(null);
                user.setDepartmentName(department.getName());
                user.setDepartmentId(department.getId());
                user.setDepartment(department);
            }
            if (securityService.saveUser(user)) {
                return login(user);
            }
        }
        return responseBody;
    }

    private List<Department> extractDepartmentList(String departmentInfo) {
        String endFlag = Awekit.getEnvProperty("sso.department.end.flag", ",OU=部门树");
        int endIndex = departmentInfo.indexOf(endFlag);
        if (endIndex > 0) {
            departmentInfo = departmentInfo.substring(0, endIndex);
        }
        departmentInfo = departmentInfo.replaceAll(
                Awekit.getEnvProperty("sso.department.replace.separator", "OU="), "");
        List<String> departList = Splitter.on(Awekit.getEnvProperty("sso.department.separator", ","))
                .omitEmptyStrings().trimResults().splitToList(departmentInfo);
        departList = Lists.reverse(departList);
        List<Department> departmentList = Department.convertFromList(departList);
        systemService.batchSaveOrUpdateDepartment(departmentList);
        return departmentList;
    }

    @RequestMapping(path = "/unauth")
    public ResponseBody<String> unauth() {
        return new ResponseBody<>(ResponseCode.CODE_NOT_LOGIN, ResponseCode.MSG_NOT_LOGIN);
    }

    @RequestMapping(path = "/ssoTokenUrl")
    public ResponseBody<Map<String, Object>> getSsoTokenUrl(ModelMap modelMap) {
        ssoService.prepareSsoParameters(request, modelMap);
        String ssoUrlPrefix = (String) modelMap.get("ssoUrlPrefix");
        String tokenUrl = ssoUrlPrefix + Awekit.getEnvProperty("sso.token.api", "/user/token.json");
        Map<String, Object> dataMap = Maps.newHashMap();
        dataMap.put("tokenUrl", tokenUrl);
        ResponseBody<Map<String, Object>> responseBody = new ResponseBody<>(ResponseCode.CODE_OK, ResponseCode.MSG_OK);
        responseBody.setData(dataMap);
        return responseBody;
    }

    private boolean hasPermission(String departmentInfo) {
        if (Strings.isNullOrEmpty(departmentInfo)) {
            return false;
        }
        return departmentInfo.contains(Awekit.getEnvProperty("sso.allow.visit.root.department", "福建公司"));
    }
}
