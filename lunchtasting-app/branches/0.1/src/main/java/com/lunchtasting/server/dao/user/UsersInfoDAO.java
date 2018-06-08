package com.lunchtasting.server.dao.user;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.UsersInfo;

public interface UsersInfoDAO extends GenericDAO<UsersInfo> {

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
	UsersInfo getByPhoneAndPwd(String phone,String pwd);
}
