package com.lunchtasting.server.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.AdminMatchDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Match;

@Repository
public class AdminMatchDaoImpl extends GenericDAOSupport<Long,Match> implements AdminMatchDao{

	@Override
	public Long saveMatch(Match match) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create",match);
	}

	@Override
	public Long updateMatch(Match match) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".update",match);
	}

	@Override
	public List queryMatchList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryMatchList",map);
	}

	@Override
	public Match queryMatchById(String id) {
		HashMap map = new HashMap();
		map.put("id", id);
		return (Match) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryMatchById",map);
	}

	@Override
	public int queryMatchCount(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryMatchCount",map);
	}

	@Override
	public int deleteMatch(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update(getNamespace() + ".deleteMatch",map);
	}

	@Override
	public int topMatch(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update(getNamespace() + ".topMatch",map);
	}

	@Override
	public List queryMatchNotLimit() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryMatchNotLimit");
	}

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return Match.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "matchs";
	}

}
