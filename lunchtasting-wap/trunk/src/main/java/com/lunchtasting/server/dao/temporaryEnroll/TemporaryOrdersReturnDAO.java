package com.lunchtasting.server.dao.temporaryEnroll;

import java.util.HashMap;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.temporaryEnroll.TemporaryOrdersReturn;

public interface TemporaryOrdersReturnDAO extends GenericDAO<TemporaryOrdersReturn>{
	/**
	 * 得到订单内容
	 * @param map
	 * @return
	 */
	TemporaryOrdersReturn getOrders(HashMap map);
	
	/**
	 * 根据价格查询个数
	 * @param totalFee
	 * @return
	 */
	Integer checkButton(int totalFee);
}
