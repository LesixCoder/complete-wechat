package com.lunchtasting.server.dao.mssage.impl;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.mssage.MessageNotificationDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.MessageNotification;

@Repository
public class MessageNotificationDAOImpl extends GenericDAOSupport<Long, MessageNotification>
	implements MessageNotificationDAO {

	@Override
	protected Class<?> getObjectClass() {
		return MessageNotification.class;
	}

	@Override
	protected String getTableName() {
		return "message_notification";
	}

}
