package com.lunchtasting.server.dao.activity;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Activity;


public interface ActivityDAO extends GenericDAO<Activity> {
	/**
	 * 查询首页列表
	 * @param map
	 * @return
	 */
	List queryActivityList(HashMap map);
	
	/**
	 * 查询全部列表
	 * @param map
	 * @return
	 */
	List queryAllList(HashMap map);
	
	/**
	 * 根据id查询活动
	 * @param map
	 * @return
	 */
	Activity queryActivityById(HashMap map);
}
