package com.lunchtasting.server.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.AdminWeekDayBIZ;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.WeekDay;
import com.lunchtasting.server.util.IdWorker;
@Controller
public class InsertHolidayController extends BaseController{
	
	@Autowired
	private AdminWeekDayBIZ adminWeekDayBIZ;
	
	@RequestMapping(value = "/insertHoliday")
	@ResponseBody
	public Model insert(Model model) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			java.util.Date start = sdf.parse("20170101");// 开始时间
			java.util.Date end = sdf.parse("20171231");// 结束时间
			List<Date> lists = dateSplit(start, end);

			Date nextWorkDate = sdf.parse("20180102");// 下一个工作日，默认1月2日，1日为元旦
			Date lastWorkDate = sdf.parse("20171229");// 上一个工作日

			if (!lists.isEmpty()) {
				for (Date date : lists) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					System.out.println("********插入日期:" + sdf.format(date)
							+ "***********");

					int year = cal.get(Calendar.YEAR);
					int month = cal.get(Calendar.MONTH) + 1;
					int day = cal.get(Calendar.DATE);
					int week = cal.get(Calendar.DAY_OF_WEEK) - 1;

					String monthStr = "", dayStr = "";
					if (month / 10 == 0) {
						monthStr = "0" + String.valueOf(month);
					} else {
						monthStr = String.valueOf(month);
					}
					if (day / 10 == 0) {
						dayStr = "0" + String.valueOf(day);
					} else {
						dayStr = String.valueOf(day);
					}

					lastWorkDate = getLastWorkDay(date);
					//年，月，日，日期，标识（1为假日，0为工作日），周几，上一个工作日，下一个工作日
					WeekDay weekDay = new WeekDay();
					weekDay.setId(IdWorker.getId());
					weekDay.setwYear(year+"");
					weekDay.setwMonth(monthStr);
					weekDay.setwDay(dayStr);
					weekDay.setwDate(date);
					weekDay.setwFlag(isHoliday(date));
					weekDay.setwWeek(week);
					weekDay.setwLastDay(lastWorkDate);
					weekDay.setwNextDay(nextWorkDate);
					adminWeekDayBIZ.addWeekDay(weekDay);
					
					if (isHoliday(date) == 0) {
						nextWorkDate = date;
					}
				}
				model.addAttribute("flag", "success");
			}
		} catch (Exception e) {
			model.addAttribute("flag", "fail");
			e.printStackTrace();
		}
		return model;
	}

	private static List<Date> dateSplit(Date start, Date end) throws Exception {
		if (!start.before(end))
			throw new Exception("开始时间应该在结束时间之后");
		Long spi = end.getTime() - start.getTime();
		Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数

		List<Date> dateList = new ArrayList<Date>();
		dateList.add(end);
		for (int i = 1; i <= step; i++) {
			dateList.add(new Date(dateList.get(i - 1).getTime()
					- (24 * 60 * 60 * 1000)));// 比上一天减一
		}
		return dateList;
	}

	/**
	 * 判断是否为节假日，若是返回1，否则返回0
	 * 
	 * @param date
	 * @return
	 */
	private static int isHoliday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		List<String> holidays = getHolidays();
		List<String> workdays = getWorkDays();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// System.out.println(sdf.format(date));
		if (((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal
				.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) && !workdays
				.contains(sdf.format(date)))
				|| holidays.contains(sdf.format(date))) {
			return 1;
		}
		return 0;
	}

	private static Date getLastWorkDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date dateTemp = cal.getTime();

		while (isHoliday(dateTemp) != 0) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
			dateTemp = cal.getTime();
		}
		return dateTemp;
	}

	private static List<String> getHolidays() {
		List<String> holidays = new ArrayList<String>();
		
		 //元旦
		 holidays.add("20161231");
		 holidays.add("20170101");
		 holidays.add("20170102");
		 //春节
		 holidays.add("20170127");
		 holidays.add("20170128");
		 holidays.add("20170129");
		 holidays.add("20170130");
		 holidays.add("20170131");
		 holidays.add("20170201");
		 holidays.add("20170202");
		 //清明节 
		 holidays.add("20170402");
		 holidays.add("20170403");
		 holidays.add("20170404");
		 //劳动节
		 holidays.add("20170429");
		 holidays.add("20170430");
		 holidays.add("20170501");
		 //端午节
		 holidays.add("20170528");
		 holidays.add("20170529");
		 holidays.add("20170530");
		 //中秋节
		 /*holidays.add("20160915");
		 holidays.add("20160916");
		 holidays.add("20160917");*/
		 //国庆节
		 holidays.add("20171001");
		 holidays.add("20171002");
		 holidays.add("20171003");
		 holidays.add("20171004");
		 holidays.add("20171005");
		 holidays.add("20171006");
		 holidays.add("20171007");
		 holidays.add("20171008");
		 
		return holidays;
	}

	private static List<String> getWorkDays() {
		List<String> workDays = new ArrayList<String>();
		 //补班 
		 /*workDays.add("20160206");
		 workDays.add("20160214");
		 workDays.add("20160502");
		 workDays.add("20160612");
		 workDays.add("20160918");
		 workDays.add("20161008");
		 workDays.add("20161009");*/
		 
		 workDays.add("20170122");
		 workDays.add("20170204");
		 workDays.add("20170401");
		 workDays.add("20170527");
		 workDays.add("20170930");
		 
		return workDays;
	}
}
