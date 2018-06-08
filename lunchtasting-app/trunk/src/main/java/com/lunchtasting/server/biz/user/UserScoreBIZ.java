package com.lunchtasting.server.biz.user;

import java.util.List;

public interface UserScoreBIZ {
	
	/**
	 * 获得用户积分获取方式的总数
	 * @param userId
	 * @return
	 */
	Integer getUserScoreCount(Long userId);
	
	/**
	 * 获得用户积分列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryUserScoreList(Long userId,Integer page,Integer pageSize);
	
	/**
	 * 获得用户积分数量的总数
	 * @param userId
	 * @return
	 */
	Integer getUserScoreTotal(Long userId);
	
	/**
	 * 榜单
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryUserScoreRank(Integer page,Integer pageSize);
	/**
	 * 个人排名
	 * @param UserId
	 * @return
	 */
	Integer getUserScoreRank(Long userId);
	/**
	 * 积分榜总个数
	 * @return
	 */
	Integer getScoreRankCount();
	
	/**
	 * 注册补全
	 * @param phone
	 * @return
	 */
	boolean completion(String phone,Long userId);
}
