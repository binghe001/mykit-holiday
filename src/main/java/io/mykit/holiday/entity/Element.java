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

import com.alibaba.fastjson.JSONObject;
import io.mykit.holiday.constants.HolidayConstants;

import java.io.Serializable;

/**
 * @author binghe
 * @version 1.0.0
 * @description 计算元素，存储的是结果数据
 */
public class Element implements Serializable {
    private static final long serialVersionUID = -5931060700211084394L;
    //公历
    //公元年4位数字
    private int gregorianYear;
    //公元月数字
    private int gregorianMonth;
    //公元日数字
    private int gregorianDay;
    //星期, 1个中文
    private char week;

    //农历
    //公元年4位数字
    private int lunarYear;
    //农历月数字
    private int lunarMonth;
    //农历日数字
    private int lunarDay;
    //是否为农历闰月?
    private boolean isLeap;

    //中文
    private String lunarMonthChinese;
    private String lunarDayChinese;

    //八字
    //年柱, 2个中文
    private String chineseYear;
    //月柱, 2个中文
    private String chineseMonth;
    //日柱, 2个中文
    private String chineseDay;
    private String color;
    //是否是今天
    private boolean isToday;

    //农历节日
    private String lunarFestival;
    //公历节日
    private String solarFestival;
    //节气
    private String solarTerms;
    //是否是闰年
    private boolean isLeapYear;

    private String sgz5;
    private char sgz3;


    public Element(int gregorianYear, int gregorianMonth, int gregorianDay, char week, int lunarYear,int  lunarMonth,int  lunarDay, boolean isLeap,
                   String  chineseYear, String chineseMonth, String chineseDay, boolean isToday, boolean isLeapYear) {
        this.isToday = isToday; //是否是今天
        //公历
        this.gregorianYear = gregorianYear;   //公元年4位数字
        this.gregorianMonth = gregorianMonth;  //公元月数字
        this.gregorianDay = gregorianDay;    //公元日数字
        this.week = week;    //星期, 1个中文
        //农历
        this.lunarYear = lunarYear;   //公元年4位数字
        this.lunarMonth = lunarMonth;  //农历月数字
        this.lunarDay = lunarDay;    //农历日数字
        this.isLeap = isLeap;  //是否为农历闰月?
        //中文
        this.lunarMonthChinese = HolidayConstants.MONTH_CHINESE[lunarMonth-1];
        this.lunarDayChinese = HolidayConstants.DAY_CHINESE[lunarDay-1];
        //八字
        this.chineseYear = chineseYear;   //年柱, 2个中文
        this.chineseMonth = chineseMonth;  //月柱, 2个中文
        this.chineseDay = chineseDay;    //日柱, 2个中文

        this.color = "";

        this.lunarFestival = ""; //农历节日
        this.solarFestival = ""; //公历节日
        this.solarTerms = ""; //节气
        this.isLeapYear = isLeapYear;   //是否是闰年

    }

    public String getSgz5() {
        return sgz5;
    }

    public void setSgz5(String sgz5) {
        this.sgz5 = sgz5;
    }

    public char getSgz3() {
        return sgz3;
    }

    public void setSgz3(char sgz3) {
        this.sgz3 = sgz3;
    }

    public int getGregorianYear() {
        return gregorianYear;
    }

    public void setGregorianYear(int gregorianYear) {
        this.gregorianYear = gregorianYear;
    }

    public int getGregorianMonth() {
        return gregorianMonth;
    }

    public void setGregorianMonth(int gregorianMonth) {
        this.gregorianMonth = gregorianMonth;
    }

    public int getGregorianDay() {
        return gregorianDay;
    }

    public void setGregorianDay(int gregorianDay) {
        this.gregorianDay = gregorianDay;
    }

    public char getWeek() {
        return week;
    }

    public void setWeek(char week) {
        this.week = week;
    }

    public int getLunarYear() {
        return lunarYear;
    }

    public void setLunarYear(int lunarYear) {
        this.lunarYear = lunarYear;
    }

    public int getLunarMonth() {
        return lunarMonth;
    }

    public void setLunarMonth(int lunarMonth) {
        this.lunarMonth = lunarMonth;
    }

    public int getLunarDay() {
        return lunarDay;
    }

    public void setLunarDay(int lunarDay) {
        this.lunarDay = lunarDay;
    }

    public boolean isLeap() {
        return isLeap;
    }

    public void setLeap(boolean leap) {
        isLeap = leap;
    }

    public String getChineseYear() {
        return chineseYear;
    }

    public void setChineseYear(String chineseYear) {
        this.chineseYear = chineseYear;
    }

    public String getChineseMonth() {
        return chineseMonth;
    }

    public void setChineseMonth(String chineseMonth) {
        this.chineseMonth = chineseMonth;
    }

    public String getChineseDay() {
        return chineseDay;
    }

    public void setChineseDay(String chineseDay) {
        this.chineseDay = chineseDay;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isToday() {
        return isToday;
    }

    public void setToday(boolean today) {
        isToday = today;
    }

    public String getLunarFestival() {
        return lunarFestival;
    }

    public void setLunarFestival(String lunarFestival) {
        this.lunarFestival = lunarFestival;
    }

    public String getSolarFestival() {
        return solarFestival;
    }

    public void setSolarFestival(String solarFestival) {
        this.solarFestival = solarFestival;
    }

    public String getSolarTerms() {
        return solarTerms;
    }

    public void setSolarTerms(String solarTerms) {
        this.solarTerms = solarTerms;
    }

    public String getLunarMonthChinese() {
        return lunarMonthChinese;
    }

    public void setLunarMonthChinese(String lunarMonthChinese) {
        this.lunarMonthChinese = lunarMonthChinese;
    }

    public String getLunarDayChinese() {
        return lunarDayChinese;
    }

    public void setLunarDayChinese(String lunarDayChinese) {
        this.lunarDayChinese = lunarDayChinese;
    }

    public boolean isLeapYear() {
        return isLeapYear;
    }

    public void setLeapYear(boolean leapYear) {
        isLeapYear = leapYear;
    }

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
