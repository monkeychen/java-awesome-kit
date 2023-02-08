package com.simiam.awekit;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * <p>Title: Awekit</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/5/17 14:40</p>
 */
public abstract class Awekit {

    public static String getEnvProperty(String name, String def) {
        return System.getProperty(name, def);
    }

    public static boolean getEnvProperty(String name, boolean def) {
        return BooleanUtils.toBoolean(System.getProperty(name, def + ""));
    }

    public static Integer getEnvProperty(String name, Integer def) {
        String str = System.getProperty(name);
        if (!NumberUtils.isNumber(str)) {
            return def;
        }
        return NumberUtils.toInt(str);
    }

    public static Long getEnvProperty(String name, Long def) {
        String str = System.getProperty(name);
        if (!NumberUtils.isNumber(str)) {
            return def;
        }
        return NumberUtils.toLong(str);
    }

    public static Short getEnvProperty(String name, Short def) {
        String str = System.getProperty(name);
        if (!NumberUtils.isNumber(str)) {
            return def;
        }
        return NumberUtils.toShort(str);
    }

    public static Byte getEnvProperty(String name, Byte def) {
        String str = System.getProperty(name);
        if (!NumberUtils.isNumber(str)) {
            return def;
        }
        return NumberUtils.toByte(str);
    }

    public static Double getEnvProperty(String name, Double def) {
        String str = System.getProperty(name);
        if (!NumberUtils.isNumber(str)) {
            return def;
        }
        return NumberUtils.toDouble(str);
    }

    public static Float getEnvProperty(String name, Float def) {
        String str = System.getProperty(name);
        if (!NumberUtils.isNumber(str)) {
            return def;
        }
        return NumberUtils.toFloat(str);
    }
}
