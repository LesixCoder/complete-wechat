package com.lunchtasting.server.dao.activity.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.activity.ActivityEnrollDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.ActivityEnroll;

@Repository
public class ActivityEnrollDAOImpl extends GenericDAOSupport<Long, ActivityEnroll> implements ActivityEnrollDAO{

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return ActivityEnroll.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "activity_enroll";
	}

	@Override
	public Integer queryActivityByMapCount(HashMap map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryActivityByMapCount", map);
	}
		
}
