package com.lunchtasting.server.biz.user;

import com.lunchtasting.server.po.lt.UserSms;

public interface UserSmsBIZ {
	
	
	/**
	 * 获取用户验证码
	 * @param phone
	 * @param type
	 * @return
	 */
	UserSms getByPhoneAndType(String phone,Integer type);
	
	/**
	 * 修改手机验证码已经失效
	 * @param phone
	 * @return
	 */
	Integer updateCodeExpire(Long id);
	
	/**
	 * 发送短信码
	 * @param phone
	 * @param type
	 * @return
	 */
	Boolean createUserSms(String phone,Integer type);
}
