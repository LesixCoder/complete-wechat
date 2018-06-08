package com.lunchtasting.server.biz.orders;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.po.lt.Orders;

public interface OrdersBIZ {
	
	/**
	 * 创建订单
	 * @param userId
	 * @param courseId
	 * @param sellerId
	 * @param number
	 * @param price
	 * @param payType
	 * @return
	 */
	Orders createOrder(Long userId,Long courseId,Long sellerId,Integer number
					,Double price);
	
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
	 * 同步查询第三方完成支付清单
	 * @param orderId
	 * @param userId
	 * @param price
	 * @param number
	 * @return
	 */
	Boolean synchroCompletePayOrder(Long orderId,Long userId,Double price,Integer number) throws Exception;
	
	/**
	 * 第三方异步通知完成支付订单
	 * @param outTradeNo
	 * @param payType
	 * @param payMap
	 * @return
	 * @throws Exception
	 */
	Boolean notifityCompletePayOrder(String outTradeNo,Integer payType,Map payMap) throws Exception;
	
	/**
	 * 判断第三方(支付宝/微信)通知的订单是否存在
	 * @param code
	 * @param payPrice
	 * @return
	 */
	Boolean checkPayOrderIsExist(String code,Double payPrice);
	
	/**
	 * 获得用户退款订单
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
	 * 订单退款
	 * @param orderId
	 * @param number
	 * @param userId
	 * @param payType
	 * @param outTradeNo
	 * @param payPrice
	 * @param refundPrice
	 * @param isOrder
	 * @return
	 * @throws Exception
	 */
	Map refundOrder(Long orderId,Integer number,Long userId,Integer payType,String outTradeNo,Double payPrice,Double refundPrice,Boolean isOrder) throws Exception;
	
	/**
	 * 获得订单
	 * @param orderId
	 * @param userId
	 * @return
	 */
	Map getByOrderIdAndUserId(Long orderId,Long userId);
}
