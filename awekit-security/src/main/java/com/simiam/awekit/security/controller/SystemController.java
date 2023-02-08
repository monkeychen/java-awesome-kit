package com.simiam.awekit.security.controller;

import com.simiam.awekit.api.ResponseBody;
import com.simiam.awekit.security.entity.Setting;
import com.simiam.awekit.security.entity.UiTbColumnMeta;
import com.simiam.awekit.security.service.SystemService;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: SystemController</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/12/31 9:10 上午</p>
 */
@RestController
@RequestMapping("/api/system")
public class SystemController extends AbstractSecurityController {
    private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private SystemService systemService;

    @GetMapping(path = {"/setting", "/setting/{groupName}"})
    public ResponseBody<Map<String, Object>> getGroupSettingMap(@PathVariable(required = false) String groupName) {
        ResponseBody<Map<String, Object>> responseBody = newResponseBody();
        Map<String, Map<String, Setting>> resultMap = Maps.newHashMap();
        if (Strings.isNullOrEmpty(groupName)) {
            resultMap = systemService.getGroupSettingMap();
        } else {
            Map<String, Setting> map = systemService.getSettingMap(groupName);
            resultMap.put(groupName, map);
        }
        responseBody.getData().put("group_map", resultMap);
        return responseBody;
    }

    @GetMapping(path = {"/columnMeta", "/columnMeta/{moduleName}"})
    public ResponseBody<Map<String, Object>> getUiColumnMetaListMap(@PathVariable(required = false) String moduleName) {
        ResponseBody<Map<String, Object>> responseBody = newResponseBody();
        Map<String, Map<String, List<UiTbColumnMeta>>> resultMap;
        if (Strings.isNullOrEmpty(moduleName)) {
            resultMap = systemService.loadUiColumnMetaGroupMap();
        } else {
            resultMap = systemService.loadUiColumnMetaGroupMap(moduleName);
        }
        responseBody.getData().put("meta_map", resultMap);
        return responseBody;
    }
}
