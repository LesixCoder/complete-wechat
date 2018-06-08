package com.lunchtasting.server.biz.orders.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.orders.OrdersListBIZ;
import com.lunchtasting.server.dao.orders.OrdersListDAO;

@Service
public class OrdersListBIZImpl implements OrdersListBIZ {

	@Autowired
	private OrdersListDAO ordersListDAO;

	@Override
	public Integer getOrderListCount(Long orderId,Integer status) {
		return ordersListDAO.getOrderListCount(orderId,status);
	}

	@Override
	public List queryOrderListList(Long orderId,Integer status,Integer page,Integer pageSize) {
		return ordersListDAO.queryOrderListList(orderId,status,page,pageSize);
	}
}
