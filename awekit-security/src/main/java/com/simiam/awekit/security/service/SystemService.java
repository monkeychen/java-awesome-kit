package com.simiam.awekit.security.service;

import com.simiam.awekit.security.entity.Department;
import com.simiam.awekit.security.entity.Setting;
import com.simiam.awekit.security.entity.UiTbColumnMeta;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>Title: SystemService</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/12/27 11:27 上午</p>
 */
public interface SystemService {
    List<Setting> listSetting(String groupName);

    Setting getSetting(String groupName, String paramName);

    Map<String, Setting> getSettingMap(String groupName);

    Map<String, Map<String, Setting>> getGroupSettingMap();

    <T> T getParamVal(String groupName, String paramName, Class<T> paramTypeClass);

    Map<String, String> getNameLabelMap(String groupName);

    Map<String, String> getNameValMap(String groupName);

    Optional<Department> getDepartment(String name, String parentPath);

    boolean isDepartmentPresent(String name, String parentPath);

    void batchSaveOrUpdateDepartment(List<Department> departmentList);

    Map<String, Map<String, List<UiTbColumnMeta>>> loadUiColumnMetaGroupMap();

    Map<String, Map<String, List<UiTbColumnMeta>>> loadUiColumnMetaGroupMap(String moduleName);
}
