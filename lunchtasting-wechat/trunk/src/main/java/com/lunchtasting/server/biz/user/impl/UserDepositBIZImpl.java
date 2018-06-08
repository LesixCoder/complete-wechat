package com.lunchtasting.server.biz.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.user.UserDepositBIZ;
import com.lunchtasting.server.dao.user.UserDepositDAO;

@Service
public class UserDepositBIZImpl implements UserDepositBIZ {

	@Autowired
	private UserDepositDAO userDepositDAO;

	@Override
	public Double getUserDepositTotal(Long userId) {
		return userDepositDAO.getUserDepositTotal(userId);
	}

	@Override
	public List queryUserDepositList(Long userId, Integer page, Integer pageSize) {
		return userDepositDAO.queryUserDepositList(userId, page, pageSize);
	}

	@Override
	public Integer getUserDepositCount(Long userId) {
		return userDepositDAO.getUserDepositCount(userId);
	}


}
