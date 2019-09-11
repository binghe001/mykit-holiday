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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;

/**
 * @author binghe
 * @version 1.0.0
 * @description 计算农历节假日信息
 *      SimpleCalendar.Element element=SimpleCalendar.getCalendarDetail("2019-03-03","yyyy-MM-dd");
 *      element=SimpleCalendar.getCalendarDetail("2019-03-04","yyyy-MM-dd");
 */
public class SimpleCalendar extends BaseCalendar {

    //存储结果数据的集合
    private List<Element> elements = new ArrayList<Element>();
    //公历当月天数
    private Long length;
    //公历当月1日星期几
    private int firstWeek;

    public List<Element> getElements() {
        return elements;
    }

    public void calendar(Calendar cal) throws ParseException {
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH);
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
                    cY, cM, cD, DateUtils.isToday(cal.getTime()), isLeapYear(y));
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
}
