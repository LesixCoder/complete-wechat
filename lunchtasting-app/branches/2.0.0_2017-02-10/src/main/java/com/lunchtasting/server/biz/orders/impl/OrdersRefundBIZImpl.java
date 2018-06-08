package com.lunchtasting.server.biz.orders.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.orders.OrdersRefundBIZ;
import com.lunchtasting.server.dao.orders.OrdersRefundDAO;

@Service
public class OrdersRefundBIZImpl implements OrdersRefundBIZ {
	
	@Autowired
	private OrdersRefundDAO refundDAO;
	
	@Override
	public Map getOrderRefundDetail(Long refundId) {
		Map map = refundDAO.getOrderRefundDetail(refundId);
		if(map != null){
			map.put("kf_phone", "17701085590");
		}
		return map;
	}

}
