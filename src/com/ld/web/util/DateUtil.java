package com.ld.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * 
 * <p>Title: DateUtil</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:时间工具类</p>
 *
 * @author LD
 *
 * @date 2015-08-14
 */
public class DateUtil {

    private static final Logger logger = Logger.getLogger(DateUtil.class);

    public static final String DATETIME_NO_CHARACTER = "yyyyMMddHHmmss";
    public static final String DATE_NO_CHARACTER = "yyyyMMdd";
    public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE = "yyyy-MM-dd";
    public static final String TIME = "HH:mm:ss";

    private static final Pattern PATTERN_DATETIME_NO_CHARACTER = Pattern.compile("\\d{4}\\d{1,2}\\d{1,2}\\d{1,2}\\d{1,2}\\d{1,2}");
    private static final Pattern PATTERN_DATE_NO_CHARACTER = Pattern.compile("\\d{4}\\d{1,2}\\d{1,2}");
    private static final Pattern PATTERN_DATETIME = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}(\\.\\d{1,4})?");
    private static final Pattern PATTERN_DATE = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}");

    /**
     * 格式化时间
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 格式化当前时间
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String formatNow(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 格式化时间
     * 
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parse(Date date, String pattern) {
        return parse(format(date, pattern), pattern);
    }

    /**
     * 格式化时间
     * 
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parse(String date, String pattern) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.parse(date);
        } catch (ParseException e) {
            logger.error(String.format("Parse date error: %s", e.getMessage()), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 格式化时间
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String formatStrDate(String date, String formatPattern) {
        SimpleDateFormat df = new SimpleDateFormat(formatPattern);

        Date d = parseAllPattern(date);
        return null == d ? null : df.format(d);
    }

    public static Date parseAllPattern(String date) {
        try {
            if (StringUtil.isEmpty(date)) {
                return null;
            }
            SimpleDateFormat df = new SimpleDateFormat(getFormatPattern(date));
            return df.parse(date);
        } catch (ParseException e) {
            logger.error(String.format("Parse date error: %s", e.getMessage()), e);
            throw new RuntimeException(e);
        }
    }

    private static String getFormatPattern(String date) {
        if (PATTERN_DATETIME.matcher(date).matches()) {
            return DATETIME;
        }
        if (PATTERN_DATE.matcher(date).matches()) {
            return DATE;
        }
        if (PATTERN_DATE_NO_CHARACTER.matcher(date).matches()) {
            return DATE_NO_CHARACTER;
        }
        if (PATTERN_DATETIME_NO_CHARACTER.matcher(date).matches()) {
            return DATETIME_NO_CHARACTER;
        }
        throw new IllegalArgumentException(String.format("The date(%s) is not correct date pattern.", date));
    }

    /**
     * 
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parseNow(String pattern) {
        return parse(new Date(), pattern);
    }

    /**
     * 计算year
     * 
     * @param date
     * @param day
     * @return
     */
    public static Date calcuYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 计算天
     * 
     * @param date
     * @param day
     * @return
     */
    public static Date calcuDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 计算分钟
     * 
     * @param date
     * @param minute
     * @return
     */
    public static Date calcuDateMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 当前时间基础上计算天
     * 
     * @param day
     * @return
     */
    public static Date calcuDateNow(int day) {
        return calcuDate(new Date(), day);
    }

}
