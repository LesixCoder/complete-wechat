package com.lunchtasting.server.dao;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.WeekDay;

public interface AdminWeekDayDao extends GenericDAO<WeekDay>{
	Long addWeekDay(WeekDay weekDay);
	List queryDays(HashMap map);
	List getWNextDay(HashMap map);
}
