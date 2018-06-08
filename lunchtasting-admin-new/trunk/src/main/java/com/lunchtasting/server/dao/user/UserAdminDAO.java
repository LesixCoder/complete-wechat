package com.lunchtasting.server.dao.user;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.UserAdmin;

public interface UserAdminDAO extends GenericDAO<UserAdmin> {

	/**
	 * 用户登录
	 * @param account
	 * @param pwd
	 * @return
	 */
	UserAdmin getByAccountAndPwd(String account,String pwd);
	
}
