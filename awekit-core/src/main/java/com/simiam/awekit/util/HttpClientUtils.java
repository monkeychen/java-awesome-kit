package com.simiam.awekit.util;

import com.alibaba.fastjson.JSON;
import com.simiam.awekit.Awekit;
import com.google.common.collect.Lists;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Title: HttpClientUtils</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/8/5 11:30</p>
 */
public abstract class HttpClientUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    public static final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

    public static RequestConfig requestConfig;

    public static CookieStore cookieStore = new BasicCookieStore();

    public static HttpClientContext httpClientContext = HttpClientContext.create();

    public static CloseableHttpClient httpClient;

    public static JsonResponseHandler jsonResponseHandler = new JsonResponseHandler(cookieStore);

    static {
        requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        httpClientContext.setCookieStore(cookieStore);
        cm.setMaxTotal(Awekit.getEnvProperty("http.client.max.total", 50));
        cm.setDefaultMaxPerRoute(Awekit.getEnvProperty("http.client.max.per.route", 5));
        httpClient = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(requestConfig)
                .setDefaultCookieStore(cookieStore).build();
    }

    public static <T> T sendHttpPost(String url, List<NameValuePair> paramList, ResponseHandler<T> responseHandler) {
        HttpPost httpPost = new HttpPost(url);
        if (!CollectionUtils.isEmpty(paramList)) {
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(paramList));
            } catch (UnsupportedEncodingException e) {
                logger.error("Fail to encode parameter list[{}]!", paramList, e);
            }
        }
        try {
            return httpClient.execute(httpPost, responseHandler);
        } catch (IOException e) {
            logger.error("Fail to send HTTP_POST request to url:{}", url, e);
        }
        return null;
    }

    public static <T> T sendHttpPost(String url, JSON json, List<BasicHeader> headerList, ResponseHandler<T> responseHandler) {
        HttpPost httpPost = new HttpPost(url);
        if (json != null) {
            try {
                StringEntity requestEntity = new StringEntity(json.toJSONString(), StandardCharsets.UTF_8);
                requestEntity.setContentEncoding("UTF-8");
                boolean hasFoundContentType = false;
                if (!CollectionUtils.isEmpty(headerList)) {
                    for (BasicHeader header : headerList) {
                        httpPost.setHeader(header);
                        if ("Content-type".equalsIgnoreCase(header.getName())) {
                            hasFoundContentType = true;
                        }
                    }
                }
                if (!hasFoundContentType) {
                    httpPost.setHeader("Content-type", "application/json;charset=UTF-8");
                }
                httpPost.setEntity(requestEntity);
            } catch (Exception e) {
                logger.error("Fail to encode json parameter list[{}]!", json.toJSONString(), e);
            }
        }
        try {
            return httpClient.execute(httpPost, responseHandler);
        } catch (IOException e) {
            logger.error("Fail to send HTTP_POST request to url:{} with json[{}]", url, json, e);
        }
        return null;
    }

    public static <T> T sendHttpPost(String url, JSON json, ResponseHandler<T> responseHandler) {
        HttpPost httpPost = new HttpPost(url);
        if (json != null) {
            try {
                StringEntity requestEntity = new StringEntity(json.toJSONString(), StandardCharsets.UTF_8);
                requestEntity.setContentEncoding("UTF-8");
                httpPost.setHeader("Content-type", "application/json");
                httpPost.setEntity(requestEntity);
            } catch (Exception e) {
                logger.error("Fail to encode json parameter list[{}]!", json.toJSONString(), e);
            }
        }
        try {
            return httpClient.execute(httpPost, responseHandler);
        } catch (IOException e) {
            logger.error("Fail to send HTTP_POST request to url:{} with json[{}]", url, json, e);
        }
        return null;
    }

    public static <T> T sendHttpGet(String url, ResponseHandler<T> responseHandler) {
        HttpGet httpGet = new HttpGet(url);
        try {
            return httpClient.execute(httpGet, responseHandler);
        } catch (IOException e) {
            logger.error("Fail to send HTTP_POST request to url:{}", url, e);
        }
        return null;
    }

    public static <T> T execute(String url, String httpMethod, ResponseHandler<T> responseHandler,
            NameValuePair... params) {
        List<NameValuePair> paramList = Lists.newArrayList();
        if (params != null && params.length > 0) {
            paramList = Arrays.asList(params);
        }
        T result;
        switch (httpMethod) {
            case HttpPost.METHOD_NAME:
                result = sendHttpPost(url, paramList, responseHandler);
                break;
            default:
                result = sendHttpGet(url, responseHandler);
        }
        return result;
    }

    public static <T> T httpPost(String url, ResponseHandler<T> responseHandler) {
        return execute(url, HttpPost.METHOD_NAME, responseHandler);
    }

    public static JSON httpJsonPost(String url, JSON json) {
        return sendHttpPost(url, json, jsonResponseHandler);
    }

    public static JSON httpJsonPost(String url, JSON json, List<BasicHeader> headerList) {
        return sendHttpPost(url, json, headerList, jsonResponseHandler);
    }

    public static JSON httpJsonPost(String url) {
        return execute(url, HttpPost.METHOD_NAME, jsonResponseHandler);
    }

    public static <T> T httpGet(String url, ResponseHandler<T> responseHandler) {
        return execute(url, HttpGet.METHOD_NAME, responseHandler);
    }

    public static JSON httpJsonGet(String url) {
        return execute(url, HttpGet.METHOD_NAME, jsonResponseHandler);
    }

    public static void main(String[] args) {

    }
}
