package com.lunchtasting.server.biz;

import com.lunchtasting.server.po.lt.UserAdmin;

public interface AdminUserBIZ {
	
	UserAdmin systemUsersLogin(String account,String pwd);
}
