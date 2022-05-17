package com.simiam.awekit.security.shiro;

import com.simiam.awekit.api.ResponseBody;
import com.simiam.awekit.api.ResponseCode;
import com.simiam.awekit.security.entity.User;
import com.simiam.awekit.security.util.SecurityUtils;
import com.google.common.collect.Maps;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>Title: ShiroExceptionHandler</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/12/19 6:30 下午</p>
 */
@RestControllerAdvice
public class ShiroExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ShiroExceptionHandler.class);

    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(value = {AuthenticationException.class, AuthorizationException.class,
            UnauthenticatedException.class, UnauthorizedException.class})
    public ResponseBody<Object> handleException(Exception e) {
        ResponseBody<Object> responseBody = new ResponseBody<>(ResponseCode.CODE_UNKNOWN, ResponseCode.MSG_UNKNOWN);

        Map<String, Object> dataMap = Maps.newHashMap();
        responseBody.setData(dataMap);
        User user = SecurityUtils.getCurrentUser();
        if (user == null) {
            responseBody = new ResponseBody<>(ResponseCode.CODE_NOT_LOGIN, ResponseCode.MSG_NOT_LOGIN);
            responseBody.setToken(SecurityUtils.getRequestToken(request));
        } else {
            responseBody.setToken(SecurityUtils.getCurrentSessionId());
            SecurityUtils.putUserInfoToResponseData(user, dataMap);
        }
        responseBody.setData(dataMap);
        if (e instanceof IncorrectCredentialsException) {
            responseBody.setCode(ResponseCode.CODE_INVALID_PASSWORD);
            responseBody.setMessage(ResponseCode.MSG_INVALID_PASSWORD);
        } else if (e instanceof AuthenticationException) {
            responseBody.setCode(ResponseCode.CODE_USER_NOT_EXIST);
            responseBody.setMessage(ResponseCode.MSG_USER_NOT_EXIST);
        } else if (e instanceof AuthorizationException) {
            responseBody.setCode(ResponseCode.CODE_NOT_ENOUGH_PERMISSION);
            responseBody.setMessage(ResponseCode.MSG_NOT_ENOUGH_PERMISSION);
        } else {
            responseBody.setCode(ResponseCode.CODE_UNKNOWN);
            responseBody.setMessage(ResponseCode.MSG_UNKNOWN);
        }
        logger.warn("code = {}, message = {}", responseBody.getCode(), responseBody.getMessage());
        return responseBody;
    }
}
