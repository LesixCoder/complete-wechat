package com.lunchtasting.server.biz.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.biz.AdminUserBIZ;
import com.lunchtasting.server.dao.AdminUserDao;
import com.lunchtasting.server.po.lt.UserAdmin;
@Service
public class AdminUserBIZImpl implements AdminUserBIZ{

	@Autowired
	private AdminUserDao adminUserDao;
	
	public UserAdmin systemUsersLogin(String account,String pwd) {
		return adminUserDao.systemUsersLogin(account, pwd);
	}

}
