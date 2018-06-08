package com.lunchtasting.server.biz.message;

import java.util.List;

public interface MessageNotificationBIZ {
	
	/**
	 * 获得消息总数
	 * @param desUserId
	 * @return
	 */
	Integer getNotificationCount(Long desUserId);
	
	/**
	 * 查询系统消息列表
	 * @param desUserId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryNotificationList(Long desUserId,Integer page,Integer pageSize);
}
