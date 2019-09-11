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
import io.mykit.holiday.entity.Easter;
import io.mykit.holiday.entity.Element;
import io.mykit.holiday.entity.Lunar;
import io.mykit.holiday.utils.date.DateUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;

/**
 * @author binghe
 * @version 1.0.0
 * @description 基础日期工具类
 */
public class BaseCalendar implements Serializable {
    private static final long serialVersionUID = 1960277050569752154L;

    //存储结果数据的集合
    private List<Element> elements = new ArrayList<Element>();
    //公历当月天数
    private Long length;
    //公历当月1日星期几
    private int firstWeek;

    public List<Element> getElements() {
        return elements;
    }

    /**
     * 实际执行计算的方法
     */
    protected void calendar(int y, int m, int d) throws ParseException {
        Date sDObj = null;
        Lunar lDObj = null;
        Boolean lL = null;
        Long lD2 = null;
        Integer lY = null, lM = null, lD = 1, lX = 0, tmp1, tmp2, lM2, lY2 = null, tmp3, dayglus, bsg, xs, xs1, fs, fs1, cs, cs1 = null;
        String cY, cM, cD; //年柱,月柱,日柱
        Integer[] lDPOS = new Integer[3];
        Integer n = 0;
        Integer firstLM = 0;
        String dateString = y + "-" + DateUtils.getFormatString(m+1) + "-" + "01";
        sDObj = DateUtils.parseStringDateToDate(dateString, DateUtils.DATE_FORMAT);

        this.length = getSolarMonthDays(y, m);    //公历当月天数
        this.firstWeek = sDObj.getDay();    //公历当月1日星期几

        //年柱 1900年立春后为庚子年(60进制36)
        if (m < 2) cY = getHeavenlyAndEarthly(y - 1900 + 36 - 1);
        else cY = getHeavenlyAndEarthly(y - 1900 + 36);
        int term2 = getDateByFestivalFromYear(y, 2); //立春日期

        //月柱 1900年1月小寒以前为 丙子月(60进制12)
        int firstNode = getDateByFestivalFromYear(y, m * 2);//返回当月「节」为几日开始
        cM = getHeavenlyAndEarthly((y - 1900) * 12 + m + 12);

        lM2 = (y - 1900) * 12 + m + 12;
        //当月一日与 1900/1/1 相差天数
        //1900/1/1与 1970/1/1 相差25567日, 1900/1/1 日柱为甲戌日(60进制10)
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        df2.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = df2.parse("" + y + "-" + (m+1) + "-" + "01" + " 00:00:00");

        long dayCyclical = date.getTime() / 86400000 + 25567 + 10;
        //// long dayCyclical =date.getTime() / 86400000 + 25567 + 10;
        SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (int i = 0; i < this.length; i++) {
            if(i==18){
                int b = 5;
            }
            if (lD > lX) {
                sDObj= df3.parse("" + y + "-" + DateUtils.getFormatString(m+1) + "-" + DateUtils.getFormatString(i+1) + " 00:00:00");   //当月一日日期
                lDObj = new Lunar(sDObj);       //农历
                lY = lDObj.getYear();           //农历年
                lM = lDObj.getMonth();          //农历月
                lD = lDObj.getDay();            //农历日
                lL = lDObj.isLeap();            //农历是否闰月
                lX = lL ? getYearLeapMonthDays(lY) : getYearMonthDays(lY, lM); //农历当月最后一天
                if (n == 0) firstLM = lM;
                lDPOS[n++] = i - lD + 1;
            }

            //依节气调整二月分的年柱, 以立春为界
            if (m == 1 && (i + 1) == term2) {
                cY = getHeavenlyAndEarthly(y - 1900 + 36);
                lY2 = (y - 1900 + 36);
            }
            //依节气月柱, 以「节」为界
            if ((i + 1) == firstNode) {
                cM = getHeavenlyAndEarthly((y - 1900) * 12 + m + 13);
                lM2 = (y - 1900) * 12 + m + 13;
            }
            //日柱
            cD = getHeavenlyAndEarthly(dayCyclical + i);
            lD2 = (dayCyclical + i);
            Element element = new Element(y, m + 1, i + 1, (HolidayConstants.CHINESE_NUMBERS[(i + this.firstWeek) % 7]),
                    lY, lM, lD++, lL,
                    cY, cM, cD, DateUtils.isToday(y, (m + 1), d), isLeapYear(y));
            element.setChineseDay(getChineseDate(element.getLunarDay()));
            int paramterLy2 = lY2 == null ? -1 : (lY2 % 12);
            int paramterLm2 = lM2 == null ? -1 : lM2 % 12;
            long paramterLd2 = lD2 == null ? -1 : lD2 % 12;
            int paramterLy2b = lY2 == null ? -1 : lY2 % 10;
            int paramterLy2c = (int) (lD2 == null ? -1 : lD2 % 10);
            int paramterLld = lD == null ? -1 : lD - 1;
            element.setSgz5(getCalendarRemark(paramterLy2, paramterLm2, (int) paramterLd2, paramterLy2b,paramterLy2c , lM,paramterLld , m + 1, cs1 == null ? -1 : cs1));
            element.setSgz3(getLunarCalendar(lM2 % 12, (int) ((lD2) % 12)));
            elements.add(element);
        }
        //节气
        tmp1 = getDateByFestivalFromYear(y, m * 2) - 1;
        tmp2 = getDateByFestivalFromYear(y, m * 2 + 1) - 1;
        elements.get(tmp1).setSolarTerms(HolidayConstants.SOLAR_TERM[m * 2]);
        elements.get(tmp2).setSolarTerms(HolidayConstants.SOLAR_TERM[m * 2 + 1]);
        if (m == 3) elements.get(tmp1).setColor(HolidayConstants.COLOR_RED); //清明颜色

        //公历节日
        for (String i : HolidayConstants.NATIONAL_HOLIDAY){
            Matcher matcher = HolidayConstants.NATIONAL_CALENDAR_PATTERN.matcher(i);
            if (matcher.matches()) {
                if(HolidayConstants.XIAN_INCIDENT.equals(i)){
                    int j = 2;
                }
                if (Integer.valueOf(matcher.group(1)).intValue() == (m + 1)) {
                    elements.get(Integer.valueOf(matcher.group(2)) - 1).setSolarFestival(elements.get(Integer.valueOf(matcher.group(2)) - 1).getSolarFestival() + matcher.group(4)+"");
                    String tmp = matcher.group(3);
                    if ("*".equals(tmp)){
                        String day =  matcher.group(2);
                        elements.get(Integer.valueOf(day) - 1).setColor(HolidayConstants.COLOR_RED);
                    }
                }
            }
        }
        //农历节日
        for (String i : HolidayConstants.LUNAR_HOLIDAY){
            Matcher matcher = HolidayConstants.LUNAR_CALENDAR_PATTERN.matcher(i);
            if (matcher.matches()) {
                tmp1 = Integer.valueOf(matcher.group(1)) - firstLM;
                if (tmp1 == -11) tmp1 = 1;
                if (tmp1 >= 0 && tmp1 < n) {
                    tmp2 = lDPOS[tmp1] +Integer.valueOf(matcher.group(2)) - 1;
                    if (tmp2 >= 0 && tmp2 < this.length) {
                        elements.get(tmp2).setLunarFestival(elements.get(tmp2).getLunarFestival() + matcher.group(4));
                        if (matcher.group(3).equals("*")) elements.get(tmp2).setColor(HolidayConstants.COLOR_RED);
                    }
                }
            }
        }

        //复活节只出现在3或4月
        if (m == 2 || m == 3) {
            Easter estDay = new Easter(y);
            if (m == estDay.getMonth())
                elements.get(estDay.getDate() - 1).setSolarFestival(elements.get(estDay.getDate() - 1).getSolarFestival() + " " + HolidayConstants.EASTER_CALENDAR);
        }
        //黑色星期五
        if ((this.firstWeek + 12) % 7 == 5)
            elements.get(12).setSolarFestival(elements.get(12).getSolarFestival() + HolidayConstants.BLACK_FRIDAY);
    }

