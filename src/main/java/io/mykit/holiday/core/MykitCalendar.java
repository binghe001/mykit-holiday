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
package io.mykit.holiday.core;

import io.mykit.holiday.constants.HolidayConstants;
import io.mykit.holiday.entity.Element;
import io.mykit.holiday.utils.calendar.SimpleCalendar;
import io.mykit.holiday.utils.date.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * @author binghe
 * @version 1.0.0
 * @description 计算日期信息
 *      Element element = MykitCalendar.getCalendarDetail("2019-09-10","yyyy-MM-dd", HolidayConstants.JDK_17);
 *      element = MykitCalendar.getCalendarDetail("2019-09-10","yyyy-MM-dd", HolidayConstants.JDK_18);
 *      element = MykitCalendar.getCalendarDetail(new Date(), HolidayConstants.JDK_17);
 *      element = MykitCalendar.getCalendarDetail(new Date(), HolidayConstants.JDK_18);
 */
public class MykitCalendar {
    /**
     * 获取指定日期的详细信息
     * @param date 传入的日期
     * @param jdkVersion JDK的版本
     * @return 日期的详细信息
     * @throws ParseException 抛出的异常
     */
    public static Element getCalendarDetail(Date date, String jdkVersion) throws ParseException {
        if (jdkVersion == null || jdkVersion.trim().isEmpty()){
            jdkVersion = HolidayConstants.JDK_18;
        }
        switch (jdkVersion){
            //基于JDK1.7 以及之前的版本
            case HolidayConstants.JDK_17:
                return SimpleCalendar.getCalendarDetailByJDK17(date, jdkVersion);
            case HolidayConstants.JDK_18:
                return SimpleCalendar.getCalendarDetailByJDK18(date, jdkVersion);
            default:
                return SimpleCalendar.getCalendarDetailByJDK18(date, jdkVersion);
        }
    }


    /**
     * 获取指定日期的详细信息
     * @param date 传入的日期
     * @param pattern 日期的格式
     * @param jdkVersion jdk版本
     * @return 日期的详细信息
     * @throws ParseException 抛出的异常
     */
    public static Element getCalendarDetail(String date, String pattern, String jdkVersion) throws ParseException {
        return getCalendarDetail(DateUtils.parseStringDateToDate(date, pattern), jdkVersion);
    }
}
