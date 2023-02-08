package com.simiam.awekit.web.echarts;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: BarSeries</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/10/31 15:24</p>
 */
public class BarSeries extends Series<Double> {
    private static final long serialVersionUID = 1543448374723325781L;

    private String barGap;

    private String stack;

    private Map<String, Object> label = Maps.newHashMap();

    public BarSeries() {

    }

    public BarSeries(String barGap) {
        this.barGap = barGap;
    }

    public BarSeries(String name, String type, String barGap) {
        super(name, type);
        this.barGap = barGap;
    }

    public BarSeries(String name, String type, List<Double> data, String barGap) {
        super(name, type, data);
        this.barGap = barGap;
    }

    public String getBarGap() {
        return barGap;
    }

    public void setBarGap(String barGap) {
        this.barGap = barGap;
    }

    public Map<String, Object> getLabel() {
        return label;
    }

    public void setLabel(Map<String, Object> label) {
        this.label = label;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }
}
