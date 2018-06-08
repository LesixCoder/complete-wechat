package com.perfit.server.biz.orders.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfit.server.biz.orders.OrdersSettlementBIZ;
import com.perfit.server.dao.orders.OrdersSettlementDAO;
@Service
public class OrdersSettlementBIZImpl implements OrdersSettlementBIZ {
	@Autowired
	private OrdersSettlementDAO ordersSettlementDAO;
	@Override
	public List getOrdersSettlementList(HashMap map) {
		// TODO Auto-generated method stub
		return ordersSettlementDAO.getOrdersSettlementList(map);
	}

	@Override
	public int getOrdersSettlementCount(HashMap map) {
		// TODO Auto-generated method stub
		return ordersSettlementDAO.getOrdersSettlementCount(map);
	}

}
