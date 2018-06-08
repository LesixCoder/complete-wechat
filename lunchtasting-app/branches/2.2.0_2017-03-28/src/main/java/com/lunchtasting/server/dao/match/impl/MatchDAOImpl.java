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
	public List queryMatchCategoryList() {
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryMatchCategoryList");
	}

	@Override
	public List queryMatchList(Long categoryId,Integer page, Integer pageSize) {
		if(categoryId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("categoryId", categoryId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryMatchList", map);
	}

	@Override
	public Integer getMatchCount(Long categoryId) {
		if(categoryId == null){
			
		}
		Map map = new HashMap();
		map.put("categoryId", categoryId);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getMatchCount", map);
	}

	@Override
	public Map getMatchDetail(Long matchId,Long userId) {
		if(matchId == null || userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("matchId",matchId);
		map.put("userId", userId);
		return (Map) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getMatchDetail", map);
	}

}
