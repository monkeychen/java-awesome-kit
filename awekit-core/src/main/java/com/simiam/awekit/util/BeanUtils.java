package com.simiam.awekit.util;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * <p>Title: BeanUtils</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/4/28 18:04</p>
 */
public abstract class BeanUtils extends org.springframework.beans.BeanUtils {
    public static Object getFieldValue(Object entity, String fieldName) {
        Field field = ReflectionUtils.findField(entity.getClass(), fieldName);
        Object value = null;
        if (field != null) {
            ReflectionUtils.makeAccessible(field);
            value = ReflectionUtils.getField(field, entity);
        }
        return value;
    }

    public static Double getFieldDoubleValue(Object entity, String fieldName) {
        Field field = ReflectionUtils.findField(entity.getClass(), fieldName);
        Object value = null;
        if (field != null) {
            ReflectionUtils.makeAccessible(field);
            value = ReflectionUtils.getField(field, entity);
        }
        if (value != null && NumberUtils.isNumber(value.toString())) {
            return Double.parseDouble(value.toString());
        }
        return null;
    }

    public static void setFieldValue(Object entity, String fieldName, Object value) {
        Field field = ReflectionUtils.findField(entity.getClass(), fieldName);
        if (field != null && value != null) {
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, entity, value);
        }
    }

    public static void setFieldDoubleValue(Object entity, String fieldName, Double value) {
        setFieldValue(entity, fieldName, value);
    }
}
