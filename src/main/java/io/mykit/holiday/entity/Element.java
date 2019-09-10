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
    private int sYear;
    //公元月数字
    private int sMonth;
    //公元日数字
    private int sDay;
    //星期, 1个中文
    private char week;

    //农历
    //公元年4位数字
    private int lYear;
    //农历月数字
    private int  lMonth;
    //农历日数字
    private int lDay;
    //是否为农历闰月?
    private boolean isLeap;

    //中文
    private String lMonthChinese;
    private String lDayChinese;

    //八字
    //年柱, 2个中文
    private String  cYear;
    //月柱, 2个中文
    private String cMonth;
    //日柱, 2个中文
    private String cDay;
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


    public Element(int sYear, int sMonth, int sDay, char week, int lYear,int  lMonth,int  lDay, boolean isLeap,String  cYear, String cMonth, String cDay, boolean isToday, boolean isLeapYear) {
        this.isToday = isToday; //是否是今天
        //公历
        this.sYear = sYear;   //公元年4位数字
        this.sMonth = sMonth;  //公元月数字
        this.sDay = sDay;    //公元日数字
        this.week = week;    //星期, 1个中文
        //农历
        this.lYear = lYear;   //公元年4位数字
        this.lMonth = lMonth;  //农历月数字
        this.lDay = lDay;    //农历日数字
        this.isLeap = isLeap;  //是否为农历闰月?
        //中文
        this.lMonthChinese = HolidayConstants.MONTH_CHINESE[lMonth-1];
        this.lDayChinese = HolidayConstants.DAY_CHINESE[lDay-1];
        //八字
        this.cYear = cYear;   //年柱, 2个中文
        this.cMonth = cMonth;  //月柱, 2个中文
        this.cDay = cDay;    //日柱, 2个中文

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

    public int getsYear() {
        return sYear;
    }

    public void setsYear(int sYear) {
        this.sYear = sYear;
    }

    public int getsMonth() {
        return sMonth;
    }

    public void setsMonth(int sMonth) {
        this.sMonth = sMonth;
    }

    public int getsDay() {
        return sDay;
    }

    public void setsDay(int sDay) {
        this.sDay = sDay;
    }

    public char getWeek() {
        return week;
    }

    public void setWeek(char week) {
        this.week = week;
    }

    public int getlYear() {
        return lYear;
    }

    public void setlYear(int lYear) {
        this.lYear = lYear;
    }

    public int getlMonth() {
        return lMonth;
    }

    public void setlMonth(int lMonth) {
        this.lMonth = lMonth;
    }

    public int getlDay() {
        return lDay;
    }

    public void setlDay(int lDay) {
        this.lDay = lDay;
    }

    public boolean isLeap() {
        return isLeap;
    }

    public void setLeap(boolean leap) {
        isLeap = leap;
    }

    public String getcYear() {
        return cYear;
    }

    public void setcYear(String cYear) {
        this.cYear = cYear;
    }

    public String getcMonth() {
        return cMonth;
    }

    public void setcMonth(String cMonth) {
        this.cMonth = cMonth;
    }

    public String getcDay() {
        return cDay;
    }

    public void setcDay(String cDay) {
        this.cDay = cDay;
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

    public String getlMonthChinese() {
        return lMonthChinese;
    }

    public void setlMonthChinese(String lMonthChinese) {
        this.lMonthChinese = lMonthChinese;
    }

    public String getlDayChinese() {
        return lDayChinese;
    }

    public void setlDayChinese(String lDayChinese) {
        this.lDayChinese = lDayChinese;
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
