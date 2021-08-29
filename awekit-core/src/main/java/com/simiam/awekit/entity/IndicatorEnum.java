package com.simiam.awekit.entity;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * <p>Title: IndicatorEnum</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/11/5 11:15</p>
 */
public enum IndicatorEnum {
    UNKNOWN("未知指标", "unknown", false, "", ""),
    ATTACH_SUC_RATE("ATTACH成功率", "attachSucRate", true, "%", "attach.png"),
    ATTACH_DELAY("ATTACH时延", "attachDelay", false, "ms", "iot.png"),
    EPS_SUC_RATE("EPS成功率", "epsSucRate", false, "%", "ratio_50.png"),
    EPS_DELAY("EPS时延", "epsDelay", false, "ms", "iot.png"),
    DNS_SUC_RATE("DNS成功率", "dnsSucRate", false, "%", "iot.png"),
    TCP_CORE_SUC_RATE("TCP核心成功率", "tcpCoreSucRate", false, "%", "wifi.png"),
    TCP_WX_SUC_RATE("TCP无线成功率", "tcpWxSucRate", false, "%", "wifi.png"),
    HTTP_SUC_RATE("HTTP业务成功率", "httpSucRate", true, "%", "web.png"),
    HTTP_DOWN_RATE("HTTP下载速率", "httpDownRate", true, "kbps", "web.png"),
    UP_TRAFFIC("上行流量", "upTraffic", false, "GB", "upload.png"),
    DOWN_TRAFFIC("下行流量", "downTraffic", false, "GB", "download.png"),
    FIRST_PKG_DELAY("首包响应时延", "firstPkgDelay", false, "ms", "iot.png"),
    HTTP_LG_PKG_RATE("HTTP大包速率", "httpLgPkgRate", false, "kbps", "wifi.png"),
    HTTP_SM_PKG_DELAY("HTTP小包时延", "httpSmPkgDelay", false, "ms", "iot.png");

    private String zhCnName;

    private String aliasName;

    private boolean isEnabled;

    private String unit;

    private String iconPath;

    IndicatorEnum(String zhCnName, String aliasName, boolean isEnabled, String unit, String iconPath) {
        this.zhCnName = zhCnName;
        this.aliasName = aliasName;
        this.isEnabled = isEnabled;
        this.unit = unit;
        this.iconPath = iconPath;
    }

    public String getZhCnName() {
        return zhCnName;
    }

    public void setZhCnName(String zhCnName) {
        this.zhCnName = zhCnName;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public static IndicatorEnum getIndicatorType(String name) {
        if (name == null) {
            return UNKNOWN;
        }
        for (IndicatorEnum type : values()) {
            if (type.zhCnName.equals(name) || type.name().equalsIgnoreCase(name)
                    || type.aliasName.equalsIgnoreCase(name)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public static List<IndicatorEnum> getValidValues() {
        List<IndicatorEnum> list = Lists.newArrayList();
        for (IndicatorEnum type : values()) {
            if (type == UNKNOWN) {
                continue;
            }
            list.add(type);
        }
        return list;
    }

    public static List<IndicatorEnum> getFactorIndicatorList() {
        return Lists.newArrayList(HTTP_SUC_RATE, HTTP_LG_PKG_RATE, HTTP_SM_PKG_DELAY);
    }

    public static List<IndicatorEnum> getHttpTypeList() {
        return Lists.newArrayList(HTTP_SUC_RATE, HTTP_DOWN_RATE, UP_TRAFFIC, DOWN_TRAFFIC, FIRST_PKG_DELAY, HTTP_LG_PKG_RATE, HTTP_SM_PKG_DELAY);
    }

    public static List<IndicatorEnum> getS1mmeTypeList() {
        return Lists.newArrayList(ATTACH_SUC_RATE, ATTACH_DELAY, EPS_SUC_RATE, EPS_DELAY);
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
}
