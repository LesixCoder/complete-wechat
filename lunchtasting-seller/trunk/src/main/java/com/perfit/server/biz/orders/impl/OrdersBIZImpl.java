package com.perfit.server.biz.orders.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfit.server.biz.orders.OrdersBIZ;
import com.perfit.server.dao.orders.OrdersDAO;
import com.perfit.server.dao.orders.OrdersListDAO;

@Service
public class OrdersBIZImpl implements OrdersBIZ{
	@Autowired
	private OrdersDAO ordersDAO;
	@Autowired
	private OrdersListDAO ordersListDAO;
	@Override
	public List queryOrdersList(HashMap map) {
		// TODO Auto-generated method stub
		return ordersDAO.queryOrdersList(map);
	}
	@Override
	public Integer getOrdersCount(HashMap map) {
		// TODO Auto-generated method stub
		return ordersDAO.getOrdersCount(map);
	}
	@Override
	public List queryOrdersListToSon(String code, Long id) {
		HashMap map = new HashMap();
		if(code!=null){
			map.put("code",code);
		}
		if(id!=null){
			map.put("ordersListId",id);
		}
		List ordersLsit = ordersDAO.queryOrdersList(map);
		for (Object object : ordersLsit) {
			HashMap mapObject = (HashMap)object;
			HashMap maplist = new HashMap();
			maplist.put("orderId", mapObject.get("id"));
			mapObject.put("orders_list", ordersListDAO.queryOrdersListList(maplist));
		}
		return ordersLsit;
	}
}
