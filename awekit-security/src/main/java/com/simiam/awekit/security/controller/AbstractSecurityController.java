package com.simiam.awekit.security.controller;

import com.simiam.awekit.BaseConstants;
import com.simiam.awekit.api.ResponseBody;
import com.simiam.awekit.api.ResponseCode;
import com.simiam.awekit.entity.AbstractEntity;
import com.simiam.awekit.security.entity.Department;
import com.simiam.awekit.security.entity.User;
import com.simiam.awekit.util.DateUtils;
import com.simiam.awekit.security.util.SecurityUtils;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: AbstractSecurityController</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/12/24 5:23 下午</p>
 */
public abstract class AbstractSecurityController {
    private static final Logger logger = LoggerFactory.getLogger(AbstractSecurityController.class);

    public static SimpleDateFormat SDF = new SimpleDateFormat(DateUtils.DEFAULT_FORMAT);

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    static {
        SDF.setLenient(false);
    }

    public User getCurrentUser() {
        return SecurityUtils.getAuthenticatedUserFromSession();
    }

    public String getCurrentSessionId() {
        return SecurityUtils.getCurrentSessionId();
    }

    public String getRequestToken() {
        return SecurityUtils.getRequestToken(WebUtils.toHttp(request));
    }

    public void putUserInfoToResponseData(User user, Map<String, Object> dataMap) {
        SecurityUtils.putUserInfoToResponseData(user, dataMap);
    }

    protected ResponseBody<Map<String, Object>> newResponseBody() {
        ResponseBody<Map<String, Object>> responseBody = new ResponseBody<>(ResponseCode.CODE_OK, ResponseCode.MSG_OK);
        Map<String, Object> dataMap = Maps.newHashMap();
        responseBody.setData(dataMap);

        User user = getCurrentUser();
        if (user == null) {
            String requestToken = getRequestToken();
            logger.debug("The request token is: [{}]", requestToken);
            responseBody = new ResponseBody<>(ResponseCode.CODE_NOT_LOGIN, ResponseCode.MSG_NOT_LOGIN);
            responseBody.setToken(requestToken);
            throw new AuthenticationException(responseBody.toString());
        } else {
            String sessionId = getCurrentSessionId();
            logger.debug("The current session-id is: [{}]", sessionId);
            responseBody.setToken(sessionId);
            dataMap.put(BaseConstants.USERNAME, user.getUsername());
            dataMap.put(BaseConstants.REAL_NAME, user.getRealName());
        }
        return responseBody;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(SDF, true));
    }

    protected List<User> desensitizeUserList(List<User> userList) {
        if (CollectionUtils.isEmpty(userList)) {
            return userList;
        }
        for (User user : userList) {
            user.setPassword(BaseConstants.MASK_CODE);
            user.setConfirmPassword(BaseConstants.MASK_CODE);
            user.setSalt(BaseConstants.MASK_CODE);
            Department department = user.getDepartment();
            if (department != null) {
                user.setDepartmentName(department.getParentPath() + "/" + department.getName());
            }
        }
        return userList;
    }

    protected Sort.Order createSortOrder(AbstractEntity sample, String defSortColumn) {
        String sortColName = sample.getSortColumn();
        if (Strings.isNullOrEmpty(sortColName)) {
            sortColName = defSortColumn;
        }
        return "asc".equalsIgnoreCase(sample.getSortOrder()) ? Sort.Order.asc(sortColName) : Sort.Order.desc(sortColName);
    }
}
