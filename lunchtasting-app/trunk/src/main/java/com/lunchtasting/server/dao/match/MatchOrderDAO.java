package com.lunchtasting.server.dao.match;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.MatchOrder;

public interface MatchOrderDAO extends GenericDAO<MatchOrder> {

	/**
	 * 获得当前活动订单（报名）人数
	 * @param MatchId
	 * @return
	 */
	Integer getMatchOrderCount(Long matchId);
	
	/**
	 * 判断用户是否报名某个活动
	 * @param matchId
	 * @param userId
	 * @return
	 */
	Integer getByMatchIdAndUserId(Long matchId,Long userId);
	
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
	List queryUserMatchOrderList(Long userId,Integer page,Integer pageSize);
}
