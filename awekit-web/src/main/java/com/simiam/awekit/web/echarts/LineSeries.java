package com.simiam.awekit.web.echarts;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: LineSeries</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/10/31 15:42</p>
 */
public class LineSeries extends Series<Double> {
    private static final long serialVersionUID = 2085581604676082508L;

    private Map<String, Object> areaStyle;

    private Map<String, Object> label = Maps.newHashMap();

    private double smooth = 0;

    /**
     * ECharts提供的标记类型包括 'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow', 'none'
     */
    private String symbol = "circle";

    public LineSeries() {

    }

    public LineSeries(Map<String, Object> areaStyle) {
        this.areaStyle = areaStyle;
    }

    public LineSeries(String name, String type) {
        super(name, type);
    }

    public LineSeries(String name, String type, Map<String, Object> areaStyle) {
        super(name, type);
        this.areaStyle = areaStyle;
    }

    public LineSeries(String name, String type, List<Double> data, Map<String, Object> areaStyle) {
        super(name, type, data);
        this.areaStyle = areaStyle;
    }

    public Map<String, Object> getAreaStyle() {
        return areaStyle;
    }

    public void setAreaStyle(Map<String, Object> areaStyle) {
        this.areaStyle = areaStyle;
    }

    public double getSmooth() {
        return smooth;
    }

    public void setSmooth(double smooth) {
        this.smooth = smooth;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Map<String, Object> getLabel() {
        return label;
    }

    public void setLabel(Map<String, Object> label) {
        this.label = label;
    }
}
