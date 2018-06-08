package com.lunchtasting.server.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.AdminMatchCategoryDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.MatchCategory;
@Repository
public class AdminMatchCategoryDaoImpl extends GenericDAOSupport<Long,MatchCategory> implements AdminMatchCategoryDao{

	@Override
	public List queryMatchCategoryNotLimit() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryMatchCategoryNotLimit");
	}

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return MatchCategory.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "match_category";
	}

	@Override
	public Long saveMatchCategory(MatchCategory matchCategory) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create",matchCategory);
	}

	@Override
	public Long updateMatchCategory(MatchCategory matchCategory) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".update",matchCategory);
	}

	@Override
	public List queryMatchCategoryList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryMatchCategoryList",map);
	}

	@Override
	public MatchCategory queryMatchCategoryById(String id) {
		HashMap map = new HashMap();
		map.put("id", id);
		return (MatchCategory) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryMatchCategoryById",map);
	}

	@Override
	public int queryMatchCategoryCount(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryMatchCategoryCount",map);
	}

	@Override
	public int deleteMatchCategory(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update(getNamespace() + ".deleteMatchCategory",map);
	}

	@Override
	public int getMatchCategoryByName(String name) {
		HashMap map = new HashMap();
		map.put("name", name);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryMatchCategoryByName",map);
	}

}
