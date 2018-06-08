package com.lunchtasting.server.dao.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.user.UserDepositDrawDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.UserDepositDraw;

@Repository
public class UserDepositDrawDAOImpl extends GenericDAOSupport<Long, UserDepositDraw> 
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
	public Integer getNowDrawCount(Long userId,Integer day) {
		if(userId == null || day == null){
			return 0;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("day", day);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getNowDrawCount", map);		
	}

	@Override
	public List queryDrawList(Long userId, Integer page,
			Integer pageSize) {
		if(userId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return  getSqlMapClientTemplate().queryForList(getNamespace() + ".queryDrawList", map);
	}

	@Override
	public Integer getDrawCount(Long userId) {
		if(userId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getDrawCount", map);		
	}

}
