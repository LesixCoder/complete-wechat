package com.lunchtasting.server.dao;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.UserAdmin;

public interface AdminUserDao extends GenericDAO<UserAdmin>{
	
	UserAdmin systemUsersLogin(String account,String pwd);
}
