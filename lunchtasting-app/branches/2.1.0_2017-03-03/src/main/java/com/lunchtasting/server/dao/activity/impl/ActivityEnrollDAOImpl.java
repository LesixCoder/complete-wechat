package com.lunchtasting.server.dao.activity.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.activity.ActivityEnrollDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.ActivityEnroll;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class ActivityEnrollDAOImpl extends GenericDAOSupport<Long, ActivityEnroll> implements ActivityEnrollDAO {

	@Override
	protected Class<?> getObjectClass() {
		return ActivityEnroll.class;
	}

	@Override
	protected String getTableName() {
		return "activity_enroll";
	}

	@Override
	public Integer getEnrollCount(Long activityId) {
		if(ValidatorHelper.isEmpty(activityId)){
			return 0;
		}
		Map map = new HashMap();
		map.put("activityId", activityId);
        return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getEnrollCount", map);
	}

	@Override
	public Long getIsEnroll(Long activityId, Long userId) {
		if(ValidatorHelper.isEmpty(activityId) || ValidatorHelper.isEmpty(userId)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("activityId", activityId);
		map.put("userId", userId);
        return (Long)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getIsEnroll", map);
	}

	@Override
	public Long getEnrollId(Long activityId, Long userId) {
		if(ValidatorHelper.isEmpty(activityId) || ValidatorHelper.isEmpty(userId)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("activityId", activityId);
		map.put("userId", userId);
        return (Long)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getEnrollId", map);
	}

	@Override
	public Integer getUserEnrollerCount(Long userId) {
		if(userId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("userId", userId);
        return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserEnrollerCount", map);
	}

	@Override
	public List queryUserEnrollerList(Long userId, Integer page,
			Integer pageSize) {
		if(userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("page", page);
		map.put("pageSize", pageSize);
        return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserEnrollerList", map);
	}

}
