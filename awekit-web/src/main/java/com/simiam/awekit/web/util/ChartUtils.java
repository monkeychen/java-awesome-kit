package com.simiam.awekit.web.util;

import com.simiam.awekit.web.entity.ChartEntity;
import com.simiam.awekit.entity.GenericChartEntity;
import com.simiam.awekit.entity.IndicatorModel;
import com.simiam.awekit.entity.NameValuePair;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.simiam.awekit.util.BeanUtils;
import com.simiam.awekit.util.CommonUtils;
import com.simiam.awekit.web.echarts.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * <p>Title: ChartUtils</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/1/3 10:41</p>
 */
public abstract class ChartUtils {

    public static <E> EchartsOption<LineSeries> getCityLineOption(String chartTitle,
                                                                  String yAxisName, List<String> timeSeqAxisItemList, String indicatorName,
                                                                  Map<Integer, Map<String, E>> city_timeEntityMap_Map, boolean isIncludeProvince) {
        EchartsOption<LineSeries> lineOption = new EchartsOption<>(new Title(chartTitle));
        lineOption.getExtra().put("xAxisData", timeSeqAxisItemList);
        lineOption.getExtra().put("xAxisLabelInterval", 1);
        lineOption.getExtra().put("yAxisName", yAxisName);

        List<String> seriesNameList = Lists.newArrayList();
        List<Integer> cityIdList = Lists.newArrayList(CommonUtils.getCityIdList());
        if (isIncludeProvince) {
            cityIdList.add(590);
        }
        for (Integer cityId : cityIdList) {
            String cityName = CommonUtils.getCityNameOrDefault(cityId, "福建");
            seriesNameList.add(cityName);
            LineSeries series = new LineSeries(cityName, ChartType.LINE.getValue());
            series.setSmooth(0.3);
            List<Double> dataList = Lists.newArrayList();
            Map<String, E> timeEntityMap = city_timeEntityMap_Map.computeIfAbsent(cityId, k -> Maps.newHashMap());
            for (String minute : timeSeqAxisItemList) {
                E entity = timeEntityMap.get(minute);
                if (entity == null) {
                    dataList.add(0D);
                    continue;
                }
                Field field = ReflectionUtils.findField(entity.getClass(), indicatorName);
                Object value = null;
                if (field != null) {
                    ReflectionUtils.makeAccessible(field);
                    value = ReflectionUtils.getField(field, entity);
                }
                if (value != null) {
                    dataList.add(Double.parseDouble(value.toString()));
                } else {
                    dataList.add(0D);
                }
            }
            series.setData(dataList);
            lineOption.getSeries().add(series);
        }
        lineOption.getLegend().setData(seriesNameList);
        return lineOption;
    }

    public static <E extends ChartEntity<K>, K> EchartsOption<LineSeries> getLineOption(String chartTitle,
                                                                                        String xAxisFmt, String yAxisName, SeriesProvider<K, LineSeries> provider,
                                                                                        List<E> entityList, String indicatorName) {

        return getChartOption(chartTitle, xAxisFmt, yAxisName, provider, entityList, indicatorName);
    }

    public static <E extends ChartEntity<K>, K> EchartsOption<BarSeries> getBarOption(String chartTitle,
                                                                                      String xAxisFmt, String yAxisName, SeriesProvider<K, BarSeries> provider,
                                                                                      List<E> entityList, String indicatorName) {

        return getChartOption(chartTitle, xAxisFmt, yAxisName, provider, entityList, indicatorName);
    }

