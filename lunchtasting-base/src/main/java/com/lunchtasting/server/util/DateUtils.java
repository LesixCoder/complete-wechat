package com.lunchtasting.server.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateFormatUtils;


public class DateUtils {
	
	/**
	 * 静态常量
	 */
	public static final String C_TIME_PATTON_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public static final String C_DATE_PATTON_DEFAULT = "yyyy-MM-dd";
    public static final String MM1DD = "MM.dd";  
    public static final String YYYY1MM1DD = "yyyy.MM.dd"; 
	
	public static final int C_ONE_SECOND = 1000;
	public static final int C_ONE_MINUTE = 60 * C_ONE_SECOND;
	public static final long C_ONE_HOUR = 60 * C_ONE_MINUTE;
	public static final long C_ONE_DAY = 24 * C_ONE_HOUR;
	
	/**
	 * 计算当前月份的最大天数
	 * @return
	 */
	public static int findMaxDayInMonth() {
		return findMaxDayInMonth(0, 0);
	}
	
	/**
	 * 计算指定日期月份的最大天数
	 * @param date
	 * @return
	 */
	public static int findMaxDayInMonth(Date date) {
		if (date == null) {
			return 0;
		}
		return findMaxDayInMonth(date2Calendar(date));
	}
	
	/**
	 * 计算指定日历月份的最大天数
	 * @param calendar
	 * @return
	 */
	public static int findMaxDayInMonth(Calendar calendar) {
		if (calendar == null) {
			return 0;
		}
		
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 计算当前年某月份的最大天数
	 * @param month
	 * @return
	 */
	public static int findMaxDayInMonth(int month) {
		return findMaxDayInMonth(0, month);
	}
	
	/**
	 * 计算某年某月份的最大天数
	 * @param year
	 * @param month
	 * @return
	 */
	public static int findMaxDayInMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		if (year > 0) {
			calendar.set(Calendar.YEAR, year);
		}
		
		if (month > 0) {
			calendar.set(Calendar.MONTH, month - 1);
		}
		
		return findMaxDayInMonth(calendar);
	}
	
	/**
	 * Calendar 转换为 Date
	 * @param calendar
	 * @return
	 */
	public static Date calendar2Date(Calendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.getTime();
	}
	
