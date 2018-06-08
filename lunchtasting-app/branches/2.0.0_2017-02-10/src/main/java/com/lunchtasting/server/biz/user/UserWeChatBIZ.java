package com.lunchtasting.server.biz.user;

import com.lunchtasting.server.po.lt.UserWeChat;

public interface UserWeChatBIZ {
	
	/**
	 * 获得微信授权信息
	 * @param openId
	 * @return
	 */
	UserWeChat getByOpenId(String openId);
	
	/**
	 * 获得微信授权信息
	 * @param openId
	 * @return
	 */
	UserWeChat getByUserId(Long userId);
}
