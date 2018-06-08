package com.lunchtasting.server.biz.activity;

public interface ActivityEnrollBIZ {
	
	/**
	 * 创建活动报名
	 * @param userId
	 * @param activityId
	 * @param name
	 * @param phone
	 * @return
	 */
	Boolean createEnroll(Long userId,Long activityId,String name,String phone);

	/**
	 * 获得活动报名人总数
	 * @param activityId
	 * @return
	 */
	Integer getEnrollCount(Long activityId);
	
	/**
	 * 判断当前活动是否可以报名
	 * @param activityId
	 * @param userId
	 * @return
	 */
	Boolean checkIsEnroll(Long activityId,Long userId);
	
	/**
	 * 判断某活动用户是否已经报名
	 * @param activityId
	 * @param userId
	 * @return
	 */
	Long getEnrollId(Long activityId,Long userId);
	
}
