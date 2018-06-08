package com.lunchtasting.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间操作工具类
 * @author xuqian
 *
 */
public class DateUtil {
	public static final String DATE_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DATE_PATTERN_YYYYMMDD = "yyyyMMdd";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String MM_DD_HH_MM = "MM月dd号 HH:mm";
	public static final String HH_MM="HH:mm";
	public static final String HH_MM_SS = "HH-mm-ss";
	public static final String MM_DD="MM-dd";
	public static final String YYYY_MM_DD="yyyy年MM月dd日";
	public static final String CHINESE_YYYY_MM_DD_HH_MM = "yyyy年MM月dd日  HH时mm分";
	
	public static final  SimpleDateFormat CHINESE_DATE_FORMAT = new SimpleDateFormat(CHINESE_YYYY_MM_DD_HH_MM);
	

	/**
	 * 
	 * 将一个日期字符按照pattern格式转成Date
	 * @param dateStr
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date convertStringTODate(String dateStr, String pattern)
			throws ParseException {
		if((dateStr==null||dateStr.equals("")) ||(pattern==null||pattern.equals(""))){
			return null;
		}
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		return sf.parse(dateStr);
	}
	
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 
	 * 将一个Date按照pattern转成日期字符
	 * @param date
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static String convertDateToString(Date date, String pattern){
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		sf.setLenient(false);
		return sf.format(date);
	}

	/**
	 * 获取当前时间
	 * @return
	 */
	public static Date getCurrentDateTime() {
		Calendar calNow = Calendar.getInstance();
		Date dtNow = calNow.getTime();
		return dtNow;
	}

	/**
	 *
	 * 按照pattern格式获取当前时间
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date getCurrentDateTime(String pattern) throws ParseException {
		return convertStringTODate(getCurrentDateString(pattern), pattern);
	}

	/**
	 *将当前时间转成 yyyy-MM-dd 格式的日期字符串
	 * @return
	 * @throws ParseException
	 */
	public static String getCurrentDateString() throws ParseException {
		return getCurrentDateString(DATE_PATTERN_YYYY_MM_DD);
	}

	/**
	 * 将当前时间按照pattern格式转成日期字符串
	 * @return
	 * @throws ParseException
	 */
	public static String getCurrentDateString(String pattern)
			throws ParseException {
		return convertDateToString(getCurrentDateTime(), pattern);
	}

	/**
	 * 获取指定时间的后一天
	 * @return
	 * @throws ParseException
	 */

	public static Date getAfterDate(String date) throws ParseException {
		return getAfterDate(convertStringTODate(date,
				DateUtil.DATE_PATTERN_YYYY_MM_DD));
	}

	/**
	 * 获取指定时间的前一天
	 * @return
	 * @throws ParseException
	 */

	public static Date getBeforeDate(String date) throws ParseException {
		return getBeforeDate(convertStringTODate(date,
				DateUtil.DATE_PATTERN_YYYY_MM_DD));
	}
	
