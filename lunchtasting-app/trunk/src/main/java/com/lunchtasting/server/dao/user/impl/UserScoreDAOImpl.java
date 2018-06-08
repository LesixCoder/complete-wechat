package com.lunchtasting.server.dao.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.user.UserScoreDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.UserScore;

@Repository
public class UserScoreDAOImpl extends GenericDAOSupport<Long, UserScore> implements UserScoreDAO {

	@Override
	protected Class<?> getObjectClass() {
		return UserScore.class;
	}

	@Override
	protected String getTableName() {
		return "user_score";
	}

	@Override
	public Integer getUserScoreCount(Long userId) {
		if(userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserScoreCount", map);
	}

	@Override
	public List queryUserScoreList(Long userId, Integer page, Integer pageSize) {
		if(userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserScoreList", map);
	}

	@Override
	public Integer getUserScoreTotal(Long userId) {
		if(userId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserScoreTotal", map);
	}

	@Override
	public List queryUserScoreRank(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserScoreRank", map);
	}

	@Override
	public Integer getUserScoreRank(Long userId) {
		// TODO Auto-generated method stub
		if(userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserScoreRank", map);
	}

	@Override
	public Integer getScoreRankCount() {
		// TODO Auto-generated method stub
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getScoreRankCount");
	}

	@Override
	public Integer getUserScoreByPhone(String phone) {
		// TODO Auto-generated method stub
		if(phone == null){
			return null;
		}
		Map map = new HashMap();
		map.put("phone", phone);
		return  (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserScoreByPhone",map);
	}

	@Override
	public Integer completion(String phone, Long userId) {
		// TODO Auto-generated method stub
		if(phone == null || userId==null){
			return null;
		}
		Map map = new HashMap();
		map.put("phone", phone);
		map.put("userId", userId);
		return getSqlMapClientTemplate().update(getNamespace() + ".completion",map);
	}
	
}
