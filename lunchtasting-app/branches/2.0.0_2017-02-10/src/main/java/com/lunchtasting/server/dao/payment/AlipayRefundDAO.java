package com.lunchtasting.server.dao.payment;

import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.payment.AlipayRefund;

public interface AlipayRefundDAO extends GenericDAO<AlipayRefund> {
	
	/**
	 * 创建支付宝退款记录
	 * @param map
	 */
	void createAlipayRefund(Map map);
}
