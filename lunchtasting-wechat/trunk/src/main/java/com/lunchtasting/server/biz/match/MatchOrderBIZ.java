package com.lunchtasting.server.biz.match;

import java.util.List;
import java.util.Map;

public interface MatchOrderBIZ {
	
	/**
	 * 赛事报名订单支付
	 * @return
	 * @throws Exception
	 */
	Long createMatchOrder(Long userId,Long matchId,Long ticketId,Double price,Double ticketPrice,Double goodsPrice,
				String signupStr,String goodsStr)throws Exception;
	
	/**
	 * 微信支付回调防止异步多次通知判断
	 * @param id
	 * @return
	 */
	Boolean checkOrderPay(Long orderId);
	
	
	/**
	 * 支付成功操作
	 * @param orderId
	 * @param inviteUserId
	 */
	Boolean updateOrderPay(Long orderId) throws Exception;
	
	/**
	 * 查询用户订单列表
	 * @param matchId
	 * @param userId
	 * @param status
	 * @return
	 */
	List queryUserOrderList(Long matchId,Long userId,Integer status);
	
	
//	/**
//	 * 参赛用户报名
//	 * @param userId
//	 * @param matchId
//	 * @param phone
//	 * @param price
//	 * @param name
//	 * @param sex
//	 * @param birth
//	 * @param realName
//	 * @param certificate
//	 * @param contactName
//	 * @param contactPhone
//	 * @param smsId
//	 * @param matchCodeId
//	 * @param isPay
//	 * @return
//	 * @throws Exception
//	 */
//	Long createMatchOrder(Long userId,Long matchId,String phone,Double price
//			,String name,Integer sex,String birth,String realName,String certificate
//			,String contactName,String contactPhone,Long smsId,Long matchCodeId,Integer isPay,Long inviteUserId) throws Exception;
//	
//	/**
//	 * 观赛票报名
//	 * @param userId
//	 * @param matchId
//	 * @param phone
//	 * @param price
//	 * @param name
//	 * @param sex
//	 * @param smsId
//	 */
//	void createMatchWatchOrder(Long userId,Long matchId,String phone,Double price,String name,Integer sex,Long smsId
//			,Long inviteUserId,String channel);
//	
//	/**
//	 * 公益票报名
//	 * @param userId
//	 * @param matchId
//	 * @param phone
//	 * @param price
//	 * @param name
//	 * @param sex
//	 * @param smsId
//	 * @return
//	 */
//	Long createMatchGongyiOrder(Long userId,Long matchId,String phone,Double price,String name,Integer sex,Long smsId,Long inviteUserId);
//
//	
//	/**
//	 * 是否报名
//	 * @param tel
//	 * @param userId
//	 * @param matchId
//	 * @param type
//	 * @return
//	 */
//	boolean verify(String tel,Long userId,Long matchId,Integer type);
//	/**
//	 * 查看支付页面(未支付订单信息)
//	 * @param id
//	 * @return
//	 */
//	Map getOrderDetail(Long orderId,Long userId);
//	
//	/**
//	 * 微信支付回调防止异步多次通知判断
//	 * @param id
//	 * @return
//	 */
//	boolean orderWeChatPay(Long id);
//	
//	/**
//	 * 支付成功操作
//	 * @param orderId
//	 * @param inviteUserId
//	 */
//	void payOrder(Long orderId ,Long inviteUserId) throws Exception;
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
