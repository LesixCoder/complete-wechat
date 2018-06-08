package com.lunchtasting.server.biz.user;

import com.lunchtasting.server.po.lt.UserAdmin;

public interface UserAdminBIZ {
	
	/**
	 * 用户登录
	 * @param account
	 * @param pwd
	 * @return
	 */
	UserAdmin getByAccountAndPwd(String account,String pwd);
}
