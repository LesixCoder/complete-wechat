package com.lunchtasting.server.biz;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.WeekDay;

public interface AdminWeekDayBIZ {
	Long addWeekDay(WeekDay weekDay);
	List queryDays(HashMap map);
	List getWNextDay(String date);
}
