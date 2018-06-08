package com.lunchtasting.server.dao.activity;

import java.util.HashMap;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.ActivityEnroll;


public interface ActivityEnrollDAO extends GenericDAO<ActivityEnroll> {
	/**
	 * 查询报名个数
	 * @param map
	 * @return
	 */
	Integer queryActivityByMapCount(HashMap map);
	
	
}
