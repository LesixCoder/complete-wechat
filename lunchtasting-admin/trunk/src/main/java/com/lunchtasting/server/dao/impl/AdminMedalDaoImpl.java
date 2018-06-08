package com.lunchtasting.server.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.AdminMedalDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Medal;
@Repository
public class AdminMedalDaoImpl extends GenericDAOSupport<Long,Medal> implements AdminMedalDao{

	@Override
	public List queryMedalNotLimit() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryMedalNotLimit");
	}

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return Medal.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "medal";
	}

	@Override
	public Long saveMedal(Medal medal) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create",medal);
	}

	@Override
	public Long updateMedal(Medal medal) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".update",medal);
	}

	@Override
	public List queryMedalList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryMedalList",map);
	}

	@Override
	public Medal queryMedalById(String id) {
		HashMap map = new HashMap();
		map.put("id", id);
		return (Medal) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryMedalById",map);
	}

	@Override
	public int queryMedalCount(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryMedalCount",map);
	}

	@Override
	public int deleteMedal(String id) {
		HashMap map = new HashMap();
		map.put("id", id);
		return getSqlMapClientTemplate().delete(getNamespace() + ".deleteMedal",map);
	}

	@Override
	public int queryMedalByName(String name) {
		HashMap map = new HashMap();
		map.put("name", name);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryMedalByName",map);
	}

}
