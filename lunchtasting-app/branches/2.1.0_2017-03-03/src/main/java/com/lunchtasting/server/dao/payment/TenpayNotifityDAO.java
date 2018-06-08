package com.lunchtasting.server.dao.payment;

import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.payment.TenpayNotifity;

public interface TenpayNotifityDAO extends GenericDAO<TenpayNotifity> {
	
	/**
	 * 查询支付记录是否存在过
	 * @param bizType
	 * @param outTradeNo
	 * @return
	 */
	Integer getNumber(Integer bizType,String outTradeNo);
	
	/**
	 * 创建财付通支付记录
	 * @param map
	 * @return
	 */
	void createTenpayNotifity(Map map);
	
	/**
	 * 查询支付通知
	 * @param bizType
	 * @param outTradeNo
	 * @return
	 */
	Map getTenpayNotifity(Integer bizType,String outTradeNo);
}
