package com.lunchtasting.server.biz.order;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.ReceiverAddress;


public interface OrdersBIZ {
	/**
	 * 查询订单列表
	 * @param map
	 * @return
	 */
	List queryOrdersList(HashMap map);
	
	/**
	 * 提交订单
	 * @param userId
	 * @param orderLineList
	 * @param receiverAddress
	 * @param deliveredTime
	 * @param couponId
	 * @return
	 */
	HashMap submitOrder(int userId,String orderLineList,ReceiverAddress receiverAddress,
			String deliveredTime,String couponId);
	
	
}
