package com.lunchtasting.server.dao.activity;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Activity;

public interface ActivityDAO extends GenericDAO<Activity> {

	/**
	 * 获得获得数量
	 * @return
	 */
	Integer getActivityCount();
	
	/**
	 * 首页（包括活动，文章）
	 */
	Integer getIndexCount();
	
	/**
	 * 首页（包括活动，文章）
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryIndexList(Integer page,Integer pageSize);
	
	/**
	 * 获得活动列表
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryActivityList(Integer page,Integer pageSize);
	
	/**
	 * 获得活动详情
	 * @param activityId
	 * @return
	 */
	Map getActivityDetail(Long activityId);
}
