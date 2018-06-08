package com.lunchtasting.server.util;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author xuqian
 *
 */
public class DateTimeUtils {
	
	public static final String PATTERN = null;
	
	public static void main(String[] args) {
		/*Date stime = new Date(DateTimeUtils.nDaysAfterDate(-1));
		Date etime = new Date(DateTimeUtils.nDaysAfterDate(0));
		System.out.println(stime);
		System.out.println(etime);*/
		
		long time = 1291824000*1000;
		Date d = new Date(time);
		System.out.println(DateTimeUtils.format(d,"yyyy-MM-dd HH:mm:ss"));
	}
	
	public static final long getTimeInMillis() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTimeInMillis();
	}
	
	public static final String format(Date date) {
		if(date == null) {
			return null;
		} else {
			return format(date.getTime(), null);
		}
	}
	
	public static final String format(Date date, String pattern) {
		if(date == null) {
			return null;
		} else {
			return format(date.getTime(), pattern);
		}
	}
	
	public static final String format(Long millisecond) {
		return format(millisecond, null);
	}
	
	public static final String format(Long millisecond, String pattern) {
		if(millisecond == null) {
			return null;
		}
		if(null == pattern) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.format(new Date(millisecond));
		return sdf.format(new Date(millisecond));
	}
	
	public static long nDaysAfterDate(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTimeInMillis();
	}
	
	public static long nHoursAfterNow(int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		return calendar.getTimeInMillis();
	}
	
	public static long nHoursAfterTime(long timeInMillis, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timeInMillis);
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		return calendar.getTimeInMillis();
	}

	public static long nSecondAfterNow(int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, second);
		return calendar.getTimeInMillis();
	}
}
