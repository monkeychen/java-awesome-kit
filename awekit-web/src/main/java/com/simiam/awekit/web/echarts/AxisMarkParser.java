package com.simiam.awekit.web.echarts;

import com.simiam.awekit.entity.GenericChartEntity;
import com.simiam.awekit.util.DateUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: AxisMarkParser</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2020</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2020/3/5 10:39 下午</p>
 */
public interface AxisMarkParser<T> {
    default String parse(GenericChartEntity<T> entity, String axisColumnName, String fmtPattern) {
        Object columnVal = entity.getValue(axisColumnName);
        if (columnVal == null) {
            return null;
        }
        String strVal = columnVal.toString();
        if ((columnVal instanceof Number)) {
            if (strVal.length() == 8) {
                Date tmpDate = DateUtils.parseInt(((Number) columnVal).intValue(), DateUtils.yyyyMMdd);
                return DateFormatUtils.format(tmpDate, fmtPattern);
            } else {
                return strVal;
            }
        } else if (columnVal instanceof Date) {
            return DateFormatUtils.format((Date) columnVal, fmtPattern);
        } else {
            return String.format(fmtPattern, strVal);
        }
    }

    default T genericParse(GenericChartEntity<T> entity, String axisColumnName, String fmtPattern) {
        return entity.getSeriesName(null);
    }

    default List<String> convertXAxisMarkList(List<String> xAxisMarkList) {
        return xAxisMarkList;
    }
}