    public static <E extends ChartEntity<K>, K> EchartsOption<BarSeries> getBarOption(
            String chartTitle, String yAxisName, BarSeriesProvider<K> provider, List<E> entityList) {
        EchartsOption<BarSeries> barOption = new EchartsOption<>(new Title(chartTitle));
        if (CollectionUtils.isEmpty(entityList)) {
            return barOption;
        }
        Map<String, E> entityMap = Maps.newHashMap();
        Set<K> entityKeySet = Sets.newHashSet();
        for (E entity : entityList) {
            K key = entity.getSeriesName();
            entityKeySet.add(key);
            entityMap.put(key.toString(), entity);
        }
        List<String> xAxisMarkList = provider.getxAxisMarkList(entityKeySet);
        List<String> seriesNameList = Lists.newArrayList();
        for (K key : provider.getOrderedSeriesKeyList(entityKeySet)) {
            String seriesName = provider.getSeriesName(key);
            seriesNameList.add(seriesName);
            BarSeries series = provider.newSeries(seriesName);
            List<Double> dataList = Lists.newArrayList();
            for (String xAxisMark : xAxisMarkList) {
                E entity = entityMap.get(xAxisMark);
                if (entity == null) {
                    dataList.add(0D);
                    continue;
                }
                Double value = BeanUtils.getFieldDoubleValue(entity, key.toString());
                dataList.add(value);
            }
            series.setData(dataList);
            barOption.getSeries().add(series);
        }
        barOption.getLegend().setData(seriesNameList);

        barOption.getExtra().put("xAxisData", xAxisMarkList);
        barOption.getExtra().put("xAxisLabelInterval", 1);
        barOption.getExtra().put("yAxisName", yAxisName);
        return barOption;
    }

    public static <E extends ChartEntity<K>, K> EchartsOption<LineSeries> getLineOption(
            String chartTitle, String yAxisName, LineSeriesProvider<K> provider, List<E> entityList) {
        EchartsOption<LineSeries> lineOption = new EchartsOption<>(new Title(chartTitle));
        if (CollectionUtils.isEmpty(entityList)) {
            return lineOption;
        }
        Map<String, E> entityMap = Maps.newHashMap();
        Set<K> entityKeySet = Sets.newHashSet();
        for (E entity : entityList) {
            K key = entity.getSeriesName();
            entityKeySet.add(key);
            entityMap.put(key.toString(), entity);
        }
        List<String> xAxisMarkList = provider.getxAxisMarkList(entityKeySet);
        List<String> seriesNameList = Lists.newArrayList();
        for (K key : provider.getOrderedSeriesKeyList(entityKeySet)) {
            String seriesName = provider.getSeriesName(key);
            seriesNameList.add(seriesName);
            LineSeries series = provider.newSeries(seriesName);
            List<Double> dataList = Lists.newArrayList();
            for (String xAxisMark : xAxisMarkList) {
                E entity = entityMap.get(xAxisMark);
                if (entity == null) {
                    dataList.add(0D);
                    continue;
                }
                Double value = BeanUtils.getFieldDoubleValue(entity, key.toString());
                dataList.add(value);
            }
            series.setData(dataList);
            lineOption.getSeries().add(series);
        }
        lineOption.getLegend().setData(seriesNameList);

        lineOption.getExtra().put("xAxisData", xAxisMarkList);
        lineOption.getExtra().put("xAxisLabelInterval", 1);
        lineOption.getExtra().put("yAxisName", yAxisName);
        return lineOption;
    }

