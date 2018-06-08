package com.lunchtasting.server.dao.activity;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.ActivityOrder;

public interface ActivityOrderDAO  extends GenericDAO<ActivityOrder> {

	/**
	 * 获得当前活动订单（报名）人数
	 * @param activityId
	 * @return
	 */
	Integer getActivityOrderCount(Long activityId);
	
	/**
	 * 判断用户是否报名某个活动
	 * @param activityId
	 * @param userId
	 * @return
	 */
	Integer getByActivityIdAndUserId(Long activityId,Long userId);
	
	
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
	List queryUserActivityOrderList(Long userId,Integer page,Integer pageSize);
}
