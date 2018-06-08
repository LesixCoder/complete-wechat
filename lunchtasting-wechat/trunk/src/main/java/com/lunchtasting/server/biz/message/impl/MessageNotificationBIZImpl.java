package com.lunchtasting.server.biz.message.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.message.MessageNotificationBIZ;
import com.lunchtasting.server.dao.mssage.MessageNotificationDAO;

@Service
public class MessageNotificationBIZImpl implements MessageNotificationBIZ {

	@Autowired
	private MessageNotificationDAO notificationDAO;
}
