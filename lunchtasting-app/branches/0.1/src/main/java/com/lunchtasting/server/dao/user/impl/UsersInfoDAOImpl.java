package com.lunchtasting.server.dao.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.user.UsersInfoDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.UsersInfo;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class UsersInfoDAOImpl extends GenericDAOSupport<Long, UsersInfo> implements UsersInfoDAO {

	@Override
	protected Class<?> getObjectClass() {
		return UsersInfo.class;
	}

	@Override
	protected String getTableName() {
		return "users_info";
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
	public UsersInfo getByPhoneAndPwd(String phone, String pwd) {
		if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("phone", phone);
		map.put("pwd", pwd);
		return (UsersInfo) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getByPhoneAndPwd", map);
	}

	

}
