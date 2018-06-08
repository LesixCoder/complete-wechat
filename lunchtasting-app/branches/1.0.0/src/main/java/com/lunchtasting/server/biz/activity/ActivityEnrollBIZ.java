package com.lunchtasting.server.biz.activity;

import java.util.List;

public interface ActivityEnrollBIZ {
	
	/**
	 * 创建活动报名
	 * @param userId
	 * @param activityId
	 * @param name
	 * @param phone
	 * @param sex
	 * @param age
	 * @param remark
	 * @param smsId
	 * @return
	 */
	Boolean createEnroll(Long userId,Long activityId,String name,String phone
			,Integer sex,Integer age,String remark,Long smsId);

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
	
	/**
	 * 获得用户报名的活动总数
	 * @param userId
	 * @return
	 */
	Integer getUserEnrollerCount(Long userId);
	
	/**
	 * 获得用户报名的活动列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryUserEnrollerList(Long userId,Integer page,Integer pageSize) throws Exception;
	
}
