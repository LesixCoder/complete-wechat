package com.lunchtasting.server.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 计算工作日类
 * @author Administrator
 *
 */
public class ComputeWorkDay {
	public static void main(String[] args) {
		ComputeWorkDay t = new ComputeWorkDay();
		System.out.println(t.getWorkDay(new Date(), new Date()));
	}

	/**
	 * 显示工作日
	 * 
	 * @param date1
	 * @param date2
	 */
	public static int getWorkDay(Date d1, Date d2) {
		// 这里要判断第二个参数日期要比第一个参数日期大先继续运行
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat holidaysdf = new SimpleDateFormat("MM-dd");
		// 工作日
		int workDay = 0;
		try {
//			Date d1 = sdf.parse(date1);
//			Date d2 = sdf.parse(date2);
			gc.setTime(d1);
			// System.out.println(sdf2.format(d1));
			long time = d2.getTime() - d1.getTime();
			long day = time / 3600000 / 24 + 1;
			// System.out.println(day);
			for (int i = 0; i < day; i++) {
				if (gc.get(GregorianCalendar.DAY_OF_WEEK) != GregorianCalendar.SATURDAY
						&& gc.get(GregorianCalendar.DAY_OF_WEEK) != GregorianCalendar.SUNDAY) {
					// System.out.println(holidaysdf.format(gc.getTime()));
					//这里处理双休以为的假日
//					if (!holidayList(holidaysdf.format(gc.getTime()))
//							&& !holidayOfCN(sdf.format(gc.getTime())))
						workDay++;
				}
				// 天数加1
				gc.add(gc.DATE, 1);
			}
			// gc.add(gc.DATE,1);
			// System.out.println(sdf.format(gc.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workDay;
	}

	// 春节放假三天，定义到2020年
	private boolean holidayOfCN(String year) {
		List ls = new ArrayList();
		ls.add("2005-02-09");
		ls.add("2005-02-10");
		ls.add("2005-02-11");
		ls.add("2006-01-29");
		ls.add("2006-01-30");
		ls.add("2006-01-31");
		ls.add("2007-02-18");
		ls.add("2007-02-19");
		ls.add("2007-02-21");
		ls.add("2008-02-07");
		ls.add("2008-02-08");
		ls.add("2008-02-09");
		ls.add("2009-01-26");
		ls.add("2009-01-27");
		ls.add("2009-01-28");
		ls.add("2010-02-14");
		ls.add("2010-02-15");
		ls.add("2010-02-16");
		ls.add("2011-02-03");
		ls.add("2011-02-04");
		ls.add("2011-02-05");
		ls.add("2012-01-23");
		ls.add("2012-01-24");
		ls.add("2012-01-25");
		ls.add("2013-02-10");
		ls.add("2013-02-11");
		ls.add("2013-02-12");
		ls.add("2014-01-31");
		ls.add("2014-02-01");
		ls.add("2014-02-02");
		ls.add("2015-02-19");
		ls.add("2015-02-20");
		ls.add("2015-02-21");
		ls.add("2006-02-08");
		ls.add("2006-02-09");
		ls.add("2006-02-10");
		ls.add("2017-01-28");
		ls.add("2017-01-29");
		ls.add("2017-01-30");
		ls.add("2018-02-16");
		ls.add("2018-02-17");
		ls.add("2018-02-18");
		ls.add("2019-02-05");
		ls.add("2019-02-06");
		ls.add("2019-02-07");
		ls.add("2020-01-25");
		ls.add("2020-01-26");
		ls.add("2020-01-27");
		if (ls.contains(year))
			return true;
		return false;
	}

	// 法定假日，五一和国庆
	private boolean holidayList(String findDate) {
		List ls = new ArrayList();
		ls.add("05-01");
		ls.add("05-02");
		ls.add("05-03");
		ls.add("10-01");
		ls.add("10-02");
		ls.add("10-03");
		if (ls.contains(findDate))
			return true;
		return false;
	}
}