	/**
	 * Date 转换为 Calendar
	 * @param date
	 * @return
	 */
	public static Calendar date2Calendar(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	/**
	 * 拿到默认格式的SimpleDateFormat
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormat() {
		return getSimpleDateFormat(null);
	}
	
	/**
	 * 拿到指定输出格式的SimpleDateFormat
	 * @param format
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormat(String format) {
		SimpleDateFormat sdf;
		if (format == null) {
			sdf = new SimpleDateFormat(C_TIME_PATTON_DEFAULT);
		} else {
			sdf = new SimpleDateFormat(format);
		}
		
		return sdf;
	}
	
	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @author qzw
	 */
	public static Date formatDate2Date(Date date,String format) {
		return formatStr2Date(formatDate2Str(date, format), format);
		//return parseDate(format(date, "yyyyMMdd"), "yyyyMMdd");
	}
	
	/**
	 * 转换当前时间为默认格式
	 * @return
	 */
	public static String formatDate2Str() {
		return formatDate2Str(new Date());
	}
	
	/**
	 * 转换指定时间为默认格式
	 * @param date
	 * @return
	 */
	public  static String formatDate2Str(Date date) {
		return formatDate2Str(date, C_TIME_PATTON_DEFAULT);
	}
	
	/**
	 * 转换指定时间为指定格式
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate2Str(Date date, String format) {
		if (date == null) {
			return null;
		}
		
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		SimpleDateFormat sdf = getSimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 转换默认格式的时间为Date
	 * @param dateStr
	 * @return
	 */
	public static Date formatStr2Date(String dateStr) {
		return formatStr2Date(dateStr, null);
	}
	
	/**
	 * 转换指定格式的时间为Date
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date formatStr2Date(String dateStr, String format) {
		if (dateStr == null) {
			return null;
		}
		
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		SimpleDateFormat sdf = getSimpleDateFormat(format);
		return sdf.parse(dateStr, new ParsePosition(0));
	}
	
	/**
	 * 转换默认格式的时间为指定格式时间
	 * @param dateStr
	 * @param defineFormat
	 * @return
	 */
	public static String formatDefault2Define(String dateStr, String defineFormat) {
		return formatSource2Target(dateStr, C_TIME_PATTON_DEFAULT, defineFormat);
	}
	
	/**
	 * 转换源格式的时间为目标格式时间
	 * @param dateStr
	 * @param sourceFormat
	 * @param targetFormat
	 * @return
	 */
	public static String formatSource2Target(String dateStr, String sourceFormat, String targetFormat) {
		Date date = formatStr2Date(dateStr, sourceFormat);
		return formatDate2Str(date, targetFormat);
	}
	
	/**
	 * 计算当天是该年中的第几周
	 * @return
	 */
	public static int findWeeksNoInYear() {
		return findWeeksNoInYear(new Date());
	}
	
	/**
	 * 计算指定日期是该年中的第几周
	 * @param date
	 * @return
	 */
	public static int findWeeksNoInYear(Date date) {
		if (date == null) {
			return 0;
		}
		return findWeeksNoInYear(date2Calendar(date));
	}
	
	/**
	 * 计算指定日历是该年中的第几周
	 * @param calendar
	 * @return
	 */
	public static int findWeeksNoInYear(Calendar calendar) {
		if (calendar == null) {
			return 0;
		}
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * 计算一年中的第几星期是几号 
	 * @param year
	 * @param weekInYear
	 * @param dayInWeek
	 * @return
	 */
	public static Date findDateInWeekOnYear(int year, int weekInYear, int dayInWeek) {
		Calendar calendar = Calendar.getInstance();
		if (year > 0) {
			calendar.set(Calendar.YEAR, year);
		}
		
		calendar.set(Calendar.WEEK_OF_YEAR, weekInYear);
		calendar.set(Calendar.DAY_OF_WEEK, dayInWeek);
		
		return calendar.getTime();
	}
	
	/**
	 * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * @param days
	 * @return
	 */
	public static Date dayBefore2Date(int days) { 
		Date date = new Date();
		return dayBefore2Object(days, null, date);
	}
	
	/**
	 * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * @param days
	 * @return
	 */
	public static String dayBefore2Str(int days) {
		String string = formatDate2Str();
		return dayBefore2Object(days, null, string);
	}
	
	/**
	 * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * @param days
	 * @param format
	 * @param instance
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T dayBefore2Object(int days, String format, T instance) {
		Calendar calendar = Calendar.getInstance();
		if (days == 0) {
			return null;
		}
		
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		
		calendar.add(Calendar.DATE, days);
		if (instance instanceof Date) {
			return (T)calendar.getTime();
		} else if (instance instanceof String) {
			return (T)formatDate2Str(calendar2Date(calendar), format);
		}
		return null;
	}
	
	/**
	 * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date defineDayBefore2Date(Date date, int days) {
		Date dateInstance = new Date();
		return defineDayBefore2Object(date, days, null, dateInstance);
	}
	
	/**
	 * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * @param date
	 * @param days
	 * @return
	 */
	public static String defineDayBefore2Str(Date date, int days) {
		String stringInstance = formatDate2Str();
		return defineDayBefore2Object(date, days, null, stringInstance);
	}
	
	/**
	 * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * @param <T>
	 * @param date
	 * @param days
	 * @param format
	 * @param instance
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T defineDayBefore2Object(Date date, 
			int days, String format, T instance) {
		if (date == null || days == 0) {
			return null;
		}
		
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		
		Calendar calendar = date2Calendar(date);
		calendar.add(Calendar.DATE, days);
		if (instance instanceof Date) {
			return (T)calendar.getTime();
		} else if (instance instanceof String) {
			return (T)formatDate2Str(calendar2Date(calendar), format);
		}
		return null;
	}
	
	/**
	 * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * @param months
	 * @return
	 */
	public static Date monthBefore2Date(int months) {
		Date date = new Date();
		return monthBefore2Object(months, null, date);
	}
	
	/**
	 * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * @param months
	 * @return
	 */
	public static String monthBefore2Str(int months) {
		String string = formatDate2Str();
		return monthBefore2Object(months, null, string);
	}
	
	/**
	 * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * @param <T>
	 * @param months
	 * @param format
	 * @param instance
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T monthBefore2Object(int months, String format, T instance) {
		if (months == 0) {
			return null;
		}
		
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, months);
		
		if (instance instanceof Date) {
			return (T)calendar.getTime();
		} else if (instance instanceof String) {
			return (T)formatDate2Str(calendar2Date(calendar), format);
		}
		
		return null;
	}
	
	/**
	 * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date defineMonthBefore2Date(Date date, int months) {
		Date dateInstance = new Date();
		return defineMonthBefore2Object(date, months, null, dateInstance);
	}
	
	/**
	 * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * @param date
	 * @param months
	 * @return
	 */
	public static String defineMonthBefore2Str(Date date, int months) {
		String stringInstance = formatDate2Str();
		return defineMonthBefore2Object(date, months, null, stringInstance);
	}
	
	/**
	 * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * @param <T>
	 * @param date
	 * @param months
	 * @param format
	 * @param instance
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T defineMonthBefore2Object(Date date,
			int months, String format, T instance) {
		if (months == 0) {
			return null;
		}
		
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		
		Calendar calendar = date2Calendar(date);
		calendar.add(Calendar.MONTH, months);
		
		if (instance instanceof Date) {
			return (T)calendar.getTime();
		} else if (instance instanceof String) {
			return (T)formatDate2Str(calendar2Date(calendar), format);
		}
		
		return null;
	}
	
	/**
	 * 计算两个日期直接差的天数
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static int caculate2Days(Date firstDate, Date secondDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstDate);
		int dayNum1 = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(secondDate);
		int dayNum2 = calendar.get(Calendar.DAY_OF_YEAR);
		return Math.abs(dayNum1 - dayNum2);
	}
	
	/**
	 * 计算两个日期直接差的天数
	 * @param firstCalendar
	 * @param secondCalendar
	 * @return
	 */
	public static int caculate2Days(Calendar firstCalendar, Calendar secondCalendar) {
		if (firstCalendar.after(secondCalendar)) {
			Calendar calendar = firstCalendar;
			firstCalendar = secondCalendar;
			secondCalendar = calendar;
		}
		
		long calendarNum1 = firstCalendar.getTimeInMillis();
		long calendarNum2 = secondCalendar.getTimeInMillis();
		return Math.abs((int)((calendarNum1 - calendarNum2)/C_ONE_DAY));
	}
	
	public  static String getOrderNum(){
		return formatDate2Str(new Date(), "yyyyMMddHHmmss")+RandomUtils.nextInt(1000);
	}
	
	
	
  public static final long DAY_IN_MILLISECOND = 24 * 60 * 60 * 1000;
  
	public static Date addDay(Date date,int day){
		return org.apache.commons.lang.time.DateUtils.addDays(date, day);
	}
  
	public static Date addWeek(Date date,int week){
		return org.apache.commons.lang.time.DateUtils.addWeeks(date, week);
	}
	public static Date addMonth(Date date,int month){
		return org.apache.commons.lang.time.DateUtils.addMonths(date, month);
	}
	
	
	public static Date addTime(Date date, int hours, int mins, int seconds) {
		Date d = org.apache.commons.lang.time.DateUtils.addHours(date, hours);
		d = org.apache.commons.lang.time.DateUtils.addMinutes(d, mins);
		return org.apache.commons.lang.time.DateUtils.addSeconds(d, seconds);
	}
	
	public static Date getPreMonthDate(Date date)  
	{  
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(date);  
		int month = calendar.get(Calendar.MONTH);  
		calendar.set(Calendar.MONTH,month-1);  
		return calendar.getTime();  
	}  
		   
	public static Date getPreYearDate(Date date)  
	{  
		Calendar calendar=Calendar.getInstance();  
		calendar.setTime(date);  
		int year=calendar.get(Calendar.YEAR);  
		calendar.set(Calendar.YEAR,year-1);  
		return calendar.getTime();  
  }   
	
	public static int get(Date date, int field) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(field);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date,"yyyy-MM-dd");
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date,String pattern) {
		return DateFormatUtils.format(date, pattern);
	}
	
