package com.lunchtasting.server.biz;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.User;

public interface AdminUserForNoteBIZ {
	List queryUserList();
	List queryUser1();
	List queryUser2();
	int deleteUser(String id);
	Long saveUser(User user);
	Long updateUser(User user);
	User queryUserById(String id);
	HashMap queryUserList(HashMap map);
	int queryUserByPhone(String phone);
	Long saveDevice(Long id, Long userId);
}
