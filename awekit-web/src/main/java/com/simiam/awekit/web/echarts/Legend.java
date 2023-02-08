package com.simiam.awekit.web.echarts;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: Legend</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/10/31 9:10</p>
 */
public class Legend implements Serializable {
    private static final long serialVersionUID = -7803851995539864124L;

    /**
     * 值为'left', 'center', 'right'
     */
    private String left = "center";

    /**
     * 值为'top', 'middle', 'bottom'
     */
    private String top = "bottom";

    /**
     * 'single' 或者 'multiple'
     */
    private String selectedMode = "multiple";

    private List<String> data = Lists.newArrayList();

    public Legend() {
    }

    public Legend(String selectedMode, List<String> data) {
        this.selectedMode = selectedMode;
        this.data = data;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getSelectedMode() {
        return selectedMode;
    }

    public void setSelectedMode(String selectedMode) {
        this.selectedMode = selectedMode;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
