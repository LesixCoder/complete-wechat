package com.lunchtasting.server.biz.activity;

import java.util.List;

public interface ActivityCommentBIZ {
	
	/**
	 * 创建活动评论
	 * @param activityId
	 * @param userId
	 * @param content
	 * @param imgArray
	 * @param parentId
	 * @param parentUserId
	 * @return
	 */
	Boolean createComment(Long activityId,Long userId,String content,String imgArray
			,String parentId,String parentUserId);
	
	/**
	 * 活动评论总数
	 * @param activityId
	 * @return
	 */
	Integer getCommentCount(Long activityId);
	
	/**
	 * 活动评论列表
	 * @param activityId
	 * @param activityUserId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryCommentList(Long activityId,Long activityUserId,Integer page,Integer pageSize);
}
