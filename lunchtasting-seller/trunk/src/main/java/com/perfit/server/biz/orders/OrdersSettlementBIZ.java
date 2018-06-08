package com.perfit.server.biz.orders;

import java.util.HashMap;
import java.util.List;

public interface OrdersSettlementBIZ {
	/**
	 * 查询结算列表
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
