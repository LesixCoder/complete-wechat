package com.lunchtasting.server.biz.message;

import java.util.List;
import java.util.Map;

public interface MessageNotificationBIZ {
	
	/**
	 * 创建消息并推送消息
	 * @param srcUserId
	 * @param desUserId
	 * @param bizId
	 * @param bizType
	 * @param title
	 * @param content
	 * @param imgUrl
	 * @return
	 */
	Boolean pushMessage(Long srcUserId,Long desUserId,Long bizId,Integer bizType,
				String title,String content,String imgUrl);
	
	/**
	 * 获得消息总数
	 * @param userId
	 * @return
	 */
	Integer getNotificationCount(Long userId);
	
	/**
	 * 查询系统消息列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryNotificationList(Long userId,Integer page,Integer pageSize) throws Exception;
	
	/**
	 * 获得评论消息总数
	 * @param userId
	 * @return
	 */
	Integer getCommentNotificationCount(Long userId);
	
	/**
	 * 获得评论消息列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryCommentNotificationList(Long userId,Integer page,Integer pageSize)throws Exception;
	
	/**
	 * 获得点赞消息总数
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Integer getLikeNotificationCount(Long userId);
	
	/**
	 * 获得点赞消息列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryLikeNotificationList(Long userId,Integer page,Integer pageSize)throws Exception;
	
	/**
	 * 获得所有消息的未读数字
	 * @param userId
	 * @return
	 */
	Integer getUnreadNotificationCount(Long userId);
	
	/**
	 * 获得用户未读的消息各类型总数
	 * @param userId
	 * @return
	 */
	Map getUnreadMap(Long userId);
	
	/**
	 * 修改某个消息已读
	 * @param userId
	 * @param bizType
	 */
	void updateIsRead(Long userId,Integer bizType);
	
	/**
	 * 修改通知消息里面全部消息已读
	 * @param userId
	 */
	void updateNotifyIsRead(Long userId);
	
	/**
	 * 判断好友是否多次关注推送，避免骚扰推送
	 * @param srcUserId
	 * @param desUserId
	 * @return
	 */
	Boolean checkSameFriend(Long srcUserId,Long desUserId);
	
	/**
	 * 判断重复人多次喜欢同一条帖子，避免骚扰推送
	 * @param srcUserId
	 * @param desUserId
	 * @param bizId
	 * @return
	 */
	Boolean checkSameNoteLike(Long srcUserId,Long desUserId,Long bizId);
}



