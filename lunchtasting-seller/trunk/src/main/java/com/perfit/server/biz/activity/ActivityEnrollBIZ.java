package com.perfit.server.biz.activity;

import java.util.HashMap;
import java.util.List;

public interface ActivityEnrollBIZ {
	/**
	 * 根据id查询报名列表
	 * @param activityId
	 * @return
	 */
	List queryEnrollerList(HashMap map);
	
	/**
	 * 查询报名的总人数
	 */
	Integer queryEnrollerListCount(Long activityId); 
}
