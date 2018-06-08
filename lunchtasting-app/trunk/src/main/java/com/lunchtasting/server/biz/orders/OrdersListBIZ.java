package com.lunchtasting.server.biz.orders;

import java.util.List;

public interface OrdersListBIZ {
	
	/**
	 * 查询订单清单总数
	 * @param orderId
	 * @param status
	 * @return
	 */
	Integer getOrderListCount(Long orderId,Integer status);
	
	/**
	 * 查询订单清单列表
	 * @param orderId
	 * @param status
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryOrderListList(Long orderId,Integer status,Integer page,Integer pageSize);
}
