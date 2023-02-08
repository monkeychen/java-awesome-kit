package com.simiam.awekit.web.echarts;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: Series</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/10/31 10:18</p>
 */
public class Series<T> implements Serializable {
    private static final long serialVersionUID = 3973983486568729532L;

    private String name;

    private String type;

    private int xAxisIndex = 0;

    private int yAxisIndex = 0;

    private List<T> data = Lists.newArrayList();

    public Series() {
    }

    public Series(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Series(String name, String type, List<T> data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getxAxisIndex() {
        return xAxisIndex;
    }

    public void setxAxisIndex(int xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
    }

    public int getyAxisIndex() {
        return yAxisIndex;
    }

    public void setyAxisIndex(int yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
    }
}
