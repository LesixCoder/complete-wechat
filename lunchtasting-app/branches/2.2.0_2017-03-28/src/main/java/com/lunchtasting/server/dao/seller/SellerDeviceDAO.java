package com.lunchtasting.server.dao.seller;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.SellerDevice;

public interface SellerDeviceDAO extends GenericDAO<SellerDevice> {

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
	Integer updateDevice(SellerDevice device);
	
	/**
	 * 获得商户用户的设备信息
	 * @param userId
	 * @return
	 */
	SellerDevice getBySellerId(Long sellerId);
	
	/**
	 * 判断是否有商户用户的设备
	 * @param userId
	 * @return
	 */
	Long getDeviceId(Long sellerId);
}
