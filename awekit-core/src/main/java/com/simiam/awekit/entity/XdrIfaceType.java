package com.simiam.awekit.entity;

import com.google.common.base.Strings;

/**
 * <p>Title: XdrIfaceType</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/11/21 15:08</p>
 */
public enum XdrIfaceType {
    UNKNOWN("unknown", false),
    HTTP("http", true),
    S1MME("s1mme", true),
    DNS("dns", true),
    OTHER("other", true),
    HTTP_FACTOR("http_factor", false);

    private String aliasName;

    private boolean enable;

    XdrIfaceType(String aliasName, boolean enable) {
        this.aliasName = aliasName;
        this.enable = enable;
    }

    public static XdrIfaceType getType(String name) {
        if (Strings.isNullOrEmpty(name)) {
            return UNKNOWN;
        }
        for (XdrIfaceType type : values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public static XdrIfaceType deduceIfaceType(IndicatorEnum type) {
        switch (type) {
            case ATTACH_SUC_RATE:
            case ATTACH_DELAY:
            case EPS_SUC_RATE:
            case EPS_DELAY:
                return XdrIfaceType.S1MME;
            case DNS_SUC_RATE:
                return XdrIfaceType.DNS;
            case HTTP_SUC_RATE:
            case HTTP_DOWN_RATE:
            case UP_TRAFFIC:
            case DOWN_TRAFFIC:
            case FIRST_PKG_DELAY:
            case HTTP_LG_PKG_RATE:
            case HTTP_SM_PKG_DELAY:
                return XdrIfaceType.HTTP;
            default:
                return XdrIfaceType.OTHER;
        }
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