	/**
	 * 获取指定时间date的前一天
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getBeforeDate(Date date) throws ParseException {
		return new Date(date.getTime() - 1000 * 3600 * 24);
	}

	/**
	 * 获取指定时间date的后一天
	 * @param date
	 * @return
	 */
	public static Date getAfterDate(Date date) {
		return new Date(date.getTime() + 1000 * 3600 * 24);
	}

//	/**
//	 * 获取指定时间+interval天之后的日期（注意：当interval超过25时，该计算方法会有问题）
//	 * @param date
//	 * @param interval
//	 * @return
//	 */
//	public static Date addDate(Date date, int interval) {
//		return new Date(date.getTime() + ((1000 * 3600 * 24) * interval));
//	}
	
	
	/**
	 * 获取指定时间+interval天之后的日期
	 * @param date
	 * @param interval
	 * @return
	 */
	public static Date addDate(Date date, int interval) {
		SimpleDateFormat formatDate = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);  
		Calendar c = Calendar.getInstance();  
		c.setTimeInMillis(date.getTime());  
		c.add(Calendar.DATE, interval);
		return new Date(c.getTimeInMillis()); 
	}
	

	/**
	 *获取指定时间+interval分钟后的时间
	 * @param startTime
	 * @param interval
	 * @return
	 */
	public static Date addMinutes(Date startTime, int interval) {
		if (startTime == null) {
			return null;
		}
		long ms = startTime.getTime() + interval * 60 * 1000;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(ms);
		return calendar.getTime();
	}
	
	
	/**
	 *获取指定时间+interval分钟前的时间
	 * @param startTime
	 * @param interval
	 * @return
	 */
	public static Date minusMinutes(Date startTime, int interval) {
		if (startTime == null) {
			return null;
		}
		long ms = startTime.getTime() - interval * 60 * 1000;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(ms);
		return calendar.getTime();
	}

	/**
	 * 获取指定时间的一个小时后的时间
	 * @param time
	 * @return
	 */
	public static Date getNextHour(Date time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		calendar.set(Calendar.HOUR_OF_DAY, hour + 1);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取指定时间的半个小时后的时间
	 * @param time
	 * @return
	 */
	public static Date getNextHalfHour(Date time) {
		Date result = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		int minute = calendar.get(Calendar.MINUTE);
		if (minute >= 30) {
			result = getNextHour(time);
		} else {
			calendar.set(Calendar.MINUTE, 30);
			calendar.set(Calendar.SECOND, 0);
			result = calendar.getTime();
		}
		return result;
	}
	
	/**
	 * 获取当前时间的中国时间格式
	 * @return
	 */
	public static String getCurrentTimeChineseStr(){
		
		return CHINESE_DATE_FORMAT.format(getCurrentDateTime());
	}
	

	/**
	 * 判断strDate是否日期格式
	 * @param strDate
	 * @return
	 */
	public static boolean isDateFormat(String strDate) {
		String eL = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(strDate);
		boolean b = m.matches();

		return b;
	}
	
	
	
	/**
     * 计算2个日期间相隔的月数
     * @param start
     * @param end
     * @return
     */
    public static int getMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR)
                - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH)
                - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) != 1)) {

        	return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }
	/**
     * 计算2个日期相隔的天数
     * @param start
     * @param end
     * @return
     * 日期计算不准确，请用DateUtils.caculate2Days(Date firstDate, Date secondDate)方法代替
     */ 
    @Deprecated()
    public static long differDay(Date fromDate, Date toDate) 
    { 
        //return date1.getTime() / (24*60*60*1000) - date2.getTime() / (24*60*60*1000); 
       if(fromDate != null && toDate != null){
    	return (toDate.getTime() - fromDate.getTime()) / 86400000;
       }else{
    	   return 0;
       }
    } 
    
    /**
     * 计算2个日期相隔的小时
     * @param fromDate
     * @param toDate
     * @return
     */
    public static long differHour(Date fromDate, Date toDate){
    	long time = toDate.getTime() - fromDate.getTime();
    	return  (time%86400000)/(60*60*1000);
    	 
    }
    
    /**
     * 计算2个时间相隔的分钟数
     * @param fromDate
     * @param toDate
     * @return
     */
    public static long differMin(Date fromDate, Date toDate) {
    	long time = toDate.getTime() - fromDate.getTime();
    	return  (time%86400000)/(60*1000);
    	
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
    


	public static void main(String args[]) {

		/*try {
			String pattern = "yyyy-MM-dd HH:mm:ss";
			Date d1 = new Date();
			Date d2 = DateUtil.addMinutes(d1, -60);
			String time =  DateUtil.convertDateToString(d2, pattern);
			System.out.println("time="+time);
			long time = 1288022400000L;
			long time2 = 1288022400L*1000;
			Date d = new Date(time);
			System.out.println(new Date().getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		Date d=DateUtils.formatStr2Date("2012-01-08 21:43:05");
		Date c=new Date();
		System.out.println(DateUtils.caculate2Days(d,c));
	}

}
