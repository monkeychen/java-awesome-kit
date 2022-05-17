package com.simiam.awekit.web.echarts;

/**
 * <p>Title: ChartBasicInfo</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2020</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2020/3/5 11:02 下午</p>
 */
public class ChartBasicInfo {
    String title;

    String axisColumnName;

    String axisFmt;

    String yAxisName;

    public ChartBasicInfo(String title, String axisColumnName, String axisFmt, String yAxisName) {
        this.title = title;
        this.axisColumnName = axisColumnName;
        this.axisFmt = axisFmt;
        this.yAxisName = yAxisName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAxisColumnName() {
        return axisColumnName;
    }

    public void setAxisColumnName(String axisColumnName) {
        this.axisColumnName = axisColumnName;
    }

    public String getAxisFmt() {
        return axisFmt;
    }

    public void setAxisFmt(String axisFmt) {
        this.axisFmt = axisFmt;
    }

    public String getyAxisName() {
        return yAxisName;
    }

    public void setyAxisName(String yAxisName) {
        this.yAxisName = yAxisName;
    }
}
