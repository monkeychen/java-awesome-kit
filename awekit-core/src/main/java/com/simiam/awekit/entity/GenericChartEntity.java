package com.simiam.awekit.entity;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <p>Title: GenericChartEntity</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2020</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2020/3/5 10:26 下午</p>
 */
public class GenericChartEntity<T> {
    private Map<String, Object> valueMap;

    public GenericChartEntity() {
        this.valueMap = Maps.newHashMap();
    }

    public GenericChartEntity(Map<String, Object> valueMap) {
        this.valueMap = valueMap;
    }

    @SuppressWarnings("unchecked")
    public T getSeriesName(T defVal) {
        if (valueMap == null) {
            return null;
        }
        return (T) valueMap.getOrDefault("series_name", defVal);
    }

    public Object getValue(String columnName) {
        if (valueMap == null) {
            return null;
        }
        return valueMap.get(columnName);
    }

    public Map<String, Object> getValueMap() {
        return valueMap;
    }

    public void setValueMap(Map<String, Object> valueMap) {
        this.valueMap = valueMap;
    }
}
