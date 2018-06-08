package com.lunchtasting.server.biz.user;

import java.util.HashMap;

public interface WechatBIZ {
	/**
	 * 微信登录
	 * @param map
	 * @return
	 */
	HashMap webchatLogin(String code)throws Exception;;
}
