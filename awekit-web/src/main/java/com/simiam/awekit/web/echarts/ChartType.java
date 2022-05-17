package com.simiam.awekit.web.echarts;

/**
 * <p>Title: ChartType</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/10/31 14:39</p>
 */
public enum ChartType {
    PIE("pie"),

    LINE("line"),

    BAR("bar"),

    MAP("map"),

    GAUGE("gauge");

    private String value;

    ChartType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
