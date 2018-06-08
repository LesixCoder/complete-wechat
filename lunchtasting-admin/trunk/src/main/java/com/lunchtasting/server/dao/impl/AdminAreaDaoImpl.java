package com.lunchtasting.server.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.AdminAreaDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Area;
@Repository
public class AdminAreaDaoImpl extends GenericDAOSupport<Long,Area> implements AdminAreaDao{

	@Override
	public List queryArea() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryArea");
	}

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return Area.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "area";
	}

	@Override
	public Area queryAreaByName(String name) {
		HashMap map = new HashMap();
		map.put("name", name);
		return (Area) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryAreaByName",map);
	}

	@Override
	public List queryAreaList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryAreaList",map);
	}

	@Override
	public Long addArea(Area area) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create",area);
	}

	@Override
	public Long updateArea(Area area) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".update",area);
	}

	@Override
	public int queryNameCount(String name) {
		HashMap map = new HashMap();
		map.put("name", name);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryNameCount",map);
	}

	@Override
	public int queryAreaCount(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryAreaCount",map);
	}

	@Override
	public Area queryAreaById(String id) {
		HashMap map = new HashMap();
		map.put("id", id);
		return (Area) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryAreaById",map);
	}

	@Override
	public Long updateArea(HashMap map) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".deleteArea",map);
	}

}
