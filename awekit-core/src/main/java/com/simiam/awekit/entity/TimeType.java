package com.simiam.awekit.entity;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * <p>Title: TimeType</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/12/4 15:12</p>
 */
public enum TimeType {
    UNKNOWN(0, "", "未知"),
    MINUTE_1(1, "", "一分钟"),
    MINUTE_5(5, "5", "五分钟"),
    HOUR_1(60, "60", "一小时"),
    DAY_1(360, "360", "一天");

    private Integer value;

    private String shortName;

    private String zhCnName;

    TimeType(Integer value, String shortName, String zhCnName) {
        this.value = value;
        this.shortName = shortName;
        this.zhCnName = zhCnName;
    }

    public static TimeType getType(int value) {
        for (TimeType type : values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public static TimeType getType(String name) {
        for (TimeType type : values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public static List<TimeType> getValuesExcept(DimensionType dimensionType, TimeType timeType) {
        List<TimeType> timeTypeList = Lists.newArrayList(MINUTE_1, MINUTE_5, HOUR_1, DAY_1);
        timeTypeList.remove(timeType);
        if (DimensionType.ECI.equals(dimensionType)) {
            timeTypeList.remove(MINUTE_1);
        }
        return timeTypeList;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getZhCnName() {
        return zhCnName;
    }

    public void setZhCnName(String zhCnName) {
        this.zhCnName = zhCnName;
    }
}
