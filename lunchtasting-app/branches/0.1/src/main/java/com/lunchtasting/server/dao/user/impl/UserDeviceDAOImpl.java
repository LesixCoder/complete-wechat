package com.lunchtasting.server.dao.user.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.user.UserDeviceDAO;
import com.lunchtasting.server.dao.user.UsersInfoDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.UserDevice;
import com.lunchtasting.server.po.lt.UsersInfo;

@Repository
public class UserDeviceDAOImpl extends GenericDAOSupport<Long, UserDevice> implements UserDeviceDAO {

	@Override
	protected Class<?> getObjectClass() {
		return UserDevice.class;
	}

	@Override
	protected String getTableName() {
		return "user_dervice";
	}

	@Override
	public String getAccessTokenByAuthId(String authId) {
		if(StringUtils.isEmpty(authId)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("authId", authId);
		return (String) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getAccessTokenByAuthId", map);
	}

	@Override
	public Integer updateDevice(UserDevice userDevice) {
		if(userDevice == null){
			return 0;
		}
		return getSqlMapClientTemplate().update(getNamespace() + ".updateDevice", userDevice);
	}

}
