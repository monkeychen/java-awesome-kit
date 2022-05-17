package com.simiam.awekit.web.echarts;

import java.util.Collection;
import java.util.List;

/**
 * <p>Title: LineSeriesProvider</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/5/20 17:57</p>
 */
public interface LineSeriesProvider<K> extends SeriesProvider<K, LineSeries> {

    List<String> getxAxisMarkList(Collection<K> rawKeySet);
}
