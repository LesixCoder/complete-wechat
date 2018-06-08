package com.lunchtasting.server.dao.match.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.match.MatchGroupDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Match;
import com.lunchtasting.server.po.lt.MatchGroup;
@Repository
public class MatchGroupDAOImpl extends GenericDAOSupport<Long,MatchGroup> implements MatchGroupDAO{

	@Override
	protected Class<?> getObjectClass() {
		return MatchGroup.class;
	}

	@Override
	protected String getTableName() {
		return "match_group";
	}

	@Override
	public void createMatchGroupUser(Long id,Long groupId,Long userId,Long matchId) {
		if(id == null || groupId == null || userId == null || matchId == null){
			return;
		}
		
		Map map = new HashMap();
		map.put("id", id);
		map.put("userId",userId);
		map.put("matchId",matchId);
		map.put("matchGroupId",groupId);
		getSqlMapClientTemplate().insert(getNamespace() + ".createMatchGroupUser", map);
	}

	@Override
	public Map getMatchGroupUser(Long matchId, Long userId) {
		if(matchId == null || userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId",userId);
		map.put("matchId",matchId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getMatchGroupUser", map);
	}

	@Override
	public Map getGroupUserOther(Long groupId, Long userId) {
		if(groupId == null || userId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("groupId",groupId);
		map.put("userId",userId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getGroupUserOther", map);	}
	
}
