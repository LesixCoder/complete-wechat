package com.lunchtasting.server.dao.match.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.match.MatchVoteDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.MatchVote;

@Repository
public class MatchVoteDAOImpl extends GenericDAOSupport<Long,MatchVote> implements MatchVoteDAO {

	@Override
	protected Class<?> getObjectClass() {
		return MatchVote.class;
	}

	@Override
	protected String getTableName() {
		return "match_vote";
	}

	@Override
	public Integer getIsVote(Long orderId, Long userId) {
		if(orderId == null || userId == null){
			return 0;
		}
		
		Map map = new HashMap();
		map.put("orderId", orderId);
		map.put("userId", userId);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getIsVote", map);
	}

	@Override
	public Map getMatchVoteDetail(Long orderId, Long userId) {
		if(orderId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("orderId", orderId);
		map.put("userId", userId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getMatchVoteDetail", map);	
	}

}
