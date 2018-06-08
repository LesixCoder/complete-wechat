package com.lunchtasting.server.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.user.UserAdminBIZ;
import com.lunchtasting.server.dao.user.UserAdminDAO;
import com.lunchtasting.server.po.lt.UserAdmin;

@Service
public class UserAdminBIZImpl implements UserAdminBIZ {

	@Autowired
	private UserAdminDAO userAdminDAO;
	
	@Override
	public UserAdmin getByAccountAndPwd(String account, String pwd) {
		return userAdminDAO.getByAccountAndPwd(account, pwd);
	}

	
}
