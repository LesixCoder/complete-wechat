package com.perfit.server.dao.orders;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.OrdersSettlement;

public interface OrdersSettlementDAO extends GenericDAO<OrdersSettlement>{
	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	List getOrdersSettlementList(HashMap map);
	/**
	 * 查询总个数
	 * @param map
	 * @return
	 */
	int getOrdersSettlementCount(HashMap map);
}
