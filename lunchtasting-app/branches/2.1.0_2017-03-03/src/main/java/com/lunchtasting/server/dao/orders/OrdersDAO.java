package com.lunchtasting.server.dao.orders;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Orders;

public interface OrdersDAO extends GenericDAO<Orders> {

	/**
	 * 查询订单详情
	 * @param orderId
	 * @param userId
	 * @return
	 */
	Map getOrderDetail(Long orderId,Long userId);
	
	/**
	 * 查询订单总数
	 * @param userId
	 * @param status
	 * @return
	 */
	Integer getOrderCount(Long userId,Integer status);
	
	/**
	 * 查询订单列表
	 * @param userId
	 * @param status
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryOrderList(Long userId,Integer status, Integer page,Integer pageSize);
	
	/**
	 * 获得一个未支付的订单
	 * @param orderId
	 * @param userId
	 * @return
	 */
	Map getNoPayOrder(Long orderId,Long userId);
	
	/**
	 * 修改订单状态
	 * @param orderId
	 * @param status
	 * @return
	 */
	Integer updateStatus(Long orderId,Integer status);
	
	/**
	 * 判断第三方(支付宝/微信)通知的订单是否存在
	 * @param code
	 * @param payPrice
	 * @return
	 */
	Long getByCodeAndPayPrice(String outTradeNo,Double payPrice);
	
	/**
	 * 获得退款订单
	 * @param orderId
	 * @param userId
	 * @return
	 */
	Map getRefundOrder(Long orderId,Long userId);
	
	/**
	 * 获得用户贷评论订单
	 * @param orderId
	 * @param userId
	 * @return
	 */
	Map getCommentOrder(Long orderId,Long userId);
	
	/**
	 * 修改订单已退款/过期
	 * @param orderId
	 * @param status
	 * @return
	 */
	Integer updateOrderRefund(Long orderId,Integer status);
	
	/**
	 * 通过订单号获得订单
	 * @param outTradeNo
	 * @return
	 */
	Map getOrderByOutTradeNo(String outTradeNo);
	
	/**
	 * 获得订单
	 * @param orderId
	 * @param userId
	 * @return
	 */
	Map getByOrderIdAndUserId(Long orderId,Long userId);
	
	/**
	 * 修改订单已支付
	 * @param orderId
	 * @param payType
	 * @param status
	 * @return
	 */
	Integer updateIsPay(Long orderId,Integer payType,Integer status);
	
	/**
	 * 获得订单的退款数量
	 * @param orderId
	 * @return
	 */
	Map getOrderRefundNumber(Long orderId);
}
