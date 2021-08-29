package com.simiam.awekit.web.echarts;

import com.simiam.awekit.entity.IndicatorModel;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>Title: AbstractBarSeriesProvider</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/7/2 15:00</p>
 */
public abstract class AbstractBarSeriesProvider implements BarSeriesProvider<String> {

    protected List<IndicatorModel> modelList;

    protected Map<String, IndicatorModel> modelMap;

    public AbstractBarSeriesProvider(List<IndicatorModel> modelList) {
        this.modelList = modelList;
        init();
    }

    @Override
    public List<String> getOrderedSeriesKeyList(Collection<String> rawKeySet) {
        List<String> seriesNameList = Lists.newArrayList();
        for (IndicatorModel model : modelList) {
            seriesNameList.add(model.getName());
        }
        return seriesNameList;
    }

    @Override
    public String getSeriesName(String key) {
        return modelMap.get(key).getCnName();
    }

    @Override
    public BarSeries newSeries(String seriesName) {
        return new BarSeries(seriesName, ChartType.BAR.getValue(), "10%");
    }

    private void init() {
        modelMap = modelList.stream().collect(Collectors.toMap(IndicatorModel::getName, (model -> model)));
    }
}
