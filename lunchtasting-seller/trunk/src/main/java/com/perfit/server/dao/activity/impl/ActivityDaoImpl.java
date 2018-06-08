package com.perfit.server.dao.activity.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Activity;
import com.perfit.server.dao.activity.ActivityDAO;
@Repository
public class ActivityDaoImpl extends GenericDAOSupport<Long,Activity> implements ActivityDAO{

	public Long addActivity(Activity activity) {
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create", activity);
	}

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return Activity.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "activity";
	}

	public Activity queryActivityById(String id) {  	
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("id", id);
		return (Activity) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryActivityById", map);
	}

	public Long updateActivity(Activity activity) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".update", activity);
	}

	public int queryActivityListCount(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryActivityListCount", map);
	}

	public List queryActivityList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryActivityList", map);
	}

	public int deleteActivity(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update(getNamespace() + ".deleteActivity", map);
	}

	@Override
	public Integer authenticationUser(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".authenticationUser", map);
	}

}
