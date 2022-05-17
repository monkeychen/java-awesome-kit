package com.simiam.awekit.web.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.simiam.awekit.Awekit;
import com.simiam.awekit.Constants;
import com.simiam.awekit.util.HttpClientUtils;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: SsoService</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/8/5 10:41</p>
 */
@Service("ssoService")
public class SsoService {
    private static final Logger logger = LoggerFactory.getLogger(SsoService.class);

    public static final String AUTH_SUCCESS = "authSuccess";

    public static final String AUTH_FAIL_CAUSE = "authFailCause";

    public static final String AUTH_RESPONSE_CODE = "authResponseCode";

    public static final String SID = "sid";

    public static final String UID = "uid";

    public static final String SSO_HOST_KEY = "sso.host";

    public static final String SSO_JUNCTION_KEY = "sso.junction";

    public void prepareSsoParameters(HttpServletRequest request, ModelMap modelMap) {
        String ssoHost = getSsoHost();
        String ssoJunction = getSsoJunction();
        String ssoUrlPrefix = ssoHost + ssoJunction;
        String ivUserName = request.getHeader("iv-user");
        int fromPortalFlag = 0;
        if (!Strings.isNullOrEmpty(ivUserName)) {
            fromPortalFlag = 1;
            ssoUrlPrefix = "/sodev/" + ssoJunction;
        }
        modelMap.addAttribute("ssoUrlPrefix", ssoUrlPrefix);
        modelMap.addAttribute("fromPortalFlag", fromPortalFlag);
        JSONObject userInfo = getLoginUser(request);
        if (userInfo != null && userInfo.getString("name").equals(ivUserName)) {
            modelMap.addAttribute(Constants.USER_INFO, userInfo);
            modelMap.addAttribute(SID, userInfo.getString(SID));
        } else {
            request.getSession().removeAttribute(Constants.USER_INFO);
            request.getSession().removeAttribute(SID);
        }
    }

    public JSONObject ssoAuth(HttpServletRequest request, String sid) {
        JSONObject responseJson = new JSONObject();
        responseJson.put(AUTH_SUCCESS, false);

        JSONObject userInfo = getLoginUser(request);
        if (userInfo != null) {
            userInfo.put(AUTH_SUCCESS, true);
            return userInfo;
        }

        if (!Strings.isNullOrEmpty(sid)) {
            request.getSession().setAttribute(SID, sid);
        }
        sid = (String) request.getSession().getAttribute(SID);
        if (Strings.isNullOrEmpty(sid)) {
            responseJson.put(AUTH_FAIL_CAUSE, "SID is null or empty!");
            responseJson.put(AUTH_RESPONSE_CODE, 1);
            return responseJson;
        }

        String sidCheckUrl = getSidCheckUrl(sid);
        logger.debug("SID check url = {}", sidCheckUrl);
        JSONArray sidJsonArr = (JSONArray) HttpClientUtils.httpJsonGet(sidCheckUrl);
        if (sidJsonArr == null || sidJsonArr.size() == 0) {
            responseJson.put(AUTH_FAIL_CAUSE, "Fail to check SID, the url is: " + sidCheckUrl);
            responseJson.put(AUTH_RESPONSE_CODE, 2);
            return responseJson;
        }
        JSONObject sidJson = sidJsonArr.getJSONObject(0);
        logger.info("SID check response: {}!", sidJson);

        String uid = sidJson.getString(UID);
        if (Strings.isNullOrEmpty(uid)) {
            responseJson.put(AUTH_FAIL_CAUSE, "UID is null or empty!");
            responseJson.put(AUTH_RESPONSE_CODE, 3);
            return responseJson;
        }
        uid = uid.replaceAll(",", "");
        request.getSession().setAttribute(UID, uid);
        request.getSession().setAttribute("login_time", sidJson.getString("timestamp"));

        String uidCheckUrl = getUidCheckUrl(uid);
        logger.debug("UID check url = {}", uidCheckUrl);
        JSONArray uidJsonArr = (JSONArray) HttpClientUtils.httpJsonGet(uidCheckUrl);
        if (uidJsonArr == null || uidJsonArr.size() == 0) {
            responseJson.put(AUTH_FAIL_CAUSE, "Fail to check UID, the url is: " + uidCheckUrl);
            responseJson.put(AUTH_RESPONSE_CODE, 4);
            return responseJson;
        }
        JSONObject uidJson = uidJsonArr.getJSONObject(0);
        logger.info("UID check response: {}!", uidJson);
        responseJson = uidJson;
        responseJson.put(AUTH_SUCCESS, true);
        responseJson.put(AUTH_RESPONSE_CODE, 0);
        responseJson.put(SID, sid);
        request.getSession().setAttribute(AUTH_SUCCESS, true);
        request.getSession().setAttribute(Constants.USER_INFO, responseJson);
        return responseJson;
    }

    public JSONObject getLoginUser(HttpServletRequest request) {
        return (JSONObject) request.getSession().getAttribute(Constants.USER_INFO);
    }

    public String getSsoHost() {
        return Awekit.getEnvProperty(SSO_HOST_KEY, "http://10.48.190.35/");
    }

    public String getSsoJunction() {
        return Awekit.getEnvProperty(SSO_JUNCTION_KEY, "endtoend");
    }

    public String getSidCheckUrl(String sid) {
        return getSsoHost() + getSsoJunction() + "/sidcheck.json?sid=" + sid;
    }

    public String getUidCheckUrl(String uid) {
        return getSsoHost() + getSsoJunction() + "/uidcheck.json?uid=" + uid;
    }
}
