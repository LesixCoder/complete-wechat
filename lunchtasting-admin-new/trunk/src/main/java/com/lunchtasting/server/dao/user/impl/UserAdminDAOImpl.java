package com.lunchtasting.server.dao.user.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.user.UserAdminDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.UserAdmin;

@Repository
public class UserAdminDAOImpl extends GenericDAOSupport<Long,UserAdmin> 
	implements UserAdminDAO{

	@Override
	protected Class<?> getObjectClass() {
		return UserAdmin.class;
	}

	@Override
	protected String getTableName() {
		return "user_admin";
	}

	@Override
	public UserAdmin getByAccountAndPwd(String account, String pwd) {
		if(account == null || pwd == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("account", account);
		map.put("pwd", pwd);
		return (UserAdmin)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getByAccountAndPwd", map);		
	}

}
