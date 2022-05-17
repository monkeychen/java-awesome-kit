package com.simiam.awekit.security;

import com.simiam.awekit.api.ResponseBody;
import com.simiam.awekit.api.ResponseCode;
import com.simiam.awekit.security.entity.User;
import com.simiam.awekit.security.util.SecurityUtils;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>Title: SecurityExceptionHandler</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2020</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2020/1/4 3:21 下午</p>
 */
@RestControllerAdvice
public class SecurityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(SecurityExceptionHandler.class);

    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(value = {Exception.class})
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
        responseBody.setCode(ResponseCode.CODE_BUSINESS_ERROR);
        responseBody.setMessage(ResponseCode.MSG_BUSINESS_ERROR + ":" + e.getMessage());
        logger.error("code = {}, message = {}", responseBody.getCode(), responseBody.getMessage(), e);
        return responseBody;
    }
}
