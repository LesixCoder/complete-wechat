package com.lunchtasting.server.biz.user;

import java.util.List;

public interface UserDepositDrawBIZ {
	
	/**
	 * 判断用户是否可以提现
	 * @param userId
	 * @return
	 */
	Boolean checkDraw(Long userId);
	
	/**
	 * 创建用户提现记录
	 * @param userId
	 * @param price
	 */
	Boolean createUserDepositDraw(Long userId, Double money
			,String account,String phone,String name) throws Exception;
	
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
	
	/**
	 * 获得几天内用户已经提现的次数（
	 * @param userId
	 * @return
	 */
	Integer getNowDrawCount(Long userId,Integer day);	
}