    /**
     * 某年的第n个节气为几日(从0小寒起算)
     */
    public int getDateByFestivalFromYear(int y, int  n) throws ParseException {
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df2.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = df2.parse("1900-01-06 02:05:00");
        Long utcTime2 = date.getTime();
        BigDecimal time2=new BigDecimal(31556925974.7).multiply(new BigDecimal(y - 1900)).add(new BigDecimal( HolidayConstants.TERM_INFO[n]).multiply(BigDecimal.valueOf(60000L)));
        BigDecimal time=time2.add(BigDecimal.valueOf(utcTime2));
        Date offDate = new Date(time.longValue());
        Calendar cal = Calendar.getInstance() ;
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.setTime(offDate);
        int utcDate=cal.get(Calendar.DATE);
        //日期从0算起
        return utcDate;
    }

    /**
     * 返回农历 y年闰哪个月 1-12 , 没闰返回 0
     */
    public Long leapMonth(int y) {
        long lm = HolidayConstants.LUNAR_INFO[y - 1900] & 0xf;
        return(lm == 0xf ? 0 : lm);
    }

    /**
     * 返回农历 y年的总天数
     */
    public Long getLunarCalendarYearDays(int y) {
        long i, sum = 348;
        for (i = 0x8000; i > 0x8; i >>= 1) sum += (HolidayConstants.LUNAR_INFO[y - 1900] & i)!=0 ? 1 : 0;
        return(sum + getYearLeapMonthDays(y));
    }


    /**
     * 返回农历 y年闰月的天数
     */
    public int getYearLeapMonthDays(int y) {
        if (leapMonth(y)!=0) return((HolidayConstants.LUNAR_INFO[y - 1899] & 0xf) == 0xf ? 30 : 29);
        else return 0;
    }

