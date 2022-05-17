package com.simiam.awekit.security.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>Title: SsoMockController</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2020</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2020/1/26 1:20 下午</p>
 */
@RestController
@RequestMapping("/ssomock")
public class SsoMockController {

    private static Map<String, JSONObject> userCache = Maps.newHashMap();

    static {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "admin");
        jsonObject.put("field_ldap_cn", "超级管理员");
        userCache.put("2ddcf9eb-5e94-411e-9cd6-acc82803782c", jsonObject);
    }

    @RequestMapping(path = "/user/token.json")
    public Map<String, Object> getToken() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("sid", UUID.randomUUID().toString());
        return map;
    }

    @RequestMapping(path = "/sidcheck.json")
    public List<Map<String, Object>> getUid(String sid) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("uid", "2ddcf9eb-5e94-411e-9cd6-acc82803782c");
        return Lists.newArrayList(map);
    }

    @RequestMapping(path = "/uidcheck.json")
    public JSONArray getUserInfo(String uid) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(userCache.get(uid));
        return jsonArray;
    }


}
