package com.lunchtasting.server.dao.user.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.user.UserDeviceDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.UserDevice;
import com.lunchtasting.server.util.ValidatorHelper;

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

	@Override
	public UserDevice getByUserId(Long userId) {
		if(ValidatorHelper.isEmpty(userId)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		return (UserDevice) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getByUserId", map);
	}

	@Override
	public Long getDeviceId(Long userId) {
		if(ValidatorHelper.isEmpty(userId)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		return (Long) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getDeviceId", map);
	}

	@Override
	public Integer getDeviceCount(String appid, String idfa) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("appid", appid);
		map.put("idfa", idfa);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getDeviceCount", map);
	}

}
