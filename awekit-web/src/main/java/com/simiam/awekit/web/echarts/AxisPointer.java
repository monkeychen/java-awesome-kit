package com.simiam.awekit.web.echarts;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>Title: AxisPointer</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/10/31 10:51</p>
 */
public class AxisPointer implements Serializable {
    private static final long serialVersionUID = -8156479960949277077L;

    /**
     * 指示器类型，可选值为：
     * 'line': 直线指示器<br>
     * 'shadow': 阴影指示器<br>
     * 'none': 无指示器<br>
     * 'cross': 十字准星指示器。其实是种简写，表示启用两个正交的轴的 axisPointer。 全局参数不能设置该值。<br>
     */
    private String type = "line";

    private Map<String, Object> label;

    private Map<String, Object> lineStyle;

    private Map<String, Object> shadowStyle;

    private Map<String, Object> crossStyle;

    public AxisPointer() {
    }

    public AxisPointer(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getLabel() {
        return label;
    }

    public void setLabel(Map<String, Object> label) {
        this.label = label;
    }

    public Map<String, Object> getLineStyle() {
        return lineStyle;
    }

    public void setLineStyle(Map<String, Object> lineStyle) {
        this.lineStyle = lineStyle;
    }

    public Map<String, Object> getShadowStyle() {
        return shadowStyle;
    }

    public void setShadowStyle(Map<String, Object> shadowStyle) {
        this.shadowStyle = shadowStyle;
    }

    public Map<String, Object> getCrossStyle() {
        return crossStyle;
    }

    public void setCrossStyle(Map<String, Object> crossStyle) {
        this.crossStyle = crossStyle;
    }
}
