package com.lunchtasting.server.dao.match.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.match.MatchDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Match;
@Repository
public class MatchDAOImpl extends GenericDAOSupport<Long,Match> implements MatchDAO{

	@Override
	protected Class<?> getObjectClass() {
		return Match.class;
	}

	@Override
	protected String getTableName() {
		return "match";
	}

	@Override
	public Map getMatchDetail(Long matchId) {
		if(matchId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("matchId",matchId);
		return (Map) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getMatchDetail", map);
	}

}
