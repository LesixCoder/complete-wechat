package com.lunchtasting.server.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.user.UserWeChatBIZ;
import com.lunchtasting.server.dao.user.UserWeChatDAO;
import com.lunchtasting.server.po.lt.UserWeChat;

@Service
public class UserWeChatBIZImpl implements UserWeChatBIZ {
	
	@Autowired
	private UserWeChatDAO userWeChatDAO;

	@Override
	public UserWeChat getByOpenId(String openId) {
		return userWeChatDAO.getByOpenId(openId);
	}

	@Override
	public UserWeChat getByUserId(Long userId) {
		return userWeChatDAO.getByUserId(userId);
	}

}
