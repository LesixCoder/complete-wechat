package com.lunchtasting.server.dao.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.user.UserDepositDrawDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.UserAdmin;
import com.lunchtasting.server.po.lt.UserDepositDraw;

@Repository
public class UserDepositDrawDAOImpl extends GenericDAOSupport<Long,UserDepositDraw> 
	implements UserDepositDrawDAO {

	@Override
	protected Class<?> getObjectClass() {
		return UserDepositDraw.class;
	}

	@Override
	protected String getTableName() {
		return "user_deposit_draw";
	}

	@Override
	public List queryDrawList(Long userId, Integer status, String beginTime,
			String endTime, Integer page, Integer pageSize) {
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("status", status);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryDrawList", map);		
	}

	@Override
	public Integer getDrawCount(Long userId, Integer status, String beginTime,
			String endTime) {
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("status", status);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getDrawCount", map);		
	}

	@Override
	public Integer updateStatusError(Long drawId) {
		if(drawId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("drawId", drawId);
		return (Integer)getSqlMapClientTemplate().update(getNamespace() + ".updateStatusError", map);		
	}

	@Override
	public Integer updateStatusSuccess(Long drawId) {
		if(drawId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("drawId", drawId);
		return (Integer)getSqlMapClientTemplate().update(getNamespace() + ".updateStatusSuccess", map);		
	}

}
