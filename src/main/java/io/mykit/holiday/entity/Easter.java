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
package io.mykit.holiday.entity;

import io.mykit.holiday.utils.calendar.BaseCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author binghe
 * @version 1.0.0
 * @description 计算指定年份的距离第一次月圆最近的周日的月份和日期
 */
public class Easter extends BaseCalendar {
    private static final long serialVersionUID = 898018543345210093L;
    //月份
    private int month;
    //日期
    private int date;

    public Easter(int y) throws ParseException {
        int term = getDateByFestivalFromYear(y, 5); //取得春分日期
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        df2.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date dayTerm = df2.parse("" +y + "-" +3 + "-" + term + " 00:00:00");//取得春分的公历日期控件(春分一定出现在3月)
        Lunar lDayTerm = new Lunar(dayTerm); //取得取得春分农历
        int lMlen=0;
        if (lDayTerm.getDay() < 15) //取得下个月圆的相差天数
            lMlen = 15 - lDayTerm.getDay();
        else
            lMlen = (lDayTerm.isLeap() ? getYearLeapMonthDays(y) : getYearMonthDays(y, lDayTerm.getMonth())) - lDayTerm.getDay() + 15;

        //一天等于 1000*60*60*24 = 86400000 毫秒
        Date l15 = new Date(dayTerm.getTime() + 86400000 * lMlen); //求出第一次月圆为公历几日
        Date dayEaster = new Date(l15.getTime() + 86400000 * ( 7 - l15.getDay() )); //求出下个周日

        this.month = dayEaster.getMonth();
        this.date = dayEaster.getDate();
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }
}
