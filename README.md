# 作者简介: 
冰河，高级软件架构师，大数据架构师，Java编程专家，Spring、MySQL内核专家，Mykit系列开源框架创始人、首席架构师及开发者，Android开源消息组件Android-MQ独立作者，国内知名开源分布式数据库中间件Mycat核心架构师、开发者，精通Java, C, C++, Python, Hadoop大数据生态体系，熟悉MySQL、Redis内核，Android底层架构。多年来致力于分布式系统架构、微服务、分布式数据库、大数据技术的研究，曾主导过众多分布式系统、微服务及大数据项目的架构设计、研发和实施落地。在高并发、高可用、高可扩展性、高可维护性和大数据等领域拥有丰富的实战经验。对Hadoop、Spark、Storm等大数据框架源码进行过深度分析并具有丰富的实战经验，《海量数据处理与大数据技术实战》作者。

# 作者联系方式
QQ：2711098650

# 框架简述
纯算法可以获取到1900-2100年之间任意日期的详细信息，包括：星期、节假日、是否闰年和闰月、公历和农历、对应的二十四节气以及天干地支信息等。  
程序的入口类为```io.mykit.holiday.core.MykitCalendar```  
调用方法如下：  
```
Element element = MykitCalendar.getCalendarDetail("2019-09-10","yyyy-MM-dd", HolidayConstants.JDK_17);
Element element = MykitCalendar.getCalendarDetail("2019-09-10","yyyy-MM-dd", HolidayConstants.JDK_18);
Element element = MykitCalendar.getCalendarDetail(new Date(), HolidayConstants.JDK_17);
Element element = MykitCalendar.getCalendarDetail(new Date(), HolidayConstants.JDK_18);
```  
返回的结果数据如下：  
```
{
    "chineseDay": "十二",
    "chineseMonth": "癸酉",
    "chineseYear": "己亥",
    "color": "",
    "gregorianDay": 10,
    "gregorianMonth": 9,
    "gregorianYear": 2019,
    "leap": false,
    "leapYear": false,
    "lunarDay": 12,
    "lunarDayChinese": "十二",
    "lunarFestival": "",
    "lunarMonth": 8,
    "lunarMonthChinese": "八",
    "lunarYear": 2019,
    "sgz3": "除",
    "sgz5": "0",
    "solarFestival": " 教师节",
    "solarTerms": "",
    "today": true,
    "week": "二"
}
```
## 提示：
具体测试用例请参见```io.mykit.holiday.test.MykitCalendarTest```类
