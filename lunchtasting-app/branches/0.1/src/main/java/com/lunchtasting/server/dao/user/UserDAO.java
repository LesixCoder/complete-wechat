package com.lunchtasting.server.dao.user;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.User;

public interface UserDAO extends GenericDAO<User> {

	/**
	 * 通过号码获得用户
	 * @param phone
	 * @return
	 */
	String getByPhone(String phone);
	
	/**
	 * 通过号码密码获得用户
	 * @param phone
	 * @param pwd
	 * @return
	 */
	User getByPhoneAndPwd(String phone,String pwd);
}
