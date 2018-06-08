package com.lunchtasting.server.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.AdminRoleDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.SysRole;

@Repository
public class AdminRoleDaoImpl extends GenericDAOSupport<Long,SysRole> implements AdminRoleDao{

	@Override
	public List<SysRole> queryRoleList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() +".queryRoleById",map);
	}

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return SysRole.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "sys_role";
	}

}
