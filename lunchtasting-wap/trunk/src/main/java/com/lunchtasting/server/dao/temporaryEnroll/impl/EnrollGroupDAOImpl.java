package com.lunchtasting.server.dao.temporaryEnroll.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.temporaryEnroll.EnrollGroupDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.temporaryEnroll.EnrollGroup;
@Repository
public class EnrollGroupDAOImpl extends GenericDAOSupport<Long,EnrollGroup> implements EnrollGroupDAO{

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return EnrollGroup.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "enroll_group";
	}
	@Override
	public EnrollGroup getEnrollGroupOne(HashMap map) {
		// TODO Auto-generated method stub
		return (EnrollGroup) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getEnrollGroupOne", map);
	}

	@Override
	public Boolean updateEnrollGroup(EnrollGroup enrollGroup) {
		// TODO Auto-generated method stub
		Integer pf = getSqlMapClientTemplate().update(getNamespace() + ".updateEnrollGroup", enrollGroup);
		if(pf==1){
			return true;
		}
		return false;
	}

	@Override
	public int getAllCounut() {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace()+".getAllCounut",map);
	}
}