	/**
	 * compare the two dates, and return the subtraction between d1 and d2(d1 - d2)
	 * result > 0 when d1 > d2 and result < 0 when d1 < d2 
	 * 
	 * @param d1
	 *            Date
	 * @param d2
	 *            Date
	 * @return int
	 */
	public static int compareDateOnDay(Date d1, Date d2) {
		if(d1.getTime() == d2.getTime())
			return 0;
		d1 = org.apache.commons.lang.time.DateUtils.truncate(d1, Calendar.DATE);
		d2 = org.apache.commons.lang.time.DateUtils.truncate(d2, Calendar.DATE);
		long l1 = d1.getTime();
		long l2 = d2.getTime();
		return (int) ((l1 - l2)/DAY_IN_MILLISECOND);
	}
	
	/**
	 * compare the two dates, and return the subtraction between the dates' month
	 * always return > 0
	 * 
	 * @param d1
	 *            Date
	 * @param d2
	 *            Date
	 * @return int
	 */
	public static int compareDateOnMonth(Date d1, Date d2) {
		if (d1.getTime() == d2.getTime()) {
			return 0; 
		}
		int flag = -1;
		// compare the dates, and put the smaller before
		if (d1.getTime() > d2.getTime()) {
			Date temp = d1;
			d1 = d2;
			d2 = temp;
			flag = 1;
		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH);
		int month2 = c2.get(Calendar.MONTH);
		int months = 0;
		if(y1==y2){
			
			months = month2-month1;
		
		}else{
			
			months = (y2-y1-1)*12+ (12-month1)+month2;
		
		}
		
		
		return months * flag;
	}
  
