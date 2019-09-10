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

import io.mykit.holiday.core.MykitCalendar;
import io.mykit.holiday.utils.date.DateUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public void testDateCalendar() throws Exception{
        logger.info(MykitCalendar.getCalendarDetail(new Date()).toString());
    }
    @Test
    public void testStringCalendar() throws Exception{
        logger.info(MykitCalendar.getCalendarDetail("2019-09-10", DateUtils.DATE_FORMAT).toString());
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
}
