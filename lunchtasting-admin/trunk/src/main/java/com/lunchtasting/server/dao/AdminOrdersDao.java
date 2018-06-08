package com.lunchtasting.server.dao;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Orders;

public interface AdminOrdersDao extends GenericDAO<Orders>{
       List queryOrdersList(HashMap map);
       int getOrdersCount(HashMap map);
       List queryOrdersListForSettl(HashMap map);
}