	/**
	 * judge the year whether is leap year
	 * 
	 * @param year
	 *            int year
	 * @return boolean
	 */
	public static boolean isLeapYear(int year) {
		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			return true;
		}
		return false;
		
	}

	/**
	 * return the days of the year gevin
	 * 
	 * @param year
	 *            int year
	 * @return int 
	 */
	public static int getYearDays(int year) {
		if (isLeapYear(year)) {
			return 366;
		}
		return 365;
	}
	
  /**
   * judge whether the two dates are in same day
   * 
   * @param date1
   * @param date2
   * @return
   */
  public static boolean isSameDay(Date date1, Date date2) {
     return org.apache.commons.lang.time.DateUtils.isSameDay(date1, date2);
  }
  
  public static Date truncate(Date d, int field) {
  	return org.apache.commons.lang.time.DateUtils.truncate(d, field);
  }

  public static boolean isLastDayOfMonth(Date date) {
  	return isFirstDayOfMonth(addDay(date, 1));
  }
  
  public static boolean isFirstDayOfMonth(Date date) {
  	return get(date, Calendar.DAY_OF_MONTH) == 1;
  }
  
  //add
	public static Date getLastMonthDay(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.MONTH,month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	
	public static Date getLastMonthDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	
	public static Date getFirstMonthDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	public static Date getFirstMonthDay(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.MONTH,month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	public static Date getFirstWeekDay(int year, int week) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.WEEK_OF_YEAR, week);
		calendar.set(calendar.DAY_OF_WEEK,1);
		return addDay(calendar.getTime(), 1);
	}
	
	public static Date getFirstWeekDay(Date date) {
		Calendar calendar = Calendar.getInstance();	
		calendar.setTime(date);
		calendar.set(calendar.DAY_OF_WEEK,1);
		return addDay(calendar.getTime(), 1);
	}
	
	public static Date getLastWeekDay(int year, int week) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.WEEK_OF_YEAR, week);
		calendar.set(calendar.DAY_OF_WEEK,7);
		return addDay(calendar.getTime(), 1);
	}
	
	public static Date getLastWeekDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.DAY_OF_WEEK,7);
		return addDay(calendar.getTime(), 1);
	}
	
	public static int getWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH)+1;
	}
	
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	
	public static int getInterval(Date d1, Date d2) {
		int betweenDays = 0; 
		Calendar c1 = Calendar.getInstance(); 
		Calendar c2 = Calendar.getInstance(); 
		c1.setTime(d1); 
		c2.setTime(d2); 
		// 保证第二个时间一定大于第一个时间 
		if(c1.after(c2)){ 
		c1 = c2; 
		c2.setTime(d1); 
		} 
		int betweenYears = c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR); 
		betweenDays = c2.get(Calendar.DAY_OF_YEAR)-c1.get(Calendar.DAY_OF_YEAR); 
		for(int i=0;i<betweenYears;i++){ 
		c1.set(Calendar.YEAR,(c1.get(Calendar.YEAR)+1));
		betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR); 
		}
		
		return betweenDays;
	}
	
	public static List<Date> getDateList(Date startDate, Date endDate) {
		List<Date> dates = new ArrayList<Date>();
		
		int betweenDays = DateUtils.getInterval(startDate, endDate);
		for (int i = 0; i <= betweenDays; i++) {
			Date day = DateUtils.addDay(startDate, i);
			
			if (day.getTime() >= startDate.getTime() && day.getTime() <= endDate.getTime()) {
				dates.add(day);
			}
		}
		
		return dates;
	}
	
	public static int getMonthInterval(Date startDate, Date endDate) {
		int betweenMonths = 0;
		Calendar c1 = Calendar.getInstance(); 
		Calendar c2 = Calendar.getInstance(); 
		
		c1.setTime(startDate);
		c2.setTime(endDate);
		// 保证第二个时间一定大于第一个时间 
		if(c1.after(c2)){ 
			c1 = c2; 
			c2.setTime(startDate); 
		} 
		
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);
		
		int m1 = c1.get(Calendar.MONTH);
		int m2 = c2.get(Calendar.MONTH);
		
		if (y2 > y1) {
			betweenMonths += (y2 - y1) * 12;
		}
		betweenMonths += (m2 - m1);
		
		return betweenMonths;
	}
	
	public static int getWeekInterval(Date startDate, Date endDate) {
		int betweenWeeks = 0;
		Calendar c1 = Calendar.getInstance(); 
		Calendar c2 = Calendar.getInstance(); 
		
		c1.setTime(startDate);
		c2.setTime(endDate);
		// 保证第二个时间一定大于第一个时间 
		if(c1.after(c2)){ 
			c1 = c2; 
			c2.setTime(startDate); 
		}
		
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);
		
		int w1 = c1.get(Calendar.WEEK_OF_YEAR);
		int w2 = c2.get(Calendar.WEEK_OF_YEAR);
		
		betweenWeeks += (w2 - w1);
		int betweenYears = y2 - y1;
		for (int i = 0; i < betweenYears; i++) {
			c1.set(Calendar.YEAR,(c1.get(Calendar.YEAR)+1));
			betweenWeeks += c1.getMaximum(Calendar.WEEK_OF_YEAR);
		}
		
		return betweenWeeks;
	}
	
	/**
     * 返回小时
     *
     * @param date
     * 日期
     * @return 返回小时
     */
    public static int getHour(java.util.Date date)
    {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        return c.get(java.util.Calendar.HOUR_OF_DAY);
    }
    
    public static void main(String[] args) {
		System.out.println(DateUtils.getHour(new Date())-DateUtils.getHour(new Date()));
	}
	
    /**
     * 返回毫秒
     *
     * @param date
     * 日期
     * @return 返回毫秒
     */
    public static long getMillis(java.util.Date date)
    {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }
    
	/**
     * 返回分钟
     *
     * @param date
     * 日期
     * @return 返回分钟
     */
    public static int getMinute(java.util.Date date)
    {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        return c.get(java.util.Calendar.MINUTE);
    }
    /**
     * 返回秒钟
     *
     * @param date
     * 日期
     * @return 返回秒钟
     */
    public static int getSecond(java.util.Date date)
    {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        return c.get(java.util.Calendar.SECOND);
    }
  
    /**
     * 日期相加
     *
     * @param date
     * 日期
     * @param day
     * 天数
     * @return 返回相加后的日期
     */
    public static java.util.Date addDate(java.util.Date date, int day)
    {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
        return c.getTime();
    }
    /**
     * 日期相减
     *
     * @param date
     * 日期
     * @param date1
     * 日期
     * @return 返回相减后的日期
     */
    public static int diffDate(java.util.Date date, java.util.Date date1)
    {
        return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
    }
    public static int diffDateToHour(Date date, Date date1)
    {
        return (int) ((getMillis(date) - getMillis(date1)) / (1000 * 60* 60));
    }
    /**
     * 判断是否是同一年 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDateYear(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        return isSameYear;
    }
    /**
     * 同一年 去掉年
     * @param date
     * @return
     */
    public static String datetotai(Date date){  
        SimpleDateFormat sdfMD = new SimpleDateFormat(DateUtils.MM1DD);  
        SimpleDateFormat sdfYMD = new SimpleDateFormat(DateUtils.YYYY1MM1DD); 
        Date now = new Date();
        String str = date.toString();
        try{
	        if(DateUtils.isSameDateYear(date,now)){
	        	  str = sdfMD.format(date);  
	        }else{
	        	  str = sdfYMD.format(date);  
	        }
        }catch (Exception e) {
			// TODO: handle exception
        	 return str;  
		}
        return str;  
    } 
	
}