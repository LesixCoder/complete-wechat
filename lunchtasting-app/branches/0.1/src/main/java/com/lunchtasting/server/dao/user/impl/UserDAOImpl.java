package com.lunchtasting.server.dao.user.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.user.UserDAO;
import com.lunchtasting.server.dao.user.UserDeviceDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.User;

@Repository
public class UserDAOImpl  extends GenericDAOSupport<Long, User> implements UserDAO {

	@Override
	protected Class<?> getObjectClass() {
		return User.class;
	}

	@Override
	protected String getTableName() {
		return "user";
	}

	@Override
	public String getByPhone(String phone) {
		if(StringUtils.isEmpty(phone)){
			return null;
		}
		Map map = new HashMap();
		map.put("phone", phone);
		return (String) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getByPhone", map);
	}

	@Override
	public User getByPhoneAndPwd(String phone, String pwd) {
		if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("phone", phone);
		map.put("pwd", pwd);
		return (User) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getByPhoneAndPwd", map);
	}

}
