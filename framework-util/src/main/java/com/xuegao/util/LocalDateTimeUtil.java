package com.xuegao.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class LocalDateTimeUtil {
    private LocalDateTimeUtil() {
    }

    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_YYYY_MM_DD_HH_MM_00 = "yyyy-MM-dd HH:mm:00";
    public static final String PATTERN_YYYYMMDDHHMM = "yyyyMMddHHmm";
    public static final String PATTERN_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String PATTERN_HH_MM_SS = "HH:mm:ss";
    public static final String PATTERN_HH_MM = "HH:mm";
    public static final String PATTERN_MM_SS = "mm:ss";

    // region str interconversion LocalDateTime

    public static LocalDateTime strToLocalDateTime(String str) {
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(PATTERN_YYYY_MM_DD_HH_MM_SS));
    }

    public static LocalDateTime strToLocalDateTime(String str, String pattern) {
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(pattern));
    }

    public static String localDateTimeToStr(LocalDateTime localDateTime) {
        return localDateTimeToStr(localDateTime, PATTERN_YYYY_MM_DD_HH_MM_SS);
    }

    public static String localDateTimeToStr(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    // endregion

    // region LocalDateTime 通用

    /**
     * l1 = l2：= 0 相等  2022-06-29 15:31:00 = 2022-06-29 15:31:00
     * l1 > l2：= 1 大于  2022-06-30 15:31:00 = 2022-06-29 15:31:00
     * l1 < l2：= -1 小于  2022-06-29 15:31:00 = 2022-06-30 15:31:00
     * <p>
     * compare
     *
     * @param l1:
     * @param l2:
     * @return int
     * @author xuegao
     * @date 2022/6/29 15:30
     */
    public static int compare(LocalDateTime l1, LocalDateTime l2) {
        return l1.compareTo(l2);
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    // endregion

    // region 增加减少 秒数，分钟数，小时数，天数，，周，月，年，等等

    /**
     * 获取一个时间，负数是过去的，正数是现在的
     * plusDays
     *
     * @param seconds:
     * @return java.time.LocalDateTime
     * @author xuegao
     * @date 2022/6/29 15:24
     */
    public static LocalDateTime plusSeconds(long seconds) {
        LocalDateTime nowLocalDateTime = LocalDateTime.now();
        return plusSeconds(nowLocalDateTime, seconds);
    }

    /**
     * 获取一个时间，负数是过去的，正数是现在的
     * plusDays
     *
     * @param seconds:
     * @return java.time.LocalDateTime
     * @author xuegao
     * @date 2022/6/29 15:24
     */
    public static LocalDateTime plusSeconds(LocalDateTime localDateTime, long seconds) {
        return localDateTime.plusSeconds(seconds);
    }

    /**
     * 获取一个时间，负数是过去的，正数是现在的
     * plusDays
     *
     * @param minutes:
     * @return java.time.LocalDateTime
     * @author xuegao
     * @date 2022/6/29 15:24
     */
    public static LocalDateTime plusMinutes(long minutes) {
        LocalDateTime nowLocalDateTime = LocalDateTime.now();
        return plusMinutes(nowLocalDateTime, minutes);
    }

    /**
     * 获取一个时间，负数是过去的，正数是现在的
     * plusDays
     *
     * @param minutes:
     * @return java.time.LocalDateTime
     * @author xuegao
     * @date 2022/6/29 15:24
     */
    public static LocalDateTime plusMinutes(LocalDateTime localDateTime, long minutes) {
        return localDateTime.plusMinutes(minutes);
    }

    /**
     * 获取一个时间，负数是过去的，正数是现在的
     * plusDays
     *
     * @param days:
     * @return java.time.LocalDateTime
     * @author xuegao
     * @date 2022/6/29 15:24
     */
    public static LocalDateTime plusDays(long days) {
        LocalDateTime nowLocalDateTime = LocalDateTime.now();
        return plusDays(nowLocalDateTime, days);
    }

    /**
     * 获取一个时间，负数是过去的，正数是现在的
     * plusDays
     *
     * @param days:
     * @return java.time.LocalDateTime
     * @author xuegao
     * @date 2022/6/29 15:24
     */
    public static LocalDateTime plusDays(LocalDateTime localDateTime, long days) {
        return localDateTime.plusDays(days);
    }

    // endregion

    // region LocalDateTime interconversion Date Timestamp

    public static Date toDate() {
        return toDate(now());
    }

    public static Date toDate(LocalDateTime localDateTime) {
        java.util.Date from = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return new Date(from.getTime());
    }

    public static Timestamp toTimestamp() {
        return toTimestamp(now());
    }

    public static Timestamp toTimestamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    public static LocalDateTime toLocalDateTime() {
        return toLocalDateTime(toDate());
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return new java.util.Date(date.getTime()).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    // endregion

    // region 计算两个时间相差多少秒，分钟，小时，天，月，年

    /**
     * 计算两个时间相差多少天（date2 - date1）
     *
     * @param date1 时间1
     * @param date2 时间2
     * @author xuegao
     */
    public static long differentSeconds(LocalDateTime date1, LocalDateTime date2) {
        return date1.until(date2, ChronoUnit.SECONDS);
    }

    /**
     * 计算两个时间相差多少天（date2 - date1）
     *
     * @param date1 时间1
     * @param date2 时间2
     * @author xuegao
     */
    public static long differentSeconds(LocalDate date1, LocalDate date2) {
        LocalDateTime localDateTime1 = localDateToLocalDateTime(date1, LocalTime.MIN);
        LocalDateTime localDateTime2 = localDateToLocalDateTime(date2, LocalTime.MIN);
        return differentSeconds(localDateTime1, localDateTime2);
    }

    public static long differentSeconds(String date1, String date2, String pattern) {
        if (PATTERN_YYYY_MM_DD.equals(pattern)) {
            LocalDate localDate1 = strToLocalDate(date1, pattern);
            LocalDate localDate2 = strToLocalDate(date2, pattern);
            return differentSeconds(localDate1, localDate2);
        }
        LocalDateTime localDateTime1 = strToLocalDateTime(date1, pattern);
        LocalDateTime localDateTime2 = strToLocalDateTime(date2, pattern);
        return differentSeconds(localDateTime1, localDateTime2);
    }

    /**
     * 计算两个时间相差多少天（date2 - date1）
     *
     * @param date1 时间1
     * @param date2 时间2
     * @author fjm
     */
    public static long differentDays(LocalDateTime date1, LocalDateTime date2) {
        return date2.toLocalDate().toEpochDay() - date1.toLocalDate().toEpochDay();
    }

    /**
     * 计算两个时间相差多少天（date2 - date1）
     *
     * @param date1 时间1
     * @param date2 时间2
     * @author fjm
     */
    public static long differentDays(LocalDate date1, LocalDate date2) {
        return date2.toEpochDay() - date1.toEpochDay();
    }

    public static long differentDays(String date1, String date2, String pattern) {
        if (PATTERN_YYYY_MM_DD.equals(pattern)) {
            LocalDate localDate1 = strToLocalDate(date1, pattern);
            LocalDate localDate2 = strToLocalDate(date2, pattern);
            return differentDays(localDate1, localDate2);
        }
        LocalDateTime localDateTime1 = strToLocalDateTime(date1, pattern);
        LocalDateTime localDateTime2 = strToLocalDateTime(date2, pattern);
        return differentDays(localDateTime1, localDateTime2);
    }

    /**
     * 获取两个时间点的月份差
     *
     * @param dt1 第一个时间点
     * @param dt2 第二个时间点
     * @return int，即需求的月数差
     */
    public static int differentMonths(LocalDateTime dt1, LocalDateTime dt2) {
        //获取第一个时间点的月份
        int month1 = dt1.getMonthValue();
        //获取第一个时间点的年份
        int year1 = dt1.getYear();
        //获取第一个时间点的月份
        int month2 = dt2.getMonthValue();
        //获取第一个时间点的年份
        int year2 = dt2.getYear();
        //返回两个时间点的月数差
        return (year2 - year1) * 12 + (month2 - month1);
    }

    /**
     * 获取两个时间点的月份差
     *
     * @param dt1 第一个时间点
     * @param dt2 第二个时间点
     * @return int，即需求的月数差
     */
    public static int differentMonths(LocalDate dt1, LocalDate dt2) {
        //获取第一个时间点的月份
        int month1 = dt1.getMonthValue();
        //获取第一个时间点的年份
        int year1 = dt1.getYear();
        //获取第一个时间点的月份
        int month2 = dt2.getMonthValue();
        //获取第一个时间点的年份
        int year2 = dt2.getYear();
        //返回两个时间点的月数差
        return (year2 - year1) * 12 + (month2 - month1);
    }

    /**
     * 获取两个时间点的月份差
     *
     * @param date1 第一个时间点
     * @param date2 第二个时间点
     * @return int，即需求的月数差
     */
    public static int differentMonths(String date1, String date2, String pattern) {
        if (PATTERN_YYYY_MM_DD.equals(pattern)) {
            LocalDate localDate1 = strToLocalDate(date1, pattern);
            LocalDate localDate2 = strToLocalDate(date2, pattern);
            return differentMonths(localDate1, localDate2);
        }
        LocalDateTime localDateTime1 = strToLocalDateTime(date1, pattern);
        LocalDateTime localDateTime2 = strToLocalDateTime(date2, pattern);
        return differentMonths(localDateTime1, localDateTime2);
    }

    // endregion

    // region LocalDate

    public static LocalDate strToLocalDate(String str) {
        return LocalDate.parse(str, DateTimeFormatter.ofPattern(PATTERN_YYYY_MM_DD));
    }

    public static LocalDate strToLocalDate(String str, String pattern) {
        return LocalDate.parse(str, DateTimeFormatter.ofPattern(pattern));
    }

    public static String localDateToStr(LocalDate localDate) {
        return localDateToStr(localDate, PATTERN_YYYY_MM_DD);
    }

    public static String localDateToStr(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    // endregion

    // region LocalTime

    public static LocalTime strToLocalTime(String str) {
        return LocalTime.parse(str, DateTimeFormatter.ofPattern(PATTERN_HH_MM_SS));
    }

    public static LocalTime strToLocalTime(String str, String pattern) {
        return LocalTime.parse(str, DateTimeFormatter.ofPattern(pattern));
    }

    public static String localTimeToStr(LocalTime localTime) {
        return localTimeToStr(localTime, PATTERN_HH_MM_SS);
    }

    public static String localTimeToStr(LocalTime localTime, String pattern) {
        return localTime.format(DateTimeFormatter.ofPattern(pattern));
    }
    // endregion

    // region LocalDate interconversion LocalDateTime
    public static LocalDate localDateTimeToLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    public static LocalDateTime localDateToLocalDateTime(LocalDate localDate) {
        return localDateToLocalDateTime(localDate, LocalTime.now());
    }

    public static LocalDateTime localDateToLocalDateTime0000(LocalDate localDate) {
        return localDateToLocalDateTime(localDate, LocalTime.MIN);
    }

    public static LocalDateTime localDateToLocalDateTime2359(LocalDate localDate) {
        return localDateToLocalDateTime(localDate, LocalTime.MAX);
    }

    public static LocalDateTime localDateToLocalDateTime(LocalDate localDate, LocalTime localTime) {
        return localDate.atTime(localTime);
    }
    // endregion

    // region 获取入参中，秒的最大值，秒的最小值
    public static LocalDateTime localDateTimeSetSecond00(LocalDateTime inputLocalDateTime) {
        LocalDate localDate = inputLocalDateTime.toLocalDate();
        // 指定两位整数，
        DecimalFormat df = new DecimalFormat("00");
        int hour = inputLocalDateTime.getHour();
        int minute = inputLocalDateTime.getMinute();
        String formatHour = df.format(hour);
        String formatMinute = df.format(minute);
        String localTimeStr = formatHour + ":" + formatMinute + ":" + "00";
        return localDateToLocalDateTime(localDate, strToLocalTime(localTimeStr));
    }

    public static LocalDateTime localDateTimeSetSecond59(LocalDateTime inputLocalDateTime) {
        LocalDate localDate = inputLocalDateTime.toLocalDate();
        int hour = inputLocalDateTime.getHour();
        int minute = inputLocalDateTime.getMinute();
        // 指定两位整数
        DecimalFormat df = new DecimalFormat("00");
        String formatHour = df.format(hour);
        String formatMinute = df.format(minute);

        String localTimeStr = formatHour + ":" + formatMinute + ":" + "59";
        return localDateToLocalDateTime(localDate, strToLocalTime(localTimeStr));
    }

    /**
     * 获取入参时间 + minutes之后，的开始时间
     * localDateTimeAddMinutesSetSecond00
     *
     * @param inputLocalDateTime:
     * @param minutes:
     * @return java.time.LocalDateTime
     * @author fjm
     * @date 2022/8/11 13:19
     */
    public static LocalDateTime localDateTimeAddMinutesSetSecond00(LocalDateTime inputLocalDateTime, long minutes) {
        LocalDateTime newLocalDateTime = plusMinutes(inputLocalDateTime, minutes);

        LocalDate localDate = newLocalDateTime.toLocalDate();

        int hour = newLocalDateTime.getHour();
        int minute = newLocalDateTime.getMinute();
        // 指定两位整数
        DecimalFormat df = new DecimalFormat("00");
        String formatHour = df.format(hour);
        String formatMinute = df.format(minute);
        String localTimeStr = formatHour + ":" + formatMinute + ":" + "00";

        return localDateToLocalDateTime(localDate, strToLocalTime(localTimeStr));
    }
    // endregion

    public static void main(String[] args) {
        // 测试早上
        LocalDateTime testLocalDateTime1 = strToLocalDateTime("2022-06-29 15:31:00");
        // 测试下午
        LocalDateTime testLocalDateTime2 = strToLocalDateTime("2022-06-30 05:31:00");
        LocalDate testLocalDate1 = strToLocalDate("2022-06-29");
        // 测试对比
        int compare = compare(testLocalDateTime1, testLocalDateTime2);
        System.out.println("1 = " + compare);
        compare = compare(testLocalDateTime2, testLocalDateTime1);
        System.out.println("2 = " + compare);
        // 测试加减时间
        LocalDateTime localDateTime = plusDays(-182);
        System.out.println(LocalDateTimeUtil.localDateTimeToStr(localDateTime));

        // 测试时间的对比
        long differentSeconds = differentSeconds("2022-06-30 15:31:00", "2022-06-30 15:32:00", PATTERN_YYYY_MM_DD_HH_MM_SS);
        System.out.println(differentSeconds);
        differentSeconds = differentSeconds("2020-04-23", "2021-04-23", PATTERN_YYYY_MM_DD);
        System.out.println(differentSeconds);

        // long differentMinutes = differentMinutes("2022-06-30 15:31:00", "2022-06-30 15:32:00", PATTERN_YYYY_MM_DD_HH_MM_SS);
        // System.out.println(differentMinutes);
        // differentMinutes = differentMinutes("2020-04-23", "2021-04-23", PATTERN_YYYY_MM_DD);
        // System.out.println(differentMinutes);

        long differentDays = differentDays("2022-06-30 15:31:00", "2022-07-29 15:31:00", PATTERN_YYYY_MM_DD_HH_MM_SS);
        System.out.println(differentDays);
        differentDays = differentDays("2020-04-23", "2021-04-23", PATTERN_YYYY_MM_DD);
        System.out.println(differentDays);

        long differentMonths = differentMonths("2022-06-30 15:31:00", "2022-07-31 15:31:00", PATTERN_YYYY_MM_DD_HH_MM_SS);
        System.out.println(differentMonths);
        differentMonths = differentDays("2022-07-13", "2023-07-12", PATTERN_YYYY_MM_DD);
        System.out.println(differentMonths);

        System.out.println("LocalDate begin ");
        LocalDateTime localDateToLocalDateTime = localDateToLocalDateTime(testLocalDate1, LocalTime.MAX);
        System.out.println(localDateTimeToStr(localDateToLocalDateTime));
        LocalDateTime localDateToLocalDateTime2359 = localDateToLocalDateTime2359(testLocalDate1);
        System.out.println(localDateTimeToStr(localDateToLocalDateTime2359));
        LocalDateTime localDateToLocalDateTime0000 = localDateToLocalDateTime0000(testLocalDate1);
        System.out.println(localDateTimeToStr(localDateToLocalDateTime0000));
        System.out.println("LocalDate end ");

        LocalDateTime test1Second00 = localDateTimeSetSecond00(testLocalDateTime1);
        System.out.println(localDateTimeToStr(test1Second00));
        LocalDateTime test2Second00 = localDateTimeSetSecond00(testLocalDateTime2);
        System.out.println(localDateTimeToStr(test2Second00));

        LocalDateTime test1Second59 = localDateTimeSetSecond59(testLocalDateTime1);
        System.out.println(localDateTimeToStr(test1Second59));
        LocalDateTime test2Second59 = localDateTimeSetSecond59(testLocalDateTime2);
        System.out.println(localDateTimeToStr(test2Second59));

        LocalDateTime test1AddMinutesSetSecond00 = localDateTimeAddMinutesSetSecond00(testLocalDateTime1, 1);
        System.out.println(localDateTimeToStr(test1AddMinutesSetSecond00));
        LocalDateTime test2AddMinutesSetSecond00 = localDateTimeAddMinutesSetSecond00(testLocalDateTime2, 1);
        System.out.println(localDateTimeToStr(test2AddMinutesSetSecond00));
    }
}