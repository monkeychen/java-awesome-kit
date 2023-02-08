package com.simiam.awekit.util;

import java.math.BigDecimal;

/**
 * <p>Title: NumberUtils</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2020</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2020/6/16 21:07</p>
 */
public abstract class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {

    public static double doubleValue(Object object) {
        if (object instanceof BigDecimal) {
            BigDecimal decimal = (BigDecimal) object;
            return decimal.doubleValue();
        }
        if (object == null || !isNumber(object.toString())) {
            return 0;
        }
        return toDouble(object.toString(), 0);
    }

    public static double doubleValue(Object object, Integer precision) {
        if (object == null || !isNumber(object.toString())) {
            return 0;
        }
        return CommonUtils.round(toDouble(object.toString()), precision);
    }

    public static int intValue(Object object) {
        if (object instanceof BigDecimal) {
            BigDecimal decimal = (BigDecimal) object;
            return decimal.intValue();
        }
        if (object == null || !isNumber(object.toString())) {
            return 0;
        }
        return toInt(object.toString());
    }
}
