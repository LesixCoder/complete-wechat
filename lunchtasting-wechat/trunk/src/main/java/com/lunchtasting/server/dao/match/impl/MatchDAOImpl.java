package com.lunchtasting.server.dao.match.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.match.MatchDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Match;
import com.lunchtasting.server.util.ValidatorHelper;
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

	@Override
	public List queryMatchRecommendUserList(Long userId, Long matchId) {
		if(userId == null || matchId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("matchId",matchId);
		map.put("userId", userId);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryMatchRecommendUserList", map);
		
	}

	@Override
	public void createMatchRecommendUser(Long id,Long userId, Long recommendUserId,
			Long matchId) {
		if(id == null || userId == null || recommendUserId == null 
				|| matchId == null){
			return;
		}
		
		Map map = new HashMap();
		map.put("id", id);
		map.put("matchId",matchId);
		map.put("userId", userId);
		map.put("recommendUserId", recommendUserId);
		getSqlMapClientTemplate().insert(getNamespace() + ".createMatchRecommendUser", map);
		
	}

	@Override
	public void createMatchInvite(Long id, Long srcUserId, Long desUserId,
			Long matchId, String content) {
		if(id == null || srcUserId == null || desUserId == null || matchId == null){
			return;
		}
		Map map = new HashMap();
		map.put("id", id);
		map.put("srcUserId", srcUserId);
		map.put("desUserId", desUserId);
		map.put("matchId",matchId);
		map.put("content", content);
		getSqlMapClientTemplate().insert(getNamespace() + ".createMatchInvite", map);
		
	}

	@Override
	public List queryMatchInviteList(Long userId, Integer page, Integer pageSize) {
		if(userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId",userId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryMatchInviteList", map);
	}

	@Override
	public Integer getIsInvite(Long srcUserId, Long desUserId, Long matchId) {
		if(srcUserId == null || desUserId == null || matchId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("srcUserId", srcUserId);
		map.put("desUserId", desUserId);
		map.put("matchId",matchId);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getIsInvite", map);
	}

	@Override
	public Map getMatchCodeByCode(String code) {
		if(ValidatorHelper.isEmpty(code)){
			return null;
		}
		Map map = new HashMap();
		map.put("code", code);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getMatchCodeByCode", map);
	}

	@Override
	public List queryPxgCjList(String sex) {
		Map map = new HashMap();
		map.put("sex", sex);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryPxgCjList", map);
	}

}
