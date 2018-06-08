package com.lunchtasting.server.dao;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.OrdersList;

public interface AdminOrdersListDao extends GenericDAO<OrdersList>{
	OrdersList getOrdersListById(Long orderId);
}