    public static <E extends ChartEntity<K>, K, S extends Series<Double>> EchartsOption<S> getChartOption(
            String chartTitle, String xAxisFmt, String yAxisName, SeriesProvider<K, S> provider,
            List<E> entityList, String indicatorName) {
        EchartsOption<S> lineOption = new EchartsOption<>(new Title(chartTitle));
        if (CollectionUtils.isEmpty(entityList)) {
            return lineOption;
        }

        Map<K, Map<String, E>> series_timeEntityMap_map = Maps.newHashMap();
        Set<K> seriesNameSet = Sets.newHashSet();
        for (E entity : entityList) {
            K seriesName = entity.getSeriesName();
            seriesNameSet.add(seriesName);
            String timeKey = entity.getAxisMark(xAxisFmt);
            Map<String, E> timeEntityMap = series_timeEntityMap_map.computeIfAbsent(seriesName, k -> Maps.newHashMap());
            timeEntityMap.put(timeKey, entity);
        }
        List<String> xAxisMarkList = Lists.newArrayList();
        Map<String, E> timeEntityMap;
        Iterator<Map<String, E>> iterator = series_timeEntityMap_map.values().iterator();
        if (iterator.hasNext()) {
            timeEntityMap = iterator.next();
            if (!CollectionUtils.isEmpty(timeEntityMap)) {
                xAxisMarkList.addAll(timeEntityMap.keySet());
                xAxisMarkList.sort(String::compareTo);
            }
        }

        List<String> seriesNameList = Lists.newArrayList();
        for (K key : provider.getOrderedSeriesKeyList(seriesNameSet)) {
            String seriesName = provider.getSeriesName(key);
            seriesNameList.add(seriesName);
            S series = provider.newSeries(seriesName);
            List<Double> dataList = Lists.newArrayList();
            Map<String, E> tmpTimeEntityMap = series_timeEntityMap_map.computeIfAbsent(key, k -> Maps.newHashMap());
            for (String minute : xAxisMarkList) {
                E entity = tmpTimeEntityMap.get(minute);
                if (entity == null) {
                    dataList.add(0D);
                    continue;
                }
                Double value = BeanUtils.getFieldDoubleValue(entity, indicatorName);
                dataList.add(value);
            }
            series.setData(dataList);
            lineOption.getSeries().add(series);
        }
        lineOption.getLegend().setData(seriesNameList);

        lineOption.getExtra().put("xAxisData", xAxisMarkList);
        lineOption.getExtra().put("xAxisLabelInterval", 1);
        lineOption.getExtra().put("yAxisName", yAxisName);
        return lineOption;
    }

    public static <E extends ChartEntity<String>> Map<String, Object> getMapAndBarOption(List<E> indicatorSetList,
            IndicatorModel indicatorModel, List<NameValuePair<Double>> pairList) {
        Map<String, Object> resultMap = Maps.newHashMap();
        String unit = indicatorModel.getUnit();
        String chartTitle = indicatorModel.getCnName() + "排行(" + unit + ")";

        pairList.sort(Collections.reverseOrder(Comparator.comparingDouble(NameValuePair::getValue)));
        double max = pairList.get(0).getValue();
        double min = pairList.get(pairList.size() - 1).getValue();
        Map<String, Object> pieOption = Maps.newHashMap();
        pieOption.put("visualMapMin", min);
        pieOption.put("visualMapMax", max);
        pieOption.put("unit", unit);
        pieOption.put("data", pairList);
        resultMap.put("mapOption", pieOption);

        EchartsOption<BarSeries> barOption = ChartUtils.getBarOption(chartTitle, unit, new BarSeriesProvider<String>() {
            @Override
            public List<String> getxAxisMarkList(Collection<String> rawKeySet) {
                List<String> cityNameList = Lists.newArrayList();
                for (NameValuePair<Double> pair : pairList) {
                    cityNameList.add(pair.getName());
                }
                return cityNameList;
            }

            @Override
            public List<String> getOrderedSeriesKeyList(Collection<String> rawKeySet) {
                return Lists.newArrayList(indicatorModel.getName());
            }

            @Override
            public String getSeriesName(String key) {
                return indicatorModel.getCnName();
            }

            @Override
            public BarSeries newSeries(String seriesName) {
                return new BarSeries(seriesName, ChartType.BAR.getValue(), "10%");
            }
        }, indicatorSetList);

        barOption.getExtra().put("visualMapMin", min);
        barOption.getExtra().put("visualMapMax", max);
        barOption.getExtra().put("unit", unit);
        resultMap.put("barOption", barOption);

        return resultMap;
    }

