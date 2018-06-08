package com.perfit.server.biz.orders.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.po.lt.Orders;
import com.lunchtasting.server.po.lt.OrdersList;
import com.lunchtasting.server.vo.OrdersListExhibition;
import com.perfit.server.biz.orders.OrdersListBIZ;
import com.perfit.server.dao.orders.OrdersDAO;
import com.perfit.server.dao.orders.OrdersListDAO;

@Service
public class OrdersListBIZImpl implements OrdersListBIZ{
	@Autowired
	private OrdersListDAO ordersListDAO;
	@Autowired
	private OrdersDAO ordersDAO;
	@Override
	public List<OrdersList> queryOrdersListList(HashMap map) {
		// TODO Auto-generated method stub
		return ordersListDAO.queryOrdersListList(map);
	}
	@Override
	public boolean employCode(String code, Long id) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		if(id!=null && !id.equals("")){
			map.put("id", id);
		}
		if(code!=null && !code.equals("")){
			map.put("code", code);
		}
		map.put("status", 2);
		int pf = 0;
		pf = ordersListDAO.updateState(map);
		if(pf!=1){
			return false;
		}
		List<OrdersList> list =  ordersListDAO.queryOrdersListListByCode(map);
		int k = 0;
		for (OrdersList ordersList : list) {
			if(ordersList.getStatus()==2){
				k++;
			}
		}	
		//修改主订单
		if(k==list.size()){
			Orders orders = new Orders();
			orders.setId(list.get(0).getOrderId());
			int pf2 = ordersDAO.updateOrdersState(orders);
		}
		return true;
	}
	@Override
	public OrdersListExhibition queryOrdersListByCode(String code, Long sellerId) {
		HashMap map = new HashMap();
		OrdersListExhibition ordersList = null;
		if(code!=null && !code.equals("")&& sellerId!=null && sellerId!=0){
			map.put("code", code);
			map.put("sellerId", sellerId);
			ordersList = ordersListDAO.queryOrdersListOne(map);
		}
		return ordersList;
	}
	
	@Override
	public OrdersListExhibition queryOrdersListById(Long id, Long sellerId) {
		HashMap map = new HashMap();
		OrdersListExhibition ordersList = null;
		if(id!=null && id!=0 && sellerId!=null && sellerId!=0){
			map.put("id", id);
			map.put("sellerId", sellerId);
			ordersList = ordersListDAO.queryOrdersListOne(map);
		}
		return ordersList;
	}
	@Override
	public OrdersListExhibition queryOrdersListOne(HashMap map) {
		// TODO Auto-generated method stub
		return ordersListDAO.queryOrdersListOne(map);
	}
	@Override
	public boolean verifyOrdersList(String code, Long sellerId,Long id) {
		if(code!=null){
			OrdersListExhibition ordersListExhibition = queryOrdersListByCode(code, sellerId);
			if(ordersListExhibition!=null && ordersListExhibition.getId()!=0){
				return true;
			}
		}
		if(id!=null){
			OrdersListExhibition ordersListExhibition = queryOrdersListById(id, sellerId);
			if(ordersListExhibition!=null && ordersListExhibition.getId()!=0){
				return true;
			}
		}
		return false;
	}
}
