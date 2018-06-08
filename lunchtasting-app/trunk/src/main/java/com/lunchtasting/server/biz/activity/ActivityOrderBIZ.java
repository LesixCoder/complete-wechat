package com.lunchtasting.server.biz.activity;

import java.util.List;

public interface ActivityOrderBIZ {
	
	/**
	 * 活动报名(不需要费用)
	 * @param activityId
	 * @param userId
	 * @return
	 */
	Boolean createActivityOrder(Long activityId,Long userId);
	
	/**
	 * 获得当前活动订单（报名）人数
	 * @param activityId
	 * @return
	 */
	Integer getActivityOrderCount(Long activityId);
	
	/**
	 * 判断用户是否报名
	 * @param activityId
	 * @param userId
	 * @return
	 */
	Boolean checkUserEnroll(Long activityId,Long userId);
	
	/**
	 * 获得用户的活动报名人数
	 * @param userId
	 * @return
	 */
	Integer getUserActivityOrderCount(Long userId);
	
	/**
	 * 获得用户的活动报名列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryUserActivityOrderList(Long userId,Integer page,Integer pageSize) throws Exception;
	
}
