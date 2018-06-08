package com.lunchtasting.server.biz.user.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.biz.user.UserWeChatBIZ;
import com.lunchtasting.server.dao.user.UserDAO;
import com.lunchtasting.server.dao.user.UserWeChatDAO;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.WeChatHelper;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.po.lt.UserWeChat;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class UserWeChatBIZImpl implements UserWeChatBIZ {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserWeChatDAO userWeChatDAO;

	@Override
	public UserWeChat getByOpenId(String openId) {
		return userWeChatDAO.getByOpenId(openId);
	}
	
	@Override
	public UserWeChat getByUnionId(String unionId) {
		return userWeChatDAO.getByUnionId(unionId);
	}

	@Override
	public UserWeChat getByUserId(Long userId) {
		return userWeChatDAO.getByUserId(userId);
	}

	@Override
	public String getOpenIdByUserId(Long userId) {
		return userWeChatDAO.getOpenIdByUserId(userId);
	}

}
