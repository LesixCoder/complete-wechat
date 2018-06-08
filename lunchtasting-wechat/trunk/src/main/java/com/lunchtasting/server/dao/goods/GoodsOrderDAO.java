package com.lunchtasting.server.dao.goods;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.goods.GoodsOrder;

public interface GoodsOrderDAO extends GenericDAO<GoodsOrder> {

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
	 * 创建订单清单
	 */
	void createOrderList(Long id,Long orderId,Long goodsSkuId,Long userId
			,Integer number,Double price);
	
	/**
	 * 获得需要支付的订单
	 * @param orderId
	 * @return
	 */
	Map getOrderPayMap(Long orderId);
	
	/**
	 * 修改订单已支付
	 * @param orderId
	 * @return
	 */
	Integer updateOrderPay(Long orderId);
	
	/**
	 * 修改订单已取消
	 * @param orderId
	 * @return
	 */
	Integer updateOrderCancel(Long orderId);
}
