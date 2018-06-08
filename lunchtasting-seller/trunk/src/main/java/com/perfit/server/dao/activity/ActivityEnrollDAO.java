package com.perfit.server.dao.activity;

import java.util.HashMap;
import java.util.List;
import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.ActivityEnroll;

public interface ActivityEnrollDAO extends  GenericDAO<ActivityEnroll> {
	/**
	 * 查询活动对应的报名列表
	 * @param map
	 * @return
	 */
	List queryEnrollerList(HashMap map);
	
	/**
	 * 查询活动对应的报名个数
	 * @param map
	 * @return
	 */
	Integer queryEnrollerListCount(Long activityId);
}
