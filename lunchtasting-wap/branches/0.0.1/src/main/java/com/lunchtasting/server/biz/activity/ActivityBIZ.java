package com.lunchtasting.server.biz.activity;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.Activity;

public interface ActivityBIZ {
	
	/**
	 * 查询活动列表
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryList(HashMap map);
	
	/**
	 * 报名活动
	 * @param userId
	 * @param activityId
	 * @return
	 */
	HashMap addActivityenroll(HashMap map);
	
	/**
	 * 根据id查询活动
	 * @param id
	 * @return
	 */
	Activity queryActivityById(Long id);
	
	/**
	 * 得到已经报名的人数
	 * @param activityId
	 * @return
	 */
	Integer queryApplyNum(Long activityId);
}
