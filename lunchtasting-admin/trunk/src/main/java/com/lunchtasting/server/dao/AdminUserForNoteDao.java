package com.lunchtasting.server.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.User;

public interface AdminUserForNoteDao extends GenericDAO<User>{
	List queryUserList();
	List queryUser1();
	List queryUser2();
	int deleteUser(String id);
	Long saveUser(User user);
	Long updateUser(User user);
	User queryUserById(String id);
	int queryUserCount(HashMap map);
	List queryUserList(HashMap map);
	int queryUserByPhone(String phone);
	Long saveDevice(Long id,Long userId);
	Map getUserByPhone(String tel);
}