    /**
     * 返回农历 y年m月的总天数
     */
    protected int getYearMonthDays(int y, int m) {
        return((HolidayConstants.LUNAR_INFO[y - 1900] & (0x10000 >> m))!=0 ? 30 : 29 );
    }

    /**
     * 返回公历 y年某m+1月的天数
     */
    public long getSolarMonthDays(int y, int m) {
        if (m == 1)
            return(((y % 4 == 0) && (y % 100 != 0) || (y % 400 == 0)) ? 29 : 28);
        else
            return(HolidayConstants.SOLAR_MONTH[m]);
    }

    /**
     * 判断当前年份是否是闰年
     */
    public boolean isLeapYear(int y){
        return ((y % 4 == 0) && (y % 100 != 0) || (y % 400 == 0));
    }

    /**
     * 返回阴历 (y年, m+1月)
     */
    public char getLunarCalendar(int num, int num2) {
        if (num == 0) return(HolidayConstants.JCNAME0[num2]);
        if (num == 1) return(HolidayConstants.JCNAME1[num2]);
        if (num == 2) return(HolidayConstants.JCNAME2[num2]);
        if (num == 3) return(HolidayConstants.JCNAME3[num2]);
        if (num == 4) return(HolidayConstants.JCNAME4[num2]);
        if (num == 5) return(HolidayConstants.JCNAME5[num2]);
        if (num == 6) return(HolidayConstants.JCNAME6[num2]);
        if (num == 7) return(HolidayConstants.JCNAME7[num2]);
        if (num == 8) return(HolidayConstants.JCNAME8[num2]);
        if (num == 9) return(HolidayConstants.JCNAME9[num2]);
        if (num == 10) return(HolidayConstants.JCNAME10[num2]);
        if (num == 11) return(HolidayConstants.JCNAME11[num2]);
        return '0';
    }

    /**
     * 获取日期的标签
     */
    public String getCalendarRemark(int yy, int  mm, int dd, int y, int d, int m, int dt, int  nm, int nd) {
        int dy = d  + dd;
        if ((yy == 0 && dd == 6) || (yy == 6 && dd == 0) || (yy == 1 && dd == 7) || (yy == 7 && dd == 1) || (yy == 2 && dd == 8) || (yy == 8 && dd == 2) || (yy == 3 && dd == 9) || (yy == 9 && dd == 3) || (yy == 4 && dd == 10) || (yy == 10 && dd == 4) || (yy == 5 && dd == 11) || (yy == 11 && dd == 5)) {
            return "<FONT color=#0000A0>日值岁破 大事不宜</font>";
        }
        else if ((mm == 0 && dd == 6) || (mm == 6 && dd == 0) || (mm == 1 && dd == 7) || (mm == 7 && dd == 1) || (mm == 2 && dd == 8) || (mm == 8 && dd == 2) || (mm == 3 && dd == 9) || (mm == 9 && dd == 3) || (mm == 4 && dd == 10) || (mm == 10 && dd == 4) || (mm == 5 && dd == 11) || (mm == 11 && dd == 5)) {
            return "<FONT color=#0000A0>日值月破 大事不宜</font>";
        }
        else if ((y == 0 && dy == 911) || (y == 1 && dy == 55) || (y == 2 && dy == 111) || (y == 3 && dy == 75) || (y == 4 && dy == 311) || (y == 5 && dy == 9) || (y == 6 && dy == 511) || (y == 7 && dy == 15) || (y == 8 && dy == 711) || (y == 9 && dy == 35)) {
            return "<FONT color=#0000A0>日值上朔 大事不宜</font>";
        }
        else if ((m == 1 && dt == 13) || (m == 2 && dt == 11) || (m == 3 && dt == 9) || (m == 4 && dt == 7) || (m == 5 && dt == 5) || (m == 6 && dt == 3) || (m == 7 && dt == 1) || (m == 7 && dt == 29) || (m == 8 && dt == 27) || (m == 9 && dt == 25) || (m == 10 && dt == 23) || (m == 11 && dt == 21) || (m == 12 && dt == 19)) {
            return "<FONT color=#0000A0>日值杨公十三忌 大事不宜</font>";
        }
        else {
            return "0";
        }
    }

    /**
     * 传入 offsenew Datet 返回干支, 0 = 甲子
     */
    public String getHeavenlyAndEarthly(long num) {
        return(HolidayConstants.TIAN_GAN[(int) (num % 10)] + HolidayConstants.DI_ZHI[(int) (num % 12)]);
    }

    /**
     * 中文日期
     */
    public String getChineseDate(int d) {
        String  s;
        switch (d) {
            case  10:
                s = "初十";  break;
            case  20:
                s = "二十";  break;
            case  30:
                s = "三十";  break;
            default  :
                s = HolidayConstants.LUNAR_CALENDAR[Double.valueOf(Math.floor(d / 10)).intValue()];
                s += HolidayConstants.CHINESE_NUMBERS[d % 10];
        }
        return(s);
    }
}
