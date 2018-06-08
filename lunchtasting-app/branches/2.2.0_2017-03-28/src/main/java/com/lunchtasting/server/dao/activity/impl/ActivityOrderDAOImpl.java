package com.lunchtasting.server.dao.activity.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.activity.ActivityOrderDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.ActivityOrder;

@Repository
public class ActivityOrderDAOImpl extends GenericDAOSupport<Long, ActivityOrder> implements ActivityOrderDAO {

	@Override
	protected Class<?> getObjectClass() {
		return ActivityOrder.class;
	}

	@Override
	protected String getTableName() {
		return "activity_order";
	}

	@Override
	public Integer getActivityOrderCount(Long activityId) {
		if(activityId == null){
			return 0;
		}
		
		Map map = new HashMap();
		map.put("activityId", activityId);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getActivityOrderCount",map);	
	}

	@Override
	public Integer getByActivityIdAndUserId(Long activityId, Long userId) {
		if(activityId == null || userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("activityId", activityId);
		map.put("userId", userId);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getByActivityIdAndUserId",map);	
	}

	@Override
	public Integer getUserActivityOrderCount(Long userId) {
		if(userId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserActivityOrderCount",map);	
	}

	@Override
	public List queryUserActivityOrderList(Long userId, Integer page,
			Integer pageSize) {
		if(userId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("page", page);
		map.put("pageSize", pageSize);
        return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserActivityOrderList", map);
	}

}