    public static <E extends ChartEntity<String>> Map<String, Object> getPieAndBarOption(List<E> indicatorSetList,
            IndicatorModel indicatorModel, List<NameValuePair<Double>> pairList) {
        Map<String, Object> resultMap = Maps.newHashMap();
        String unit = indicatorModel.getUnit();
        String chartTitle = indicatorModel.getCnName() + "排行(" + unit + ")";

        pairList.sort(Collections.reverseOrder(Comparator.comparingDouble(NameValuePair::getValue)));
        List<String> xAxisMarkList = Lists.newArrayList();
        for (NameValuePair<Double> pair : pairList) {
            xAxisMarkList.add(pair.getName());
        }
        double max = pairList.get(0).getValue();
        double min = pairList.get(pairList.size() - 1).getValue();
        EchartsOption<Series<NameValuePair<Double>>> pieOption = new EchartsOption<>(new Title(chartTitle));
        pieOption.getLegend().setData(xAxisMarkList);
        pieOption.getExtra().put("visualMapMin", min);
        pieOption.getExtra().put("visualMapMax", max);
        pieOption.getExtra().put("unit", unit);
        Series<NameValuePair<Double>> pieSeries = new Series<>(indicatorModel.getCnName(), ChartType.PIE.getValue());
        pieSeries.setData(pairList);
        pieOption.getSeries().add(pieSeries);
        resultMap.put("pieOption", pieOption);

        EchartsOption<BarSeries> barOption = ChartUtils.getBarOption(chartTitle, unit, new BarSeriesProvider<String>() {
            @Override
            public List<String> getxAxisMarkList(Collection<String> rawKeySet) {
                List<String> cityNameList = Lists.newArrayList();
                for (NameValuePair<Double> pair : pairList) {
                    cityNameList.add(pair.getName());
                }
                return cityNameList;
            }

            @Override
            public List<String> getOrderedSeriesKeyList(Collection<String> rawKeySet) {
                return Lists.newArrayList(indicatorModel.getName());
            }

            @Override
            public String getSeriesName(String key) {
                return indicatorModel.getCnName();
            }

            @Override
            public BarSeries newSeries(String seriesName) {
                return new BarSeries(seriesName, ChartType.BAR.getValue(), "10%");
            }
        }, indicatorSetList);

        barOption.getExtra().put("visualMapMin", min);
        barOption.getExtra().put("visualMapMax", max);
        barOption.getExtra().put("unit", unit);
        resultMap.put("barOption", barOption);

        return resultMap;
    }

    public static <V extends Number> EchartsOption<Series<NameValuePair<V>>> getPieOption(IndicatorModel indicatorModel,
            List<NameValuePair<V>> pairList) {
        String unit = indicatorModel.getUnit();
        String chartTitle = indicatorModel.getCnName() + "分布(" + unit + ")";

        pairList.sort((o1, o2) -> (int) (o2.getValue().doubleValue() - o1.getValue().doubleValue()));
        List<String> xAxisMarkList = Lists.newArrayList();
        for (NameValuePair<V> pair : pairList) {
            xAxisMarkList.add(pair.getName());
        }
        double max = pairList.get(0).getValue().doubleValue();
        double min = pairList.get(pairList.size() - 1).getValue().doubleValue();
        EchartsOption<Series<NameValuePair<V>>> pieOption = new EchartsOption<>(new Title(chartTitle));
        pieOption.getLegend().setData(xAxisMarkList);
        pieOption.getExtra().put("visualMapMin", min);
        pieOption.getExtra().put("visualMapMax", max);
        pieOption.getExtra().put("unit", unit);
        Series<NameValuePair<V>> pieSeries = new Series<>(indicatorModel.getCnName(), ChartType.PIE.getValue());
        pieSeries.setData(pairList);
        pieOption.getSeries().add(pieSeries);

        return pieOption;
    }

    public static <K> EchartsOption<LineSeries> generateLineOption(
            ChartBasicInfo basicInfo,
            SeriesProvider<K, LineSeries> provider,
            AxisMarkParser<K> axisMarkParser,
            List<GenericChartEntity<K>> entityList,
            String indicatorName) {

        return generateChartOption(basicInfo, provider, axisMarkParser, entityList, indicatorName);
    }

    public static <K> EchartsOption<BarSeries> generateBarOption(
            ChartBasicInfo basicInfo,
            SeriesProvider<K, BarSeries> provider,
            AxisMarkParser<K> axisMarkParser,
            List<GenericChartEntity<K>> entityList,
            String indicatorName) {

        return generateChartOption(basicInfo, provider, axisMarkParser, entityList, indicatorName);
    }

