package com.lunchtasting.server.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.AdminActivityEnrollDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.ActivityEnroll;
@Repository
public class AdminActivityEnrollDaoImpl extends GenericDAOSupport<Long,ActivityEnroll> implements AdminActivityEnrollDao{

	@Override
	public List queryEnrollList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryEnrollerList",map);
	}

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return ActivityEnroll.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "activity_enroll";
	}

	@Override
	public int queryEnrollListCount(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryEnrollerListCount",map);
	}

}
