package com.simiam.awekit.security.service;

import com.simiam.awekit.security.entity.Department;
import com.simiam.awekit.security.entity.Setting;
import com.simiam.awekit.security.entity.UiTbColumnMeta;
import com.simiam.awekit.security.repository.DepartmentRepository;
import com.simiam.awekit.security.repository.SettingRepository;
import com.simiam.awekit.security.repository.UiTbColumnMetaRepository;
import com.google.common.base.Strings;
import com.google.common.collect.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>Title: SystemServiceImpl</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/12/26 4:54 下午</p>
 */
@Service("systemService")
public class SystemServiceImpl implements SystemService {
    private static final Logger logger = LoggerFactory.getLogger(SystemServiceImpl.class);

    @Autowired
    private UiTbColumnMetaRepository uiTbColumnMetaRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SettingRepository settingRepository;

    @Override
    public List<Setting> listSetting(String groupName) {
        Setting sample = new Setting();
        sample.setEnable(true);
        if (groupName != null) {
            sample.setGroupName(groupName);
        }
        List<Setting> settings = settingRepository.findAll(Example.of(sample), Sort.by(Sort.Order.by("sort")));
        if (CollectionUtils.isEmpty(settings)) {
            return Lists.newArrayList();
        }
        return settings;
    }

    @Override
    public Setting getSetting(String groupName, String paramName) {
        Setting sample = new Setting();
        sample.setGroupName(groupName);
        sample.setParamName(paramName);
        sample.setEnable(true);
        List<Setting> settings = settingRepository.findAll(Example.of(sample), Sort.by(Sort.Order.by("sort")));
        if (CollectionUtils.isEmpty(settings)) {
            return null;
        }
        return settings.get(0);
    }

    @Override
    public Map<String, Setting> getSettingMap(String groupName) {
        if (Strings.isNullOrEmpty(groupName)) {
            return Maps.newHashMap();
        }
        List<Setting> settings = listSetting(groupName);
        return Maps.uniqueIndex(settings, Setting::getParamName);
    }

    @Override
    public Map<String, Map<String, Setting>> getGroupSettingMap() {
        Map<String, Map<String, Setting>> resultMap = Maps.newHashMap();
        List<Setting> settings = listSetting(null);
        if (CollectionUtils.isEmpty(settings)) {
            return resultMap;
        }
        for (Setting setting : settings) {
            resultMap.computeIfAbsent(setting.getGroupName(), s -> Maps.newHashMap())
                    .put(setting.getParamName(), setting);
        }
        return resultMap;
    }

    @Override
    public <T> T getParamVal(String groupName, String paramName, Class<T> paramTypeClass) {
        Setting setting = getSetting(groupName, paramName);
        if (setting == null) {
            return null;
        }
        if (paramTypeClass.getName().equalsIgnoreCase(setting.getParamType())) {
            return paramTypeClass.cast(setting.getParamVal());
        }
        return null;
    }

    @Override
    public Map<String, String> getNameLabelMap(String groupName) {
        List<Setting> settings = listSetting(groupName);
        return settings.stream().collect(Collectors.toMap(Setting::getGroupName, Setting::getParamLabel));
    }

    @Override
    public Map<String, String> getNameValMap(String groupName) {
        List<Setting> settings = listSetting(groupName);
        return settings.stream().collect(Collectors.toMap(Setting::getGroupName, Setting::getParamVal));
    }

    @Override
    public Optional<Department> getDepartment(String name, String parentPath) {
        Department department = departmentRepository.getFirstByNameAndParentPath(name, "/福建公司");
        if (department == null) {
            return Optional.empty();
        }
        return Optional.of(department);
    }

    @Override
    public boolean isDepartmentPresent(String name, String parentPath) {
        return getDepartment(name, parentPath).isPresent();
    }

    @Override
    public synchronized void batchSaveOrUpdateDepartment(List<Department> departmentList) {
        if (CollectionUtils.isEmpty(departmentList)) {
            return;
        }
        List<Department> existList = departmentRepository.findAll();
        Map<String, Long> depIdMap = Maps.newHashMap();
        for (Department department : existList) {
            String path = department.getParentPath() + "/" + department.getName();
            depIdMap.put(path, department.getId());
        }
        for (Department department : departmentList) {
            String path = department.getParentPath() + "/" + department.getName();
            if (depIdMap.containsKey(path)) {
                department.setId(depIdMap.get(path));
                continue;
            }
            departmentRepository.save(department);
            depIdMap.put(path, department.getId());
        }
    }

    @Override
    public Map<String, Map<String, List<UiTbColumnMeta>>> loadUiColumnMetaGroupMap() {
        List<UiTbColumnMeta> metaList = uiTbColumnMetaRepository.findByEnable(true);
        return createModuleGroupMetaListMap(metaList);
    }

    @Override
    public Map<String, Map<String, List<UiTbColumnMeta>>> loadUiColumnMetaGroupMap(String moduleName) {
        List<UiTbColumnMeta> metaList = uiTbColumnMetaRepository.findByModuleNameAndEnable(moduleName, true);
        return createModuleGroupMetaListMap(metaList);
    }

    private Map<String, Map<String, List<UiTbColumnMeta>>> createModuleGroupMetaListMap(List<UiTbColumnMeta> metaList) {
        Map<String, Map<String, List<UiTbColumnMeta>>> moduleGroupMetaListMap = Maps.newHashMap();
        if (CollectionUtils.isEmpty(metaList)) {
            return moduleGroupMetaListMap;
        }
        ArrayListMultimap<String, UiTbColumnMeta> moduleMetaMultimap = ArrayListMultimap.create();
        for (UiTbColumnMeta meta : metaList) {
            String extra = meta.getExtra();
            if (!Strings.isNullOrEmpty(extra) && extra.toLowerCase().contains("sortable")) {
                meta.setSortable(true);
            }
            moduleMetaMultimap.put(meta.getModuleName(), meta);
        }
        Map<String, List<UiTbColumnMeta>> moduleMetasMap = Multimaps.asMap(moduleMetaMultimap);
        for (Map.Entry<String, List<UiTbColumnMeta>> listEntry : moduleMetasMap.entrySet()) {
            String moduleName = listEntry.getKey();
            List<UiTbColumnMeta> metas = listEntry.getValue();
            if (CollectionUtils.isEmpty(metas)) {
                continue;
            }
            ArrayListMultimap<String, UiTbColumnMeta> groupMetaMultiMap = ArrayListMultimap.create();
            for (UiTbColumnMeta meta : metas) {
                groupMetaMultiMap.put(meta.getGroupName(), meta);
            }
            Map<String, List<UiTbColumnMeta>> listMap = Multimaps.asMap(groupMetaMultiMap);
            // 排序
            for (List<UiTbColumnMeta> value : listMap.values()) {
                value.sort(Comparator.comparingInt(UiTbColumnMeta::getSort));
            }
            moduleGroupMetaListMap.put(moduleName, listMap);
        }
        return moduleGroupMetaListMap;
    }

    public static HashBasedTable<String, String, UiTbColumnMeta> toHashBasedTable(List<UiTbColumnMeta> columnMetaList) {
        HashBasedTable<String, String, UiTbColumnMeta> table = HashBasedTable.create();
        if (CollectionUtils.isEmpty(columnMetaList)) {
            return table;
        }
        for (UiTbColumnMeta meta : columnMetaList) {
            table.put(meta.getGroupName(), meta.getFieldName(), meta);
        }
        return table;
    }
}
