package com.simiam.awekit.web.entity;

/**
 * <p>Title: ChartEntity</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/5/6 11:22</p>
 */
public interface ChartEntity<T> {
    T getSeriesName();

    String getAxisMark(String axisFmt);
}
