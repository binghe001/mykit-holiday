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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author binghe
 * @version 1.0.0
 * @description 计算指定日期是否为闰月，年、月、日
 */
public class Lunar extends BaseCalendar {
    private static final long serialVersionUID = -6323116565193987248L;

    private int year;
    private boolean isLeap;
    private int month;
    private int day;

    /**
     * 计算指定日期是否为闰月，年、月、日
     */
    public Lunar(Date objDate) throws ParseException {
        int i, leap = 0, temp = 0;
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        df2.setTimeZone(TimeZone.getTimeZone("UTC"));
        DateFormat dtFmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = df2.parse(  dtFmt.format(objDate));
        SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        df3.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date3 = df3.parse("" + 1900 + "-" + 1 + "-" + 31 + " 00:00:00");
        long time1=date.getTime();
        long time2=date3.getTime();
        int offset = (int)(( time1-time2) / 86400000);
        for (i = 1900; i < 2100 && offset > 0; i++) {
            temp = getLunarCalendarYearDays(i).intValue();
            offset -= temp;
        }

        if (offset < 0) {
            offset += temp;
            i--;
        }

        this.year = i;
        leap = leapMonth(i).intValue(); //闰哪个月
        this.isLeap = false;

        for (i = 1; i < 13 && offset > 0; i++) {
            //闰月
            if (leap > 0 && i == (leap + 1) && !this.isLeap) {
                --i;
                this.isLeap = true;
                temp = getYearLeapMonthDays(this.year);
            }
            else {
                temp = getYearMonthDays(this.year, i);
            }
            //解除闰月
            if (this.isLeap && i == (leap + 1)) this.isLeap = false;

            offset -= temp;
        }

        if (offset == 0 && leap > 0 && i == leap + 1)
            if (this.isLeap) {
                this.isLeap = false;
            }
            else {
                this.isLeap = true;
                --i;
            }

        if (offset < 0) {
            offset += temp;
            --i;
        }

        this.month = i;
        this.day = offset + 1;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isLeap() {
        return isLeap;
    }

    public void setLeap(boolean leap) {
        isLeap = leap;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
