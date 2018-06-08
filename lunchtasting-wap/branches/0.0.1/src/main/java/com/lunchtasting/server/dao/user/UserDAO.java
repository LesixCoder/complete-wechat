package com.lunchtasting.server.dao.user;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.User;


public interface UserDAO extends GenericDAO<User> {

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
	
	/**
	 * 更新用户 
	 * @param map
	 * @return
	 */
	Integer updateUserResult(User user);
}
