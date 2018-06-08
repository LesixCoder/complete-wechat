package com.lunchtasting.server.dao.activity;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.ActivityComment;

public interface ActivityCommentDAO extends GenericDAO<ActivityComment>{

	/**
	 * 活动评论总数
	 * @param activityId
	 * @return
	 */
	Integer getCommentCount(Long activityId);
	
	/**
	 * 活动评论列表
	 * @param activityId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryCommentList(Long activityId,Integer page,Integer pageSize);
}
