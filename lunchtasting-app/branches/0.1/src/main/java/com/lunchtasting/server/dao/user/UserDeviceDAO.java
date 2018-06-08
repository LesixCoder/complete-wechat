package com.lunchtasting.server.dao.user;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.UserDevice;

public interface UserDeviceDAO extends GenericDAO<UserDevice> {
	
	/**
	 * 获得accessToken
	 * @param authId
	 * @return
	 */
	String getAccessTokenByAuthId(String authId);
	
	/**
	 * 修改用户设备信息
	 * @param userDevice
	 * @return
	 */
	Integer updateDevice(UserDevice userDevice);
}