    /**
     * 生成EchartOption实例：更适合生成趋势图（折线图）
     * @param basicInfo 基础信息
     * @param provider 序列核心数据提供者
     * @param axisMarkParser 坐标呈现方式定制化解析器
     * @param entityList 业务数据（GenericChartEntity）实例清单
     * @param indicatorColumn 指标所对应的数据列名
     * @return EchartOption实例
     */
    public static <K, S extends Series<Double>> EchartsOption<S> generateChartOption(
            ChartBasicInfo basicInfo, SeriesProvider<K, S> provider, AxisMarkParser<K> axisMarkParser,
            List<GenericChartEntity<K>> entityList, String indicatorColumn) {
        EchartsOption<S> chartOption = new EchartsOption<>(new Title(basicInfo.getTitle()));
        if (CollectionUtils.isEmpty(entityList)) {
            return chartOption;
        }

        Map<K, Map<String, GenericChartEntity<K>>> series_axisEntityMap_map = Maps.newHashMap();
        Set<K> seriesNameSet = Sets.newHashSet();
        for (GenericChartEntity<K> entity : entityList) {
            K seriesName = entity.getSeriesName(null);
            seriesNameSet.add(seriesName);
            String axisKey = axisMarkParser.parse(entity, basicInfo.getAxisColumnName(), basicInfo.getAxisFmt());
            Map<String, GenericChartEntity<K>> axisEntityMap = series_axisEntityMap_map.computeIfAbsent(seriesName, k -> Maps.newHashMap());
            axisEntityMap.put(axisKey, entity);
        }
        List<String> xAxisMarkList = Lists.newArrayList();
        Map<String, GenericChartEntity<K>> entityMap;
        Iterator<Map<String, GenericChartEntity<K>>> iterator = series_axisEntityMap_map.values().iterator();
        if (iterator.hasNext()) {
            entityMap = iterator.next();
            if (!CollectionUtils.isEmpty(entityMap)) {
                xAxisMarkList.addAll(entityMap.keySet());
                xAxisMarkList.sort(String::compareTo);
            }
        }

        List<String> seriesNameList = Lists.newArrayList();
        for (K key : provider.getOrderedSeriesKeyList(seriesNameSet)) {
            String seriesName = provider.getSeriesName(key);
            seriesNameList.add(seriesName);
            S series = provider.newSeries(seriesName);
            List<Double> dataList = Lists.newArrayList();
            Map<String, GenericChartEntity<K>> tmpAxisEntityMap = series_axisEntityMap_map.computeIfAbsent(key, k -> Maps.newHashMap());
            for (String minute : xAxisMarkList) {
                GenericChartEntity<K> entity = tmpAxisEntityMap.get(minute);
                if (entity == null) {
                    dataList.add(0D);
                    continue;
                }
                Object valObj = entity.getValue(indicatorColumn);
                if (valObj == null || !NumberUtils.isNumber(valObj.toString())) {
                    continue;
                }
                Double value = Double.parseDouble(valObj.toString());
                dataList.add(value);
            }
            series.setData(dataList);
            chartOption.getSeries().add(series);
        }
        chartOption.getLegend().setData(seriesNameList);

        chartOption.getExtra().put("xAxisData", axisMarkParser.convertXAxisMarkList(xAxisMarkList));
        chartOption.getExtra().put("xAxisLabelInterval", 1);
        chartOption.getExtra().put("yAxisName", basicInfo.getyAxisName());
        return chartOption;
    }

