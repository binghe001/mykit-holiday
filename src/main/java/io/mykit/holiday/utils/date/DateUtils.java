/**
 * Copyright 2019-2999 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mykit.holiday.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author binghe
 * @version 1.0.0
 * @description 日期工具类
 */
public class DateUtils {
    public static final String MONTH_SINGLE = "MM";
    public static final String DATE_SINGLE = "dd";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_NO_FORMAT = "yyyyMMddHHmmss";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String MONTH_FORMAT = "yyyy-MM";
    public static final String MONTH_NO_FORMAT = "yyyyMM";
    public static final String YEAR_FORMAT = "yyyy";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String MINUTE_FORMAT = "HH:mm";
    public static final String BIRTHDAY_FORMAT = "yyyy年MM月dd日";
    public static final String DATE_DATE = "yyyyMMdd";
    public static final String LOCAL_ENGLISH_FORMAT = "dd/MMM/yyyy:hh:mm:ss Z";

    /**
     * 判断是否是今天的日期
     */
    public static boolean isToday(int year, int month, int day){
        String date = String.valueOf(year).concat("-").concat(getFormatString(month)).concat("-").concat(getFormatString(day));
        return parseDateToStringDate(new Date(), DATE_FORMAT).equals(date);
    }

    /**
     * 判断是否是今天的日期
     */
    public static boolean isToday(Date date){
        return DateUtils.parseDateToStringDate(new Date(), DateUtils.DATE_FORMAT).equals(DateUtils.parseDateToStringDate(date, DateUtils.DATE_FORMAT));
    }

    /**
     * 将日期类型转化为字符串
     */
    public static String parseDateToStringDate(Date date, String formatString) {
        SimpleDateFormat format = getDateFormat(formatString);
        return format.format(date);
    }

    /**
     * 格式化字符串
     * @return 格式化后的字符串
     */
    public static String getFormatString(int num){
        return num <=9 ? "0".concat(String.valueOf(num)) : String.valueOf(num);
    }

    /**
     * 获取SimpleDateFormat
     */
    private static SimpleDateFormat getDateFormat(String formatString) {
        return new SimpleDateFormat(formatString);
    }

    /**
     * 将字符串转化为日期类型
     */
    public static Date parseStringDateToDate(String date, String formatString) {
        try {
            return getDateFormat(formatString).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
