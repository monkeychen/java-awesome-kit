package com.simiam.awekit.web.echarts;

import com.simiam.awekit.Constants;
import com.simiam.awekit.util.CommonUtils;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

/**
 * <p>Title: CityLineSeriesProvider</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/6/25 9:35</p>
 */
public class CityLineSeriesProvider implements SeriesProvider<String, LineSeries> {

    private boolean isCityLevel = true;

    private boolean includeProvince = true;

    private String provinceName = Constants.FUJIAN;

    public CityLineSeriesProvider(boolean isCityLevel, boolean includeProvince) {
        this.isCityLevel = isCityLevel;
        this.includeProvince = includeProvince;
    }

    public CityLineSeriesProvider(boolean isCityLevel, boolean includeProvince, String provinceName) {
        this.isCityLevel = isCityLevel;
        this.includeProvince = includeProvince;
        this.provinceName = provinceName;
    }

    @Override
    public List<String> getOrderedSeriesKeyList(Collection<String> rawKeySet) {
        if (isCityLevel) {
            List<String> cityNameList = Lists.newArrayList(CommonUtils.CITY_LIST);
            if (includeProvince) {
                cityNameList.add(0, provinceName);
            }
            return cityNameList;
        }
        return Lists.newArrayList(rawKeySet);
    }

    @Override
    public String getSeriesName(String key) {
        return key;
    }

    @Override
    public LineSeries newSeries(String seriesName) {
        LineSeries series = new LineSeries(seriesName, ChartType.LINE.getValue());
        series.setSmooth(0.3);
        return series;
    }

    public boolean isCityLevel() {
        return isCityLevel;
    }

    public void setCityLevel(boolean cityLevel) {
        isCityLevel = cityLevel;
    }

    public boolean isIncludeProvince() {
        return includeProvince;
    }

    public void setIncludeProvince(boolean includeProvince) {
        this.includeProvince = includeProvince;
    }
}