    /**
     * 通用柱状图
     * @param basicInfo basicInfo
     * @param provider provider
     * @param axisMarkParser axisMarkParser
     * @param entityList entityList
     * @return BarOption
     */
    public static EchartsOption<BarSeries> generateBarOption(
            ChartBasicInfo basicInfo,
            BarSeriesProvider<String> provider,
            AxisMarkParser<String> axisMarkParser,
            List<GenericChartEntity<String>> entityList) {
        EchartsOption<BarSeries> barOption = new EchartsOption<>(new Title(basicInfo.getTitle()));
        if (CollectionUtils.isEmpty(entityList)) {
            return barOption;
        }
        Map<String, GenericChartEntity<String>> entityMap = Maps.newHashMap();
        Set<String> axisMarkSet = Sets.newHashSet();
        for (GenericChartEntity<String> entity : entityList) {
            String axisMark = axisMarkParser.parse(entity, basicInfo.getAxisColumnName(), basicInfo.getAxisFmt());
            axisMarkSet.add(axisMark);
            entityMap.put(axisMark, entity);
        }
        List<String> xAxisMarkList = provider.getxAxisMarkList(axisMarkSet);
        List<String> seriesNameList = Lists.newArrayList();
        for (String key : provider.getOrderedSeriesKeyList(axisMarkSet)) {
            String seriesName = provider.getSeriesName(key);
            seriesNameList.add(seriesName);
            BarSeries series = provider.newSeries(seriesName);
            List<Double> dataList = Lists.newArrayList();
            for (String xAxisMark : xAxisMarkList) {
                GenericChartEntity<String> entity = entityMap.get(xAxisMark);
                if (entity == null) {
                    dataList.add(0D);
                    continue;
                }
                Object valObj = entity.getValue(key);
                if (valObj == null || !NumberUtils.isNumber(valObj.toString())) {
                    continue;
                }
                Double value = Double.parseDouble(valObj.toString());
                dataList.add(value);
            }
            series.setData(dataList);
            barOption.getSeries().add(series);
        }
        barOption.getLegend().setData(seriesNameList);

        barOption.getExtra().put("xAxisData", axisMarkParser.convertXAxisMarkList(xAxisMarkList));
        barOption.getExtra().put("xAxisLabelInterval", 1);
        barOption.getExtra().put("yAxisName", basicInfo.getyAxisName());
        return barOption;
    }

    /**
     * 针对同一个实体中多个属性的统计单位都一样，将该实体多个属性的变化趋势都在放同一个折线图上，可用该方法。
     * @param basicInfo basicInfo
     * @param provider provider
     * @param axisMarkParser 时间坐标解析器
     * @param entityList entityList
     * @return LineOption
     */
    public static EchartsOption<LineSeries> generateLineOption(
            ChartBasicInfo basicInfo,
            LineSeriesProvider<String> provider,
            AxisMarkParser<String> axisMarkParser,
            List<GenericChartEntity<String>> entityList) {
        EchartsOption<LineSeries> lineOption = new EchartsOption<>(new Title(basicInfo.getTitle()));
        if (CollectionUtils.isEmpty(entityList)) {
            return lineOption;
        }
        Map<String, GenericChartEntity<String>> entityMap = Maps.newHashMap();
        Set<String> axisMarkSet = Sets.newHashSet();
        for (GenericChartEntity<String> entity : entityList) {
            String axisMark = axisMarkParser.parse(entity, basicInfo.getAxisColumnName(), basicInfo.getAxisFmt());
            axisMarkSet.add(axisMark);
            entityMap.put(axisMark, entity);
        }
        List<String> xAxisMarkList = provider.getxAxisMarkList(axisMarkSet);
        List<String> seriesNameList = Lists.newArrayList();
        for (String key : provider.getOrderedSeriesKeyList(axisMarkSet)) {
            String seriesName = provider.getSeriesName(key);
            seriesNameList.add(seriesName);
            LineSeries series = provider.newSeries(seriesName);
            List<Double> dataList = Lists.newArrayList();
            for (String xAxisMark : xAxisMarkList) {
                GenericChartEntity<String> entity = entityMap.get(xAxisMark);
                if (entity == null) {
                    dataList.add(0D);
                    continue;
                }
                Object valObj = entity.getValue(key);
                if (valObj == null || !NumberUtils.isNumber(valObj.toString())) {
                    continue;
                }
                Double value = Double.parseDouble(valObj.toString());
                dataList.add(value);
            }
            series.setData(dataList);
            lineOption.getSeries().add(series);
        }
        lineOption.getLegend().setData(seriesNameList);

        lineOption.getExtra().put("xAxisData", axisMarkParser.convertXAxisMarkList(xAxisMarkList));
        lineOption.getExtra().put("xAxisLabelInterval", 1);
        lineOption.getExtra().put("yAxisName", basicInfo.getyAxisName());
        return lineOption;
    }
}
