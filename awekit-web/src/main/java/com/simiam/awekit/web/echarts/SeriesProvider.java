package com.simiam.awekit.web.echarts;

import java.util.Collection;
import java.util.List;

/**
 * <p>Title: SeriesProvider</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/5/6 10:12</p>
 */
public interface SeriesProvider<K, S> {
    List<K> getOrderedSeriesKeyList(Collection<K> rawKeySet);

    String getSeriesName(K key);

    S newSeries(String seriesName);
}
