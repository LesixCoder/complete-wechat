package com.lunchtasting.server.dao.gym;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.gym.Gym;

public interface GymDAO extends GenericDAO<Gym> {

	/**
	 * 查询场馆列表
	 * @param flag
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryGymList(Integer flag,Integer page,Integer pageSize);
	
	/**
	 * 查询简单所有健身房信息	
	 * @return
	 */
	List querySimpleGymList();
	
	/**
	 * 获得需要编辑的健身房信息
	 * @param gymId
	 * @return
	 */
	Map getEditGym(Long gymId);
	
	/**
	 * 修改状态
	 * @param gymId
	 * @param flag
	 * @return
	 */
	Integer updateFlag(Long gymId,Integer flag);
	
}
