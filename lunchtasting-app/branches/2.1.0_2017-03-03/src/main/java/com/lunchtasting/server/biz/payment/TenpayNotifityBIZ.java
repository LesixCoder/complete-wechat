package com.lunchtasting.server.biz.payment;

import java.util.Map;

public interface TenpayNotifityBIZ {

	/**
	 * 查询支付记录是否存在过
	 * @param bizType
	 * @param outTradeNo
	 * @return
	 */
	Boolean checkNotifity(Integer bizType,String outTradeNo);
	
	/**
	 * 创建一条支付宝支付信息
	 * @param map
	 * @return
	 */
	void createTenpayNotifity(Map map);
	
	/**
	 * 查询订单是否有某条状态的支付宝记录
	 * @param bizType
	 * @param outTradeNo
	 * @return
	 */
	Map getTenpayNotifity(Integer bizType,String outTradeNo);
}
