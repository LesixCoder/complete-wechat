package com.lunchtasting.server.dao.payment;

import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.payment.TenpayRefund;

public interface TenpayRefundDAO extends GenericDAO<TenpayRefund> {

	/**
	 * 创建微信退款记录
	 * @param map
	 */
	void createTenpayRefund(Map map);
}
