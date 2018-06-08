package com.lunchtasting.server.biz.user;

import java.util.List;

import com.lunchtasting.server.po.lt.User;

public interface UserBIZ {
	
	/**
	 * 通过手机号获得用户
	 * @param phone
	 * @return
	 */
	User getUserByPhone(String phone);
	
	/**
	 * 查询用户列表
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryUserList(Integer page,Integer pageSize);
}
