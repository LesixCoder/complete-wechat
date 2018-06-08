package com.lunchtasting.server.dao.order;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.Orders;

public interface OrdersDAO {
	/**
	 * 得到7天内的订单
	 * @param map
	 * @return
	 */
	List queryOrdersList(HashMap map);
	/**
	 * 提交订单
	 * @param map
	 * @return
	 */
	int submitOrder(Orders orders);
	/**
	 * 修改状态
	 * @param map
	 * @return
	 */
	int updateState(HashMap map);
	
}
