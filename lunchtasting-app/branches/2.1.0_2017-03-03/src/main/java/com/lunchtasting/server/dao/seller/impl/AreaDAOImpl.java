package com.lunchtasting.server.dao.seller.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.seller.AreaDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Area;

@Repository
public class AreaDAOImpl extends GenericDAOSupport<Long, Area> implements AreaDAO {

	@Override
	protected Class<?> getObjectClass() {
		return Area.class;
	}

	@Override
	protected String getTableName() {
		return "area";
	}

	@Override
	public List queryAreaList() {
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryAreaList");
	}

}
