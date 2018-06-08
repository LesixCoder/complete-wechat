package com.lunchtasting.server.dao.user;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.UserWeChat;

public interface UserWeChatDAO extends GenericDAO<UserWeChat> {

	/**
	 * 获得微信授权信息
	 * @param openId
	 * @return
	 */
	UserWeChat getByOpenId(String openId);
	
	UserWeChat getByUnionId(String unionId);

	
	/**
	 * 获得微信授权信息
	 * @param openId
	 * @return
	 */
	UserWeChat getByUserId(Long userId);
	
	/**
	 * 修改微信授权信息
	 * @param userWeChat
	 * @return
	 */
	Integer updateWeChat(UserWeChat userWeChat);
	
	/**
	 * 获得微信唯一标示
	 * @param userId
	 * @return
	 */
	String getOpenIdByUserId(Long userId);
}
