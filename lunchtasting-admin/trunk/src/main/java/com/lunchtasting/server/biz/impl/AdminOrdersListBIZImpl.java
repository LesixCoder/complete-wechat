package com.lunchtasting.server.biz.impl;

import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.AdminOrdersListBIZ;
import com.lunchtasting.server.dao.AdminOrdersListDao;
import com.lunchtasting.server.po.lt.OrdersList;
@Service
public class AdminOrdersListBIZImpl implements AdminOrdersListBIZ{
	private AdminOrdersListDao adminOrdersListDao;
	@Override
	public OrdersList getOrdersListById(Long orderId) {
		// TODO Auto-generated method stub
		return adminOrdersListDao.getOrdersListById(orderId);
	}

}
