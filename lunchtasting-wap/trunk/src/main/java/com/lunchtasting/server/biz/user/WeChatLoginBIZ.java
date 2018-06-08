package com.lunchtasting.server.biz.user;

import java.util.HashMap;

public interface WeChatLoginBIZ {
	/**
	 * 微信登录
	 * @param map
	 * @return
	 */
	HashMap webchatLogin(String code)throws Exception;;
}
