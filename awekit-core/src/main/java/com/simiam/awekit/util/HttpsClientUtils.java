package com.simiam.awekit.util;

import com.alibaba.fastjson.JSON;
import com.simiam.awekit.Awekit;
import com.google.common.collect.Lists;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>Title: HttpClientUtils</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/8/5 11:30</p>
 */
public abstract class HttpsClientUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpsClientUtils.class);

    public static CookieStore cookieStore = new BasicCookieStore();

    public static HttpClientContext httpClientContext = HttpClientContext.create();

    public static CloseableHttpClient httpClient;

    public static JsonResponseHandler jsonResponseHandler = new JsonResponseHandler(cookieStore);

    static {
        try {
            // 在调用SSL之前需要重写验证方法，取消检测SSL
            X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] xcs, String str) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] xcs, String str) {
                }
            };
            SSLContext ctx = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
            ctx.init(null, new TrustManager[]{trustManager}, null);
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
            // 创建Registry
            RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).setRedirectsEnabled(true)
                    .setExpectContinueEnabled(Boolean.TRUE).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                    .setProxyPreferredAuthSchemes(Collections.singletonList(AuthSchemes.BASIC)).build();
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", socketFactory).build();
            // 创建ConnectionManager，添加Connection配置信息
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            httpClientContext.setCookieStore(cookieStore);
            connectionManager.setMaxTotal(Awekit.getEnvProperty("http.client.max.total", 50));
            connectionManager.setDefaultMaxPerRoute(Awekit.getEnvProperty("http.client.max.per.route", 5));
            httpClient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig)
                    .setDefaultCookieStore(cookieStore).build();
        } catch (KeyManagementException | NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
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

    public static <T> T sendHttpPost(String url, JSON json, List<BasicHeader> headerList,
            ResponseHandler<T> responseHandler) {
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
