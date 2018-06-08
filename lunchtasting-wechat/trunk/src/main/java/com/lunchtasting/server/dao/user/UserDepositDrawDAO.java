package com.lunchtasting.server.dao.user;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.UserDepositDraw;

public interface UserDepositDrawDAO extends GenericDAO<UserDepositDraw> {
	
	/**
	 * 获得几天内用户已经提现的次数（
	 * @param userId
	 * @return
	 */
	Integer getNowDrawCount(Long userId,Integer day);
	
	/**
	 * 查询用户退款列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryDrawList(Long userId,Integer page,Integer pageSize);
	
	/**
	 * 获得用户退款总数
	 * @param userId
	 * @return
	 */
	Integer getDrawCount(Long userId);
}
