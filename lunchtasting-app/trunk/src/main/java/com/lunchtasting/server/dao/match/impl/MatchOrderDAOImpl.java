package com.lunchtasting.server.dao.match.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.match.MatchOrderDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.MatchOrder;

@Repository
public class MatchOrderDAOImpl extends GenericDAOSupport<Long,MatchOrder> implements MatchOrderDAO {

	@Override
	protected Class<?> getObjectClass() {
		return MatchOrder.class;
	}

	@Override
	protected String getTableName() {
		return "match_order";
	}

	@Override
	public Integer getMatchOrderCount(Long matchId) {
		if(matchId == null){
			return 0;
		}
		
		Map map = new HashMap();
		map.put("matchId", matchId);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getMatchOrderCount",map);	
	}

	@Override
	public Integer getByMatchIdAndUserId(Long matchId, Long userId) {
		if(matchId == null || userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("matchId", matchId);
		map.put("userId", userId);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getByMatchIdAndUserId",map);	
	}

	@Override
	public Integer getUserMatchOrderCount(Long userId) {
		if(userId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserMatchOrderCount",map);	
	}

	@Override
	public List queryUserMatchOrderList(Long userId, Integer page,
			Integer pageSize) {
		if(userId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("page", page);
		map.put("pageSize", pageSize);
        return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserMatchOrderList", map);
	}

}
