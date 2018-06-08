package com.lunchtasting.server.biz.user;

import java.util.List;

public interface UserDepositBIZ {
	
	/**
	 * 获得用户分红存款的总数
	 * @param userId
	 * @return
	 */
	Double getUserDepositTotal(Long userId);
	
	/**
	 * 查询用户存款记录
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryUserDepositList(Long userId,Integer page,Integer pageSize);
	
	/**
	 * 查询用户存款总数
	 * @param userId
	 * @return
	 */
	Integer getUserDepositCount(Long userId);
	
}
