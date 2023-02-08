package com.simiam.awekit.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.simiam.awekit.Constants;
import com.simiam.awekit.entity.Token;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.simiam.awekit.util.HttpsClientUtils;
import org.apache.hadoop.hdfs.util.MD5FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: BaiduPanController</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2021</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2021/12/4 8:34 下午</p>
 */

@Service
public class BaiduPanService implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(BaiduPanService.class);

    @Value("${baidu.pan.appKey}")
    private String appKey;

    @Value("${baidu.pan.secretKey}")
    private String secretKey;

    @Value("${baidu.pan.redirectUrl}")
    private String redirectUrl;

    @Value("${baidu.pan.refreshTokenPath}")
    private String refreshTokenPath;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    private Token token;

    public Map<String, Object> getUserInfo() {
        Map<String, Object> resultMap = Maps.newHashMap();
        String url = "https://pan.baidu.com/rest/2.0/xpan/nas?method=uinfo&access_token=" + getAccessToken();
        logger.info("The get-user-info-url is: {}", url);
        JSON json = HttpsClientUtils.httpJsonGet(url);
        resultMap.put("result", json);
        return resultMap;
    }

    public Map<String, Object> deleteFile(String filePath) {
        Map<String, Object> resultMap = Maps.newHashMap();
        String url = "https://pan.baidu.com/rest/2.0/xpan/file?method=filemanager&access_token=" + getAccessToken()
                + "&opera=delete";
        logger.info("The delete-file-url is: {}", url);
        JSONObject paramJson = new JSONObject();
        paramJson.put("async", 0);
        paramJson.put("filelist", Lists.newArrayList(filePath));
        JSON json = HttpsClientUtils.httpJsonPost(url, paramJson);
        resultMap.put("result", json);
        return resultMap;
    }

    public Map<String, Object> listFile(String dirPath) {
        Map<String, Object> resultMap = Maps.newHashMap();
        String url = "https://pan.baidu.com/rest/2.0/xpan/file?method=list&access_token=" + getAccessToken() + "&dir=" + dirPath
                + "&opera=delete";
        logger.info("The list-file-url is: {}", url);
        JSON json = HttpsClientUtils.httpJsonGet(url);
        resultMap.put("result", json);
        return resultMap;
    }

    public Map<String, Object> uploadFile(String destDirPath, File file) {
        Map<String, Object> resultMap = Maps.newHashMap();
        String url1 = "https://pan.baidu.com/rest/2.0/xpan/file?method=precreate&access_token=" + getAccessToken();
        logger.info("The pre-upload-file-url is: {}", url1);
        JSONObject paramJson = new JSONObject();
        String filePath = destDirPath + "/" + file.getName();
        try {
            filePath = URLEncoder.encode(filePath, Constants.UTF8);
        } catch (UnsupportedEncodingException e) {
            logger.error("Fail to encode file path: {}", filePath, e);
        }
        paramJson.put("path", filePath);
        paramJson.put("size", file.length());
        paramJson.put("isdir", 0);
        paramJson.put("autoinit", 1);
        paramJson.put("rtype", 3);
        String fileMd5 = "";
        try {
            fileMd5 = MD5FileUtils.computeMd5ForFile(file).toString();
            paramJson.put("block_list", Lists.newArrayList(fileMd5));

        } catch (IOException e) {
            logger.error("Fail to computeMd5 for file: {}", file.getAbsolutePath(), e);
        }

        JSON step1Json = HttpsClientUtils.httpJsonPost(url1, paramJson);
        if (step1Json instanceof JSONObject) {
            JSONObject obj1 = (JSONObject) step1Json;
            int errNo = obj1.getIntValue("errno");
            if (errNo != 0) {
                resultMap.put("result", "Upload fail in step 1!");
                return resultMap;
            }
            String path = obj1.getString("path");
            String uploadId = obj1.getString("uploadid");
            int returnType = obj1.getIntValue("return_type");
            JSONArray arr = obj1.getJSONArray("block_list");
            List<Integer> blockList = arr.toJavaList(Integer.class);

            String url2 = "https://pan.baidu.com/rest/2.0/xpan/file?method=upload&access_token=" + getAccessToken() + "&type=tmpfile&path="
                    + filePath + "&uploadid=" + uploadId + "&partseq=0";
            logger.info("The upload-file-url is: {}", url2);
            paramJson = new JSONObject();
            try {
                paramJson.put("file", Files.readAllBytes(file.toPath()));
                JSON step2Json = HttpsClientUtils.httpJsonPost(url2, paramJson);
                JSONObject obj2 = (JSONObject) step2Json;
                errNo = obj2.getIntValue("errno");
                String sliceMd5 = "";
                if (errNo != 0) {
                    resultMap.put("result", "Upload fail in step 2!");
                    return resultMap;
                } else {
                    sliceMd5 = obj2.getString("md5");
                    logger.info("slice block's md5: {}", sliceMd5);
                }
            } catch (IOException e) {
                logger.error("Fail to read file: {}", file.getAbsolutePath(), e);
            }

            String url3 = "https://pan.baidu.com/rest/2.0/xpan/file?method=create&access_token=" + getAccessToken();
            logger.info("The after-upload-file-url is: {}", url3);
            paramJson = new JSONObject();
            paramJson.put("path", filePath);
            paramJson.put("size", file.length());
            paramJson.put("isdir", 0);
//            paramJson.put("uploadid", uploadId);
            paramJson.put("rtype", 3);
            JSON step3Json = HttpsClientUtils.httpJsonPost(url3, paramJson);
            resultMap.put("result", step3Json);
        }

        return resultMap;
    }

    public String getAuthorizeUrl() {
        String url = "http://openapi.baidu.com/oauth/2.0/authorize?response_type=code" +
                "&client_id=" + appKey + "&redirect_uri=" + redirectUrl + "&scope=basic,netdisk";
        logger.info("The oauth request url is: {}", url);
        return url;
    }

    public synchronized Map<String, Object> authorizeRedirect(String code) throws IOException {
        Map<String, Object> resultMap = Maps.newHashMap();
        String url = "https://openapi.baidu.com/oauth/2.0/token?grant_type=authorization_code" +
                "&code=" + code + "&client_id=" + appKey + "&client_secret=" + secretKey + "&redirect_uri=" + redirectUrl;
        logger.info("The authorization_code url: {}", url);
        JSON json = HttpsClientUtils.httpJsonGet(url);
        this.updateTokenInstance(json);
        resultMap.put("result", json);
        return resultMap;
    }

    public String getAccessToken() {
        if (this.token == null) {
            this.updateAccessToken();
        } else {
            if (this.token.isExpired()) {
                this.updateAccessToken();
            }
        }
        return this.token.getAccessToken();
    }

    public void updateAccessToken() {
        if (this.token == null) {
            this.initRefreshTokenFromLocal();
        }
        if (this.token != null) {
            String url = "https://openapi.baidu.com/oauth/2.0/token?grant_type=refresh_token&refresh_token=" + this.token.getRefreshToken()
                    + "&client_id=" + this.appKey + "&client_secret=" + this.secretKey;
            logger.info("The access-token refresh url is: {}" + url);
            JSON json = HttpsClientUtils.httpJsonGet(url);
            this.updateTokenInstance(json);
        }
    }

    public void updateTokenInstance(JSON json) {
        if (json instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) json;
            String refreshToken = jsonObject.getString("refresh_token");
            logger.info("The response json is: {}", jsonObject);
            try {
                com.google.common.io.Files.write(refreshToken.getBytes(StandardCharsets.UTF_8), new File(refreshTokenPath));
            } catch (IOException e) {
                logger.error("Fail to write refresh-token to local path: {}", refreshTokenPath);
            }
            String accessToken = jsonObject.getString("access_token");
            Long expiredSecond = jsonObject.getLong("expires_in");
            this.token = new Token(accessToken, refreshToken, expiredSecond);
        }
    }

    public boolean initRefreshTokenFromLocal() {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(refreshTokenPath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error("Fail to load refresh-token from local path[{}]", refreshTokenPath, e);
            return false;
        }
        if (!CollectionUtils.isEmpty(lines)) {
            String refreshToken = lines.get(0);
            this.token = new Token(refreshToken);
            return true;
        } else {
            logger.warn("Can not find valid refreshToken if file[{}]!!!!!", refreshTokenPath);
            return false;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.initRefreshTokenFromLocal()) {
            this.updateAccessToken();
        }
    }
}
