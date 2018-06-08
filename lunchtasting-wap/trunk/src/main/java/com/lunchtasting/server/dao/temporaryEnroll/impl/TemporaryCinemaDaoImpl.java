package com.lunchtasting.server.dao.temporaryEnroll.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.temporaryEnroll.TemporaryCinemaDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.temporaryEnroll.TemporaryCinema;
@Repository
public class TemporaryCinemaDaoImpl extends GenericDAOSupport<Long,TemporaryCinema> implements TemporaryCinemaDao{

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return TemporaryCinema.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "temporary_cinema";
	}

	@Override
	public Long addCinema(TemporaryCinema temporaryCinema) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create", temporaryCinema);
	}

	@Override
	public int checkRandom(String random) {
		HashMap map = new HashMap();
		map.put("random", random);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".checkRandom",map);
	}

	@Override
	public TemporaryCinema getCinema(String id) {
		HashMap map = new HashMap();
		map.put("userId", id);
		return (TemporaryCinema) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getCinema",map);
	}

}
