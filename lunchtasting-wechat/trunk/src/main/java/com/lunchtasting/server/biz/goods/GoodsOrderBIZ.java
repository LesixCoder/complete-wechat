package com.lunchtasting.server.biz.goods;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.po.lt.goods.GoodsOrder;

public interface GoodsOrderBIZ {
	
	/**
	 * 订单状态 1待付款  2已取消  3已付款（待发货）  4已发货  5已完成  6已退款
	 */
	
	GoodsOrder getById(Long id);

	/**
	 * 订单列表 
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryOrderList(Long userId,Integer status,Integer page,Integer pageSize);
	
	/**
	 * 订单总数
	 * @param userId
	 * @param status
	 * @return
	 */
	Integer getOrderCount(Long userId,Integer status);
	
	/**
	 * 订单详情
	 * @param orderId
	 * @param userId
	 * @return
	 */
	Map getOrderDetail(Long orderId,Long userId);
	
	/**
	 * 创建订单
	 * @param userId
	 * @param addressId
	 * @param goodsSkuId
	 * @param price
	 * @param code
	 * @param remark
	 * @return
	 * @throws Exception
	 */
	Long createGoodsOrder(Long userId,Long addressId,Long goodsSkuId,Double price,String code,String remark)throws Exception;
	
	/**
	 * 获得需要支付的订单
	 * @param orderId
	 * @return
	 */
	Map getOrderPayMap(Long orderId);
	
	/**
	 * 判断订单是否已经支付
	 * @param orderId
	 * @return
	 */
	Boolean checkOrderPay(Long orderId);
	
	/**
	 * 修改订单已支付
	 * @param orderId
	 */
	Boolean updateOrderPay(Long orderId);
	
	/**
	 * 修改订单已取消
	 * @param orderId
	 * @return
	 */
	Boolean updateOrderCancel(Long orderId);
	
}
