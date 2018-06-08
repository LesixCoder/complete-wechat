package com.lunchtasting.server.biz.orders;

import java.util.Map;


public interface OrdersRefundBIZ {
	
	/**
	 * 获得退款详情
	 * @param refundId
	 * @return
	 */
	Map getOrderRefundDetail(Long refundId);
}
