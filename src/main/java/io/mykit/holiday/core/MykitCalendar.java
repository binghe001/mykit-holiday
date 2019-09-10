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

import io.mykit.holiday.entity.Element;
import io.mykit.holiday.utils.cache.CacheUtils;
import io.mykit.holiday.utils.calendar.SimpleCalendar;
import io.mykit.holiday.utils.date.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author binghe
 * @version 1.0.0
 * @description 计算农历节假日信息
 *      Element element = MykitCalendar.getCalendarDetail("2019-09-10","yyyy-MM-dd");
 *      element = MykitCalendar.getCalendarDetail("2019-09-10","yyyy-MM-dd");
 *      element = MykitCalendar.getCalendarDetail(new Date());
 */
public class MykitCalendar {
    /**
     * 获取指定日期的详细信息
     * @param date 传入的日期
     * @return 日期的详细信息
     * @throws ParseException 抛出的异常
     */
    public static Element getCalendarDetail(Date date) throws ParseException {
        Calendar cal = Calendar.getInstance() ;
        cal.setTime(date);
        String cacheKey = DateUtils.parseDateToStringDate(date, DateUtils.MONTH_FORMAT);
        SimpleCalendar simpleCalendar = null;
        if(CacheUtils.containsKey(cacheKey)){
            simpleCalendar = CacheUtils.getSimpleCalender(cacheKey);
        }else {
            simpleCalendar = new SimpleCalendar();
            simpleCalendar.calendar(cal);
            CacheUtils.put(cacheKey, simpleCalendar);
        }
        return simpleCalendar.getElements().get(cal.get(Calendar.DATE) - 1);
    }
    /**
     * 获取指定日期的详细信息
     * @param date 传入的日期
     * @param pattern 日期的格式
     * @return 日期的详细信息
     * @throws ParseException 抛出的异常
     */
    public static Element getCalendarDetail(String date, String pattern) throws ParseException {
        return getCalendarDetail(DateUtils.parseStringDateToDate(date, pattern));
    }
}
