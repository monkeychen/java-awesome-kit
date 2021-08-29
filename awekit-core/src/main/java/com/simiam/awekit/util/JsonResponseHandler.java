package com.simiam.awekit.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.simiam.awekit.Constants;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.cookie.Cookie;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <p>Title: JsonResponseHandler</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/8/7 8:47</p>
 */
public class JsonResponseHandler implements ResponseHandler<JSON> {
    private static final Logger logger = LoggerFactory.getLogger(JsonResponseHandler.class);

    private CookieStore cookieStore;

    public JsonResponseHandler(CookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }

    @Override
    public JSON handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        for (Cookie cookie : cookieStore.getCookies()) {
            logger.debug("{} = {}", cookie.getName(), cookie.getValue());
        }
        JSON emptyJson = new JSONObject();
        if (response == null) {
            return emptyJson;
        }
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            Object object = JSON.parse(EntityUtils.toString(response.getEntity(), Constants.UTF8));
            if (object instanceof JSONObject) {
                return (JSONObject) object;
            } else if (object instanceof JSONArray) {
                return (JSONArray) object;
            }
        }
        return emptyJson;
    }
}
