package com.lunchtasting.server.biz.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.user.UserBIZ;
import com.lunchtasting.server.dao.user.UserDAO;
import com.lunchtasting.server.po.lt.User;

@Service
public class UserBIZImpl implements UserBIZ {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public User getUserByPhone(String phone) {
		return userDAO.getUserByPhone(phone);
	}

	@Override
	public List queryUserList(Integer page, Integer pageSize) {
		return userDAO.queryUserList(page, pageSize);
	}
	
	
}
