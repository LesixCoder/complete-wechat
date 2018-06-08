package com.lunchtasting.server.dao.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	/**
	 * 获得活动的分享详情
	 * @param activityId
	 * @return
	 */
	Map getShareDetail(Long activityId);
	
	/**
	 * 获得场馆下的活动列表
	 * @param sellerId
	 * @return
	 */
	List queryVenueActivityList(Long sellerId);
}
