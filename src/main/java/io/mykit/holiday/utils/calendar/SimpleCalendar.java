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
package io.mykit.holiday.utils.calendar;

import io.mykit.holiday.constants.HolidayConstants;
import io.mykit.holiday.entity.Element;
import io.mykit.holiday.utils.cache.CacheUtils;
import io.mykit.holiday.utils.date.DateUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * @author binghe
 * @version 1.0.0
 * @description 计算农历节假日信息
 *      Element element=SimpleCalendar.getCalendarDetail("2019-03-03","yyyy-MM-dd", HolidayConstants.JDK_17);
 *      Element element=SimpleCalendar.getCalendarDetail("2019-03-03","yyyy-MM-dd", HolidayConstants.JDK_18);
 *      Element element=SimpleCalendar.getCalendarDetail(new Date(), HolidayConstants.JDK_17);
 *      Element element=SimpleCalendar.getCalendarDetail(new Date(), HolidayConstants.JDK_18);
 */
public class SimpleCalendar extends BaseCalendar {

    /**
     *  JDK 1.7 执行计算
     */
    private void jDK17Calendar(Calendar calendar) throws ParseException {
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DATE);
        this.calendar(y, m, d);
    }

    /**
     *  JDK 1.7 执行计算
     */
    private void jDK18Calendar(LocalDate localDate) throws ParseException {
        int y = localDate.getYear();
        //注意，为了与JDK 1.7 以及之前版本保持一致，这里需要减1
        int m = localDate.getMonthValue() - 1;
        int d = localDate.getDayOfMonth();
        this.calendar(y, m, d);
    }

    /**
     * 基于JDK 1.7以及之前版本的JDK 计算并获取日期信息
     */
    public static Element getCalendarDetailByJDK17(Date date, String jdkVersion) throws ParseException {
        String cacheKey = HolidayConstants.getCachekey(jdkVersion, date);
        Calendar cal = Calendar.getInstance() ;
        cal.setTime(date);
        SimpleCalendar simpleCalendar = null;
        if(CacheUtils.containsKey(cacheKey)){
            simpleCalendar = CacheUtils.getSimpleCalender(cacheKey);
        }else {
            simpleCalendar = new SimpleCalendar();
            simpleCalendar.jDK17Calendar(cal);
            CacheUtils.put(cacheKey, simpleCalendar);
        }
        return simpleCalendar.getElements().get(cal.get(Calendar.DATE) - 1);
    }

    /**
     * 基于JDK 1.8以及之后版本的JDK 计算并获取日期信息
     */
    public static Element getCalendarDetailByJDK18(Date date, String jdkVersion) throws ParseException {
        String cacheKey = HolidayConstants.getCachekey(jdkVersion, date);
        LocalDate localDate = DateUtils.parseDateToLocalDate(date);
        SimpleCalendar simpleCalendar = null;
        if(CacheUtils.containsKey(cacheKey)){
            simpleCalendar = CacheUtils.getSimpleCalender(cacheKey);
        }else {
            simpleCalendar = new SimpleCalendar();
            simpleCalendar.jDK18Calendar(localDate);
            CacheUtils.put(cacheKey, simpleCalendar);
        }
        return simpleCalendar.getElements().get(localDate.getDayOfMonth() - 1);
    }
}
