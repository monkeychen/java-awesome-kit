package com.simiam.awekit.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.keyvalue.DefaultKeyValue;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: CommonUtils</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/10/19 下午11:26</p>
 */
public abstract class CommonUtils {
    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);
    public static final String DEFAULT_DATE_FMT = "yyyy-MM-dd HH:mm";

    public static final String DEFAULT_DATE_VALUE_FMT = "yyyyMMddHHmm";

    public static Map<Integer, String> CODE_NAME_MAP = Maps.newHashMap();

    public static Map<String, Integer> NAME_CODE_MAP = Maps.newHashMap();

    public static Integer[] CITY_ID_ARRAY = new Integer[]{591, 592, 593, 594, 595, 596, 597, 598, 599};

    public static List<String> CITY_LIST = Lists.newArrayList();

    public static List<DefaultKeyValue<Integer, String>> CITY_CODE_NAME_LIST = Lists.newArrayList();

    static {
        CODE_NAME_MAP.put(591, "福州");
        CODE_NAME_MAP.put(592, "厦门");
        CODE_NAME_MAP.put(593, "宁德");
        CODE_NAME_MAP.put(594, "莆田");
        CODE_NAME_MAP.put(595, "泉州");
        CODE_NAME_MAP.put(596, "漳州");
        CODE_NAME_MAP.put(597, "龙岩");
        CODE_NAME_MAP.put(598, "三明");
        CODE_NAME_MAP.put(599, "南平");
        CODE_NAME_MAP.put(590, "福建");

        NAME_CODE_MAP.put("福州", 591);
        NAME_CODE_MAP.put("厦门", 592);
        NAME_CODE_MAP.put("宁德", 593);
        NAME_CODE_MAP.put("莆田", 594);
        NAME_CODE_MAP.put("泉州", 595);
        NAME_CODE_MAP.put("漳州", 596);
        NAME_CODE_MAP.put("龙岩", 597);
        NAME_CODE_MAP.put("三明", 598);
        NAME_CODE_MAP.put("南平", 599);
        NAME_CODE_MAP.put("福建", 590);

        CITY_LIST.add("福州");
        CITY_LIST.add("厦门");
        CITY_LIST.add("宁德");
        CITY_LIST.add("莆田");
        CITY_LIST.add("泉州");
        CITY_LIST.add("漳州");
        CITY_LIST.add("龙岩");
        CITY_LIST.add("三明");
        CITY_LIST.add("南平");

        CITY_CODE_NAME_LIST.add(new DefaultKeyValue<>(590, "全省"));
        CITY_CODE_NAME_LIST.add(new DefaultKeyValue<>(591, "福州"));
        CITY_CODE_NAME_LIST.add(new DefaultKeyValue<>(592, "厦门"));
        CITY_CODE_NAME_LIST.add(new DefaultKeyValue<>(593, "宁德"));
        CITY_CODE_NAME_LIST.add(new DefaultKeyValue<>(594, "莆田"));
        CITY_CODE_NAME_LIST.add(new DefaultKeyValue<>(595, "泉州"));
        CITY_CODE_NAME_LIST.add(new DefaultKeyValue<>(596, "漳州"));
        CITY_CODE_NAME_LIST.add(new DefaultKeyValue<>(597, "龙岩"));
        CITY_CODE_NAME_LIST.add(new DefaultKeyValue<>(598, "三明"));
        CITY_CODE_NAME_LIST.add(new DefaultKeyValue<>(599, "南平"));
    }

    @Deprecated
    public static Map<Integer, String> getCityMap() {
        Map<Integer, String> cityTable = new HashMap<>();
        cityTable.put(591, "福州市");
        cityTable.put(592, "厦门市");
        cityTable.put(593, "宁德市");
        cityTable.put(594, "莆田市");
        cityTable.put(595, "泉州市");
        cityTable.put(596, "漳州市");
        cityTable.put(597, "龙岩市");
        cityTable.put(598, "三明市");
        cityTable.put(599, "南平市");
        cityTable.put(590, "福建省");
        return cityTable;
    }

    @Deprecated
    public static Map<Integer, String> getCityIdNameMap() {
        Map<Integer, String> cityTable = Maps.newHashMap();
        cityTable.put(591, "福州");
        cityTable.put(592, "厦门");
        cityTable.put(593, "宁德");
        cityTable.put(594, "莆田");
        cityTable.put(595, "泉州");
        cityTable.put(596, "漳州");
        cityTable.put(597, "龙岩");
        cityTable.put(598, "三明");
        cityTable.put(599, "南平");
        return cityTable;
    }

    public static List<String> getCityList() {
        return CITY_LIST;
    }

    public static List<Integer> getCityIdList() {
        return Lists.newArrayList(CITY_ID_ARRAY);
    }

    public static Integer getCityCode(String cityName) {
        return NAME_CODE_MAP.getOrDefault(cityName, 590);
    }

    public static String getCityName(Integer cityCode) {
        return CODE_NAME_MAP.getOrDefault(cityCode, "福建");
    }

    public static String getCityName(Integer cityCode, String suffix) {
        return CODE_NAME_MAP.getOrDefault(cityCode, "福建") + suffix;
    }

    public static String getCityNameOrDefault(Integer cityCode, String defName) {
        return CODE_NAME_MAP.getOrDefault(cityCode, defName);
    }

    public static Date add(final Date date, final TimeUnit unit, final int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        int calendarField;
        switch (unit) {
            case MILLISECONDS:
                calendarField = Calendar.MILLISECOND;
                break;
            case SECONDS:
                calendarField = Calendar.SECOND;
                break;
            case MINUTES:
                calendarField = Calendar.MINUTE;
                break;
            case HOURS:
                calendarField = Calendar.HOUR_OF_DAY;
                break;
            case DAYS:
                calendarField = Calendar.DAY_OF_MONTH;
                break;
            default:
                calendarField = Calendar.SECOND;
        }
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    public static List<Double> generateRandomNumbers(int size) {
        List<Double> list = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            list.add(Math.round(66600 * (Math.random() + 1)) / 100.0);
        }
        return list;
    }

    public static String formatToHHmm(Date date) {
        return DateFormatUtils.format(date, "HH:mm");
    }

    public static double round(double originalNum, int fractionDigits) {
//        DecimalFormat df = new DecimalFormat("#.00");
//        System.out.println(df.format(originalNum));
        if (originalNum == 0) {
            return originalNum;
        }
        BigDecimal bg = new BigDecimal(originalNum);
        return bg.setScale(fractionDigits, RoundingMode.HALF_UP).doubleValue();
    }

    public static String formatPercent(Number originalNum, int fractionDigits) {
        return formatPercent(originalNum, 1, fractionDigits, Locale.CHINA);
    }

    public static List<String> generateTimeSequenceList(Date now, TimeUnit unit, int step, int size) {
        List<String> list = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            Date tmp = CommonUtils.add(now, unit, -step * i);
            String fmt = "HH:mm";
            if (TimeUnit.HOURS.equals(unit)) {
                fmt = "MM/dd HH";
            } else if (TimeUnit.DAYS.equals(unit)) {
                fmt = "yyyy/MM/dd";
            }
            list.add(0, DateFormatUtils.format(tmp, fmt));
        }
        return list;
    }

    public static List<String> generateTimeSequenceList(Date now, TimeUnit unit, int step, int size, String fmt) {
        List<String> list = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            Date tmp = CommonUtils.add(now, unit, -step * i);
            list.add(0, DateFormatUtils.format(tmp, fmt));
        }
        return list;
    }

    public static Date parseDate(Long dateValue, String fmt) {
        try {
            return DateUtils.parseDate(dateValue.toString(), fmt);
        } catch (ParseException e) {
            logger.error("Fail to parse date!", e);
        }
        return new Date();
    }

    public static String formatLongToDate(Long dateValue, String valueFmt) {
        Date date = parseDate(dateValue, valueFmt);
        return DateFormatUtils.format(date, DEFAULT_DATE_FMT);
    }

    public static String formatLongToDate(Long dateValue, String valueFmt, String timeFmt) {
        Date date = parseDate(dateValue, valueFmt);
        return DateFormatUtils.format(date, timeFmt);
    }

    public static Long parseDateToLong(Date date) {
        return Long.parseLong(DateFormatUtils.format(date, DEFAULT_DATE_VALUE_FMT));
    }

    public static void threadSleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (Exception e) {
            logger.error("Fail to execute Thread.sleep({})!", mills);
        }
    }

    public static Double getBeanNumberFieldValue(Class<?> targetType, Object target, String fieldName) {
        Method method = ReflectionUtils.findMethod(targetType, "get" + StringUtils.capitalize(fieldName));
        if (method == null) {
            return 0D;
        }
        Object valueObj = ReflectionUtils.invokeMethod(method, target);
        if (valueObj == null) {
            return 0D;
        }
        return round(Double.parseDouble(valueObj.toString()), 2);
    }

    public static String formatPercent(final Number target, final Integer minIntegerDigits,
                                       final Integer fractionDigits, final Locale locale) {
        if (target == null) {
            return null;
        }

        NumberFormat format = NumberFormat.getPercentInstance(locale);
        format.setMinimumFractionDigits(fractionDigits);
        format.setMaximumFractionDigits(fractionDigits);
        if (minIntegerDigits != null) {
            format.setMinimumIntegerDigits(minIntegerDigits);
        }

        return format.format(target);
    }
}
