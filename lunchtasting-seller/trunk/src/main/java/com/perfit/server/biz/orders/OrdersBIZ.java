package com.perfit.server.biz.orders;

import java.util.HashMap;
import java.util.List;

public interface OrdersBIZ {
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
	Integer getOrdersCount(HashMap map);
	/**
	 * 查询订单列表包含子订单
	 * @param map
	 * @return
	 */
	List queryOrdersListToSon(String code,Long id);
}
