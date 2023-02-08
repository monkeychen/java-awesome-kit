package com.simiam.awekit.web.echarts;

import java.io.Serializable;

/**
 * <p>Title: Title</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/10/31 9:03</p>
 */
public class Title implements Serializable {
    private static final long serialVersionUID = -5171492520242367367L;

    private String text;

    /**
     * 值为'left', 'center', 'right'
     */
    private String left = "center";

    /**
     * 值为'top', 'middle', 'bottom'
     */
    private String top = "top";

    public Title() {
    }

    public Title(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
}
