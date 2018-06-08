package com.lunchtasting.server.biz.activity;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.po.lt.Activity;

public interface ActivityBIZ {
	
	Activity getById(Long id);
	
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
	 * 获得活动数量
	 * @return
	 */
	Integer getActivityCount();
	
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
