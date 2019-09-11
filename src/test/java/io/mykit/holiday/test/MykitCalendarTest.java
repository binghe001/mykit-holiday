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
package io.mykit.holiday.test;

import io.mykit.holiday.constants.HolidayConstants;
import io.mykit.holiday.core.MykitCalendar;
import io.mykit.holiday.utils.date.DateUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试工具类
 */
public class MykitCalendarTest {

    private final Logger logger = LoggerFactory.getLogger(MykitCalendarTest.class);

    @Test
    public void testDateCalendarJDK17() throws Exception{
        logger.info(MykitCalendar.getCalendarDetail(new Date(), HolidayConstants.JDK_17).toString());
    }
    @Test
    public void testDateCalendarJDK18() throws Exception{
        logger.info(MykitCalendar.getCalendarDetail(new Date(), HolidayConstants.JDK_18).toString());
    }
    @Test
    public void testDateEquals() throws Exception{
        boolean flag = MykitCalendar.getCalendarDetail(new Date(), HolidayConstants.JDK_18).toString().equals(MykitCalendar.getCalendarDetail(new Date(), HolidayConstants.JDK_17).toString());
        if (flag){
            logger.info("使用不同版本的JDK获取的当天日期信息相同...");
        }else{
            logger.info("使用不同版本的JDK获取的当天日期信息不同...");
        }
    }
    @Test
    public void testStringCalendarJDK17() throws Exception{
        logger.info(MykitCalendar.getCalendarDetail("2100-12-31", DateUtils.DATE_FORMAT, HolidayConstants.JDK_17).toString());
    }
    @Test
    public void testStringCalendarJDK18() throws Exception{
        logger.info(MykitCalendar.getCalendarDetail("2100-12-31", DateUtils.DATE_FORMAT, HolidayConstants.JDK_18).toString());
    }

    @Test
    public void testMatcher() throws Exception{
        String str = "1001*国庆节 国际音乐节 国际老人节";
        Pattern p = Pattern.compile("^(\\d{2})(\\d{2})([\\s\\*])(.+)$");
        Matcher matcher = p.matcher(str);
        if(matcher.matches()){
            for(int i = 0; i < matcher.groupCount(); i++){
                System.out.println(matcher.group(i));
            }
        }
    }

    @Test
    public void testDate() throws Exception{
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfMonth());

        LocalDate localDate2 = LocalDate.parse("2019-09-11");
        System.out.println(localDate2);

        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH);
        System.out.println(y);
        System.out.println(m);
        System.out.println(cal.get(Calendar.DATE));
    }

    @Test
    public void testSimpleDateFormat() throws Exception{
        Date date = DateUtils.parseStringDateToDate("3033-12-31", DateUtils.DATE_FORMAT);
        System.out.println(DateUtils.parseDateToStringDate(date, DateUtils.DATE_FORMAT));
    }
}
