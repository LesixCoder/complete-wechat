package com.lunchtasting.server.dao.temporaryEnroll.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.temporaryEnroll.TemporaryEnrollDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.temporaryEnroll.TemporaryEnroll;
@Repository
public class TemporaryEnrollDAOImpl extends GenericDAOSupport<Long,TemporaryEnroll> implements TemporaryEnrollDAO{

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return TemporaryEnroll.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "temporary_enroll";
	}

	@Override
	public TemporaryEnroll getRandomOneRandom(HashMap map) {
		// TODO Auto-generated method stub
		return (TemporaryEnroll) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getRandomOneRandom", map);
	}

	@Override
	public int updateTemporaryEnroll(TemporaryEnroll temporaryEnrol) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update(getNamespace() + ".updateTemporaryEnroll", temporaryEnrol);
	}

	@Override
	public int checkUser(String phone) {
		HashMap map = new HashMap();
		map.put("phone", phone);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".checkUser", map);
	}

	@Override
	public TemporaryEnroll getTemporaryEnrollByMap(HashMap map) {
		// TODO Auto-generated method stub
		return (TemporaryEnroll) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getTemporaryEnrollByMap", map);
	}

	@Override
	public List show(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".show", map);
	}
}
