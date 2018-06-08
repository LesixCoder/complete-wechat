package com.lunchtasting.server.biz.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.user.UserDepositDrawBIZ;
import com.lunchtasting.server.dao.user.UserDepositDrawDAO;

@Service
public class UserDepositDrawBIZImpl implements UserDepositDrawBIZ {

	@Autowired
	private UserDepositDrawDAO drawDAO;

	@Override
	public List queryDrawList(Long userId, Integer status, String beginTime,
			String endTime, Integer page, Integer pageSize) {
		return drawDAO.queryDrawList(userId, status, beginTime, endTime, page, pageSize);
	}

	@Override
	public Integer getDrawCount(Long userId, Integer status, String beginTime,
			String endTime) {
		return drawDAO.getDrawCount(userId, status, beginTime, endTime);
	}

	@Override
	public Integer updateStatusError(Long drawId) {
		return drawDAO.updateStatusError(drawId);
	}

	@Override
	public Integer updateStatusSuccess(Long drawId) {
		return drawDAO.updateStatusSuccess(drawId);
	}
	
}
