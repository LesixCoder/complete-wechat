package com.lunchtasting.server.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.AdminUserDao;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.UserAdmin;
import com.lunchtasting.server.util.ValidatorHelper;
@Repository
public class AdminUserDaoImpl extends GenericDAOSupport<Long,UserAdmin> implements AdminUserDao{

	@Override
	protected Class<?> getObjectClass() {
		return UserAdmin.class;
	}

	@Override
	protected String getTableName() {
		return "user_admin";
	}
	
	public UserAdmin systemUsersLogin(String account,String pwd){
		if(ValidatorHelper.isEmpty(account) || ValidatorHelper.isEmpty(pwd)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("account", account);
		map.put("pwd", pwd);
		return (UserAdmin) getSqlMapClientTemplate().queryForObject(getNamespace() + ".systemUsersLogin",map);
	}

}
