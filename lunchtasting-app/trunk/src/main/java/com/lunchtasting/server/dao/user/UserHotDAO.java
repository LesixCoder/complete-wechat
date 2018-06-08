package com.lunchtasting.server.dao.user;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.UserHot;

public interface UserHotDAO extends GenericDAO<UserHot>{
	/**
	 * 查询排名列表
	 * @param page
	 * @param pageSize
	 * @param time
	 * @return
	 */
	List queryUserHotList(Integer page,Integer pageSize,Integer time);
	
	Integer updateUserHot(UserHot userHot);
	/**
	 * 查询个数
	 * @param page
	 * @param pageSize
	 * @param time
	 * @return
	 */
	Integer getUserHotCount(Integer time);
	
	/**
	 * 新建userhot
	 * @param userId
	 * @param number
	 * @param type
	 */
	void createUserHot(Long userId,Integer number,Integer type);
	/**
	 * 根据类型查询个数
	 * @param userId
	 * @param type
	 * @return
	 */
	Integer getTypeCount(Long userId,Integer type);
	
	/**
	 * 用户活跃度
	 * @param userId
	 * @param time
	 * @return
	 */
	Integer getUserHotByUser(Long userId,Integer time);
	/**
	 * 用户排名
	 * @param userId
	 * @param time
	 * @return
	 */
	Integer getUserHotRank(Long userId,Integer time);
}
