package com.simiam.awekit.web.echarts;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: EchartsOption</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/10/31 8:56</p>
 */
public class EchartsOption<T extends Series> implements Serializable {
    private static final long serialVersionUID = -5750145969736802623L;

    private Title title = new Title();

    private Legend legend = new Legend();

    private ToolTip toolTip = new ToolTip();

    private List<Grid> grid = Lists.newArrayList();

    private List<AxisX> xAxis = Lists.newArrayList();

    private List<AxisY> yAxis = Lists.newArrayList();

    private List<T> series = Lists.newArrayList();

    private Map<String, Object> extra = Maps.newHashMap();

    public EchartsOption() {
    }

    public EchartsOption(Title title) {
        this.title = title;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public ToolTip getToolTip() {
        return toolTip;
    }

    public void setToolTip(ToolTip toolTip) {
        this.toolTip = toolTip;
    }

    public List<Grid> getGrid() {
        return grid;
    }

    public void setGrid(List<Grid> grid) {
        this.grid = grid;
    }

    public List<AxisX> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<AxisX> xAxis) {
        this.xAxis = xAxis;
    }

    public List<AxisY> getyAxis() {
        return yAxis;
    }

    public void setyAxis(List<AxisY> yAxis) {
        this.yAxis = yAxis;
    }

    public List<T> getSeries() {
        return series;
    }

    public void setSeries(List<T> series) {
        this.series = series;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }
}
