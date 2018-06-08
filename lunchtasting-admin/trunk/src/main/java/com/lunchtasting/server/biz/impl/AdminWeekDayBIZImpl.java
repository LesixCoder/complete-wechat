package com.lunchtasting.server.biz.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.AdminWeekDayBIZ;
import com.lunchtasting.server.dao.AdminWeekDayDao;
import com.lunchtasting.server.po.lt.WeekDay;
@Service
public class AdminWeekDayBIZImpl implements AdminWeekDayBIZ{
	
    @Autowired
	private AdminWeekDayDao dminWeekDayDao;
	
	@Override
	public Long addWeekDay(WeekDay weekDay) {
		// TODO Auto-generated method stub
		return dminWeekDayDao.addWeekDay(weekDay);
	}

	@Override
	public List queryDays(HashMap map) {
		// TODO Auto-generated method stub
		return dminWeekDayDao.queryDays(map);
	}

	@Override
	public List getWNextDay(String date) {
		HashMap map = new HashMap();
		map.put("wDate", date);
		return dminWeekDayDao.getWNextDay(map);
	}

}
