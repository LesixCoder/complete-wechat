package com.lunchtasting.server.dao.match;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.MatchOrder;

public interface MatchOrderDAO extends GenericDAO<MatchOrder> {
	
	/**
	 * 创建赛事报名订单
	 * @param map
	 * @return
	 */
	Integer createMatchOrder(Map map);

	/**
	 * 创建赛事报名订单商品
	 * @param map
	 * @return
	 */
	Integer createMatchOrderGoods(Map map);
	
	/**
	 * 修改订单已支付
	 * @param orderId
	 * @return
	 */
	Integer updateOrderPay(Long orderId);
	
	/**
	 * 查询用户订单列表
	 * @param matchId
	 * @param userId
	 * @param status
	 * @return
	 */
	List queryUserOrderList(Long matchId,Long userId,Integer status);
	
//	/**
//	 * 获得当前活动订单（报名）人数
//	 * @param MatchId
//	 * @return
//	 */
//	Integer getMatchOrderCount(Long matchId);
//	
//	/**
//	 * 判断是否报名赛事
//	 * @param matchId
//	 * @param userId
//	 * @param phone
//	 * @param type
//	 * @return
//	 */
//	Integer getIsSignUp(Long matchId,Long userId,String phone,Integer type);
//	
//	/**
//	 * 获得用户的活动报名人数
//	 * @param userId
//	 * @return
//	 */
//	Integer getUserMatchOrderCount(Long userId);
//	
//	/**
//	 * 获得用户的活动报名列表
//	 * @param userId
//	 * @param page
//	 * @param pageSize
//	 * @return
//	 */
//	List queryUserMatchOrderList(Long userId,Integer page,Integer pageSize);
//	
//	/**
//	 * 查询订单详细
//	 * @param map
//	 * @return
//	 */
//	Map getOrderDetail(Long orderId,Long userId);
//	
//	/**
//	 * 修改订单状态
//	 * @param orderId
//	 * @param status
//	 * @return
//	 */
//	Integer updateStatus(Long orderId,Integer status);
//	
//	/**
//	 * 获得已经付款的异性订单用户
//	 * @param matchId
//	 * @param sex
//	 * @param size
//	 * @return
//	 */
//	List queryMatchOrderUserList(Long matchId,Integer sex,Integer size);
//	
//	/**
//	 * 获得邀请码订单支付的数量
//	 * @param matchId
//	 * @param matchCodeId
//	 * @return
//	 */
//	Integer getMatchCodeCount(Long matchId,Long matchCodeId);
//	
//	/**
//	 * 获得用户报名信息
//	 * @param userId
//	 * @param matchId
//	 * @param type
//	 * @return
//	 */
//	Map getUserMatchOrder(Long userId,Long matchId,Integer type);
//	
//	/**
//	 * 查询参赛付款用户列表
//	 * @param matchId
//	 * @param userId
//	 * @param name
//	 * @param sortType
//	 * @param page
//	 * @param pageSize
//	 * @return
//	 */
//	List queryOrderPayUserList(Long matchId,Long userId,String name,Integer sortType,Integer page,Integer pageSize);
//	
//	/**
//	 *查询参赛付款用户总数 
//	 * @param matchId
//	 * @param name
//	 * @return
//	 */
//	Integer getOrderPayUserCount(Long matchId,String name);
//	
//	/**
//	 * 获得用户报名信息（临时使用）
//	 * @param userId
//	 * @param matchId
//	 * @param type
//	 * @param size
//	 * @return
//	 */
//	Map getUserMatchOrderTest(Long userId,Long matchId,Integer type,Integer start,Integer end);
//	
//	/**
//	 * 获得用户某赛事的公益助跑信息
//	 * @param matchId
//	 * @param userId
//	 * @return
//	 */
//	Integer getMatchGongyi(Long matchId,Long userId);
//	
//	/**
//	 * 获得用户的报名总数（判断用户是否报过名）
//	 * @param matchId
//	 * @param userId
//	 * @return
//	 */
//	Integer getUserMatchCount(Long matchId,Long userId);
}
