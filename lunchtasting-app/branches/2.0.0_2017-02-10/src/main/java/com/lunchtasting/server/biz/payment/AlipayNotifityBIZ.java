package com.lunchtasting.server.biz.payment;

import java.util.Map;

import com.lunchtasting.server.po.lt.payment.AlipayNotifity;

public interface AlipayNotifityBIZ {
	
	/**
	 * 查询支付记录是否存在过
	 * @param bizType
	 * @param tradeStatus
	 * @param outTradeNo
	 * @return
	 */
	Boolean checkNotifity(Integer bizType,String tradeStatus,String outTradeNo);
	
	/**
	 * 创建一条支付宝支付信息
	 * @param map
	 * @return
	 */
	void createAlipayNotifity(Map map);
	
	/**
	 * 查询订单是否有某条状态的支付宝记录
	 * @param bizType
	 * @param outTradeNo
	 * @param tradeStatus
	 * @return
	 */
	Map getAlipayNotifity(Integer bizType,String outTradeNo,String tradeStatus);
}
