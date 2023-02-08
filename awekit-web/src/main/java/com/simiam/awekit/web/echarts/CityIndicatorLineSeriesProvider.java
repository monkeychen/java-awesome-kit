package com.simiam.awekit.web.echarts;

import com.simiam.awekit.Constants;
import com.simiam.awekit.entity.IndicatorModel;
import com.simiam.awekit.util.CommonUtils;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

/**
 * <p>Title: CityIndicatorLineSeriesProvider</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/7/2 15:00</p>
 */
public class CityIndicatorLineSeriesProvider extends AbstractLineSeriesProvider {

    private boolean isCityLevel = true;

    private boolean includeProvince = true;

    private String provinceName = Constants.FUJIAN;

    public CityIndicatorLineSeriesProvider(boolean isCityLevel, List<IndicatorModel> modelList) {
        this(isCityLevel, true, Constants.FUJIAN, modelList);
    }

    public CityIndicatorLineSeriesProvider(boolean isCityLevel, boolean includeProvince,
            List<IndicatorModel> modelList) {
        this(isCityLevel, includeProvince, Constants.FUJIAN, modelList);
    }

    public CityIndicatorLineSeriesProvider(boolean isCityLevel, boolean includeProvince, String provinceName,
            List<IndicatorModel> modelList) {
        super(modelList);
        this.isCityLevel = isCityLevel;
        this.includeProvince = includeProvince;
        this.provinceName = provinceName;
    }

    @Override
    public List<String> getxAxisMarkList(Collection<String> rawKeySet) {
        if (isCityLevel) {
            List<String> cityNameList = Lists.newArrayList(CommonUtils.CITY_LIST);
            if (includeProvince) {
                cityNameList.add(0, provinceName);
            }
            return cityNameList;
        }
        return Lists.newArrayList(rawKeySet);
    }
}
