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
	
	/**
	 * 获得用户的设备信息
	 * @param userId
	 * @return
	 */
	UserDevice getByUserId(Long userId);
	
	/**
	 * 判断是否有用户的设备
	 * @param userId
	 * @return
	 */
	Long getDeviceId(Long userId);
	
	/**
	 * 判断IOS设备是否下载
	 * @param appid
	 * @param idfa
	 * @return
	 */
	Integer getDeviceCount(String appid,String idfa);
	
}
