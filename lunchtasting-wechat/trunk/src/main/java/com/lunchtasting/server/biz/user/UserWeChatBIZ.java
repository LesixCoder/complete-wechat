package com.lunchtasting.server.biz.user;

import java.util.HashMap;

import com.lunchtasting.server.po.lt.UserWeChat;

public interface UserWeChatBIZ {
	
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
	 * 获得微信唯一标示
	 * @param userId
	 * @return
	 */
	String getOpenIdByUserId(Long userId);
	
}
