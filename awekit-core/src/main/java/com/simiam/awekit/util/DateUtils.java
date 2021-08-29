package com.simiam.awekit.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * <p>Title: DateUtils</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/4/28 19:29</p>
 */
public abstract class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";

    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    public static final String yyyyMMddHHmm00 = "yyyyMMddHHmm00";

    public static final String yyyyMMddHH0000 = "yyyyMMddHH0000";

    public static final String yyyyMMdd000000 = "yyyyMMdd000000";

    public static final String yyyyMMddHHmm = "yyyyMMddHHmm";

    public static final String yyyyMMddHH = "yyyyMMddHH";

    public static final String yyyyMMdd = "yyyyMMdd";

    public static final String yyyyMM = "yyyyMM";

    public static final String MMdd = "MMdd";

    public static final String HHmmss = "HHmmss";

    public static final String HHmm = "HHmm";

    public static final String mmss = "mmss";

    public static final String yyyy_MM_dd = "yyyy-MM-dd";

    public static final String yyyy_MM = "yyyy-MM";

    public static final String MM_dd = "MM-dd";

    public static final String HH_mm_ss = "HH:mm:ss";

    public static final String HH_mm = "HH:mm";

    public static final String mm_ss = "mm:ss";

    public static Integer parseYesterdayTo(String format) {
        Date yesterday = org.apache.commons.lang3.time.DateUtils.addDays(new Date(), -1);
        return Integer.parseInt(DateFormatUtils.format(yesterday, format));
    }

    public static Long parseDateToLong(Date date, String fmt) {
        return Long.parseLong(DateFormatUtils.format(date, fmt));
    }

    public static Integer parseDateToInt(Date date, String fmt) {
        return Integer.parseInt(DateFormatUtils.format(date, fmt));
    }

    public static Date parseDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date parseDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date parseDateWith(LocalDateTime localDateTime, int hour, int minute, int second, int nano) {
        LocalDateTime targetDateTime = localDateTime.withHour(hour).withMinute(minute).withSecond(second).withNano(nano);
        return Date.from(targetDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Long parseLocalDateTimeToLong(LocalDateTime localDateTime, String fmt) {
        return parseDateToLong(parseDate(localDateTime), fmt);
    }

    public static Long parseLocalDateToLong(LocalDate localDate, String fmt) {
        return parseDateToLong(parseDate(localDate), fmt);
    }

    public static Integer parseLocalDateTimeToInt(LocalDateTime localDateTime, String fmt) {
        return parseDateToInt(parseDate(localDateTime), fmt);
    }

    public static Integer parseLocalDateToInt(LocalDate localDate, String fmt) {
        return parseDateToInt(parseDate(localDate), fmt);
    }

    public static Date parseInt(Integer input, String parsePattern) {
        try {
            return parseDate(input.toString(), parsePattern);
        } catch (ParseException e) {
            logger.error("Fail to parse date!", e);
        }
        return null;
    }

    public static Date parseThenAddDays(Integer input, String parsePattern, int amount) {
        Date date = parseInt(input, parsePattern);
        if (date != null) {
            date = addDays(date, amount);
        }
        return date;
    }

    public static Date parseThenAddMonths(Integer input, String parsePattern, int amount) {
        Date date = parseInt(input, parsePattern);
        if (date != null) {
            date = addMonths(date, amount);
        }
        return date;
    }

    public static Date getMondayOfWeek(Integer dateId, String fmtPattern) {
        if (fmtPattern == null) {
            fmtPattern = yyyyMMdd;
        }
        Date date = DateUtils.parseInt(dateId, fmtPattern);
        return getMondayOfWeek(date);
    }

    public static Date getMondayOfWeek(Date date) {
        LocalDate localDate = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
        localDate = localDate.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).plusDays(1);
        return DateUtils.parseDate(localDate);
    }

    public static Date getSundayOfWeek(Integer dateId, String fmtPattern) {
        if (fmtPattern == null) {
            fmtPattern = yyyyMMdd;
        }
        Date date = DateUtils.parseInt(dateId, fmtPattern);
        return getSundayOfWeek(date);
    }

    public static Date getSundayOfWeek(Date date) {
        LocalDate localDate = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
        localDate = localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).minusDays(1);
        return DateUtils.parseDate(localDate);
    }

    public static LocalDateTime toLocalDateTime(Date date, ZoneId zoneId) {
        return LocalDateTime.ofInstant(date.toInstant(), zoneId != null ? zoneId : ZoneId.systemDefault());
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return toLocalDateTime(date, null);
    }

    public static LocalDate toLocalDate(Date date, ZoneId zoneId) {
        return LocalDateTime.ofInstant(date.toInstant(), zoneId != null ? zoneId : ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate toLocalDate(Date date) {
        return toLocalDate(date, null);
    }
}
