package com.lunchtasting.server.biz.match;

import java.util.List;

public interface MatchOrderBIZ {

	/**
	 * 赛事报名(不需要费用)
	 * @param matchId
	 * @param userId
	 * @return
	 */
	Boolean createMatchOrder(Long matchId,Long userId);
	
	/**
	 * 获得当前活动订单（报名）人数
	 * @param MatchId
	 * @return
	 */
	Integer getMatchOrderCount(Long matchId);
	
	/**
	 * 判断用户是否报名
	 * @param MatchId
	 * @param userId
	 * @return
	 */
	Boolean checkUserEnroll(Long matchId,Long userId);
	
	/**
	 * 获得用户的活动报名人数
	 * @param userId
	 * @return
	 */
	Integer getUserMatchOrderCount(Long userId);
	
	/**
	 * 获得用户的活动报名列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryUserMatchOrderList(Long userId,Integer page,Integer pageSize) throws Exception;
}
