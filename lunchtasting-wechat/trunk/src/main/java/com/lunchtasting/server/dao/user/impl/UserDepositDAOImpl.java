package com.lunchtasting.server.dao.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.user.UserDepositDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.UserDeposit;

@Repository
public class UserDepositDAOImpl extends GenericDAOSupport<Long, UserDeposit> 
	implements UserDepositDAO {

	@Override
	protected Class<?> getObjectClass() {
		return UserDeposit.class;
	}

	@Override
	protected String getTableName() {
		return "user_deposit";
	}

	@Override
	public Double getUserDepositTotal(Long userId) {
		if(userId == null){
			return 0d;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		return (Double) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserDepositTotal", map);		
	}

	@Override
	public List queryUserDepositList(Long userId, Integer page, Integer pageSize) {
		if(userId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return  getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserDepositList", map);
	}

	@Override
	public Integer getUserDepositCount(Long userId) {
		if(userId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserDepositCount", map);	
	}

}
