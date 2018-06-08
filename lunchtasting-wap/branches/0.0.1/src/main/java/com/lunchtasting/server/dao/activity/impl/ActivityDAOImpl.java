package com.lunchtasting.server.dao.activity.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.activity.ActivityDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Activity;

@Repository
public class ActivityDAOImpl extends GenericDAOSupport<Long, Activity> implements ActivityDAO {

	@Override
	protected Class<?> getObjectClass() {
		return Activity.class;
	}

	@Override
	protected String getTableName() {
		return "activity";
	}
	
	@Override
	public List queryActivityList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryActivityList", map);
	}

	@Override
	public List queryAllList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryAllList", map);
	}

	@Override
	public Activity queryActivityById(HashMap map) {
		// TODO Auto-generated method stub
		return (Activity)getSqlMapClientTemplate().queryForObject(getNamespace()+".queryActivityById",map);
	}
	
}
