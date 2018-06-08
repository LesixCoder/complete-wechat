package com.lunchtasting.server.biz.message.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.message.MessageNotificationBIZ;
import com.lunchtasting.server.dao.message.MessageNotificationDAO;

@Service
public class MessageNotificationBIZImpl implements MessageNotificationBIZ {

	@Autowired
	private MessageNotificationDAO notificationDAO;

	@Override
	public Integer getNotificationCount(Long desUserId) {
		return null;
	}

	@Override
	public List queryNotificationList(Long desUserId, Integer page,
			Integer pageSize) {
		return null;
	}

	
}
