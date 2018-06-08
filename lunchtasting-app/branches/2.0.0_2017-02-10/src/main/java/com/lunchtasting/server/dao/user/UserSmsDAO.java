package com.lunchtasting.server.dao.user;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.UserSms;


public interface UserSmsDAO extends GenericDAO<UserSms> {
	
	/**
	 * 获取用户验证码
	 * @param phone
	 * @param type
	 * @return
	 */
	UserSms getByPhoneAndType(String phone,Integer type);
	
	/**
	 * 修改手机验证码已经失效
	 * @param id
	 * @return
	 */
	Integer updateCodeExpire(Long id);
}
