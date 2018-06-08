package com.lunchtasting.server.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.AdminWeekDayDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.WeekDay;
@Repository
public class AdminWeekDayDaoImpl extends GenericDAOSupport<Long,WeekDay> implements AdminWeekDayDao{

	@Override
	public Long addWeekDay(WeekDay weekDay) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create",weekDay);
	}

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return WeekDay.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "week_day";
	}

	@Override
	public List queryDays(HashMap map) {
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryDays", map);
	}

	@Override
	public List getWNextDay(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".getWNextDay",map);
	}

}
