package com.lunchtasting.server.dao.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.user.UserHotDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.UserHot;
import com.lunchtasting.server.util.IdWorker;

@Repository
public class UserHotDAOImpl extends GenericDAOSupport<Long,UserHot> implements UserHotDAO{

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return UserHot.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "user_hot";
	}

	@Override
	public List queryUserHotList(Integer page,Integer pageSize,Integer time) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("page", page);
		map.put("pageSize", pageSize);
		map.put("time", time);
		return (List) getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserHotList", map);
	}

	@Override
	public Integer updateUserHot(UserHot userHot) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update("updateUserHot");
	}

	@Override
	public Integer getUserHotCount(Integer time) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("time", time);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserHotCount", map);
	}

	@Override
	public void createUserHot(Long userId, Integer number, Integer type) {
		Map map = new HashMap();
		map.put("id", IdWorker.getId());
		map.put("userId", userId);
		map.put("type", type);
		map.put("number", number);
		getSqlMapClientTemplate().insert(getNamespace() + ".createUserHot", map);
	}

	@Override
	public Integer getTypeCount(Long userId, Integer type) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("type", type);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getTypeCount", map);
	}

	@Override
	public Integer getUserHotByUser(Long userId, Integer time) {
		// TODO Auto-generated method stub
		if(userId==null){
			return 0;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("time", time);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserHotByUser", map);
	}

	@Override
	public Integer getUserHotRank(Long userId, Integer time) {
		// TODO Auto-generated method stub
		if(userId==null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("time", time);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserHotRank", map);
	}

}
