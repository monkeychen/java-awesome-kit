package com.simiam.awekit.web.echarts;

import java.io.Serializable;

/**
 * <p>Title: ToolTip</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/10/31 10:42</p>
 */
public class ToolTip implements Serializable {
    private static final long serialVersionUID = -7482344215711102154L;

    /**
     * 触发类型，可选值：
     * 'item': 数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
     * 'axis': 坐标轴触发，主要在柱状图，折线图等会使用类目轴的图表中使用。
     * 'none': 什么都不触发。
     */
    private String trigger = "item";

    private AxisPointer axisPointer;

    private String formatter;

    public ToolTip() {
    }

    public ToolTip(String trigger) {
        this.trigger = trigger;
    }

    public ToolTip(String trigger, AxisPointer axisPointer, String formatter) {
        this.trigger = trigger;
        this.axisPointer = axisPointer;
        this.formatter = formatter;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public AxisPointer getAxisPointer() {
        return axisPointer;
    }

    public void setAxisPointer(AxisPointer axisPointer) {
        this.axisPointer = axisPointer;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }
}
