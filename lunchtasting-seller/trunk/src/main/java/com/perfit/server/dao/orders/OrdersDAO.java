package com.perfit.server.dao.orders;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Orders;

public interface OrdersDAO extends  GenericDAO<Orders>{
	/**
	 * 查询订单列表
	 * @param map
	 * @return
	 */
	List queryOrdersList(HashMap map);	
	/**
	 * 查询订单总个数
	 * @param map
	 * @return
	 */
	int  getOrdersCount(HashMap map);
	/**
	 * 修改主订单
	 * @param orders
	 * @return
	 */
	int updateOrdersState(Orders orders);
}
