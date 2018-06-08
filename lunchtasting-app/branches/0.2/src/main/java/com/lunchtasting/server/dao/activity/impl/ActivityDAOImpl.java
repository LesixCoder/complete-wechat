package com.lunchtasting.server.dao.activity.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


import com.lunchtasting.server.dao.activity.ActivityDAO;
import com.lunchtasting.server.dao.user.UserDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Activity;
import com.lunchtasting.server.util.ValidatorHelper;

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
	public Integer getIndexCount() {
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getIndexCount");	
	}

	@Override
	public List queryIndexList(Integer page, Integer pageSize) {
		Map map = new HashMap();
		map.put("page", page);
		map.put("pageSize", pageSize);
        return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryIndexList", map);
	}

	
	@Override
	public Integer getActivityCount() {
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getActivityCount");	
	}
	
	@Override
	public List queryActivityList(Integer page, Integer pageSize) {
		Map map = new HashMap();
		map.put("page", page);
		map.put("pageSize", pageSize);
        return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryActivityList", map);
	}
	
	@Override
	public Map getActivityDetail(Long activityId) {
		if(ValidatorHelper.isEmpty(activityId)){
			return null;
		}
		Map map = new HashMap();
		map.put("activityId", activityId);
		return (Map) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getActivityDetail",map);	
	}

}
