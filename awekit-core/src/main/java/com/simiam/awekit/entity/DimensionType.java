package com.simiam.awekit.entity;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;

/**
 * <p>Title: DimensionType</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/11/20 8:44 PM</p>
 */
public enum DimensionType {
    UNKNOWN("unknown", "未知", 8, true),
    TERMINAL("terminal", "终端", 7, true),
    APN("apn", "APN", 6, true),
    ECI("eci", "小区", 5, true),
    NET("netip", "网元", 4, true),
    SERVER("serviceip", "服务器", 3, true),
    APP("app", "业务", 2, true),
    CITY("city", "城市", 1, true),
    SCENE("scene", "小区场景", 10, false),
    APP_SERVICE_IP_FAIL_CODE("appServiceIpFailCode", "业务错误码", 9, false);

    private String aliasName;

    private String zhCnName;

    private int sort;

    private boolean enabled;

    DimensionType(String aliasName, String zhCnName, int sort, boolean enabled) {
        this.aliasName = aliasName;
        this.zhCnName = zhCnName;
        this.sort = sort;
        this.enabled = enabled;
    }

    public static DimensionType getType(String name) {
        if (name == null) {
            return UNKNOWN;
        }
        for (DimensionType type : values()) {
            if (type.zhCnName.equals(name) || type.name().equalsIgnoreCase(name)
                    || type.aliasName.equalsIgnoreCase(name)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public static List<DimensionType> getValidValues() {
        List<DimensionType> list = Lists.newArrayList();
        for (DimensionType type : values()) {
            if (type == UNKNOWN) {
                continue;
            }
            list.add(type);
        }
        list.sort(Comparator.comparingInt(DimensionType::getSort));
        return list;
    }

    public static List<DimensionType> getJudgeDimensionList() {
        return Lists.newArrayList(APP, ECI, NET, TERMINAL);
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getZhCnName() {
        return zhCnName;
    }

    public void setZhCnName(String zhCnName) {
        this.zhCnName = zhCnName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
