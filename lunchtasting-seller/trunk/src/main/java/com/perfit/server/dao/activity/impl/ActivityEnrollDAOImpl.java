package com.perfit.server.dao.activity.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Activity;
import com.lunchtasting.server.po.lt.ActivityEnroll;
import com.perfit.server.dao.activity.ActivityEnrollDAO;
@Repository
public class ActivityEnrollDAOImpl extends GenericDAOSupport<Long,ActivityEnroll> implements ActivityEnrollDAO{

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
	public List queryEnrollerList(HashMap map) {
		return getSqlMapClientTemplate().queryForList(getNamespace()+".queryEnrollerList", map);
	}

	@Override
	public Integer queryEnrollerListCount(Long activityId) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("activityId",activityId);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace()+".queryEnrollerListCount", map);
	}
}
