package com.simiam.awekit.web.echarts;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: MapSeries</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/1/29 13:26</p>
 */
public class MapSeries<T> extends Series<T> {
    private static final long serialVersionUID = -949372367592811197L;

    private String mapType = "china";

    private boolean roam = true;

    private Double zoom = 1D;

    private Map<String, Object> label = Maps.newHashMap();

    private Map<String, Object> itemStyle = Maps.newHashMap();

    public MapSeries(String name, String mapType, Double zoom) {
        super(name, ChartType.MAP.getValue());
        this.mapType = mapType;
        this.zoom = zoom;
    }

    public MapSeries(String name, String mapType, Double zoom, List<T> data) {
        super(name, ChartType.MAP.getValue(), data);
        this.mapType = mapType;
        this.zoom = zoom;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    public boolean isRoam() {
        return roam;
    }

    public void setRoam(boolean roam) {
        this.roam = roam;
    }

    public Double getZoom() {
        return zoom;
    }

    public void setZoom(Double zoom) {
        this.zoom = zoom;
    }

    public Map<String, Object> getLabel() {
        return label;
    }

    public void setLabel(Map<String, Object> label) {
        this.label = label;
    }

    public Map<String, Object> getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(Map<String, Object> itemStyle) {
        this.itemStyle = itemStyle;
    }
}
