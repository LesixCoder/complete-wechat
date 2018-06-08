package com.perfit.server.dao.orders;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Orders;
import com.lunchtasting.server.po.lt.OrdersList;
import com.lunchtasting.server.vo.OrdersListExhibition;

public interface OrdersListDAO extends  GenericDAO<OrdersList>{
	/**
	 * 查询条件订单
	 * @param map
	 * @return
	 */
	List<OrdersList> queryOrdersListList(HashMap map);
	/**
	 * 更改状态
	 * @param map
	 * @return
	 */
	int updateState(HashMap map);
	/**
	 * 得到展示数据
	 * @param map
	 * @return
	 */
	OrdersListExhibition queryOrdersListOne(HashMap map);
	/**
	 * 查询对应id 或者 code的子订单
	 * @param map
	 * @return
	 */
	List<OrdersList> queryOrdersListListByCode(HashMap map);
}
