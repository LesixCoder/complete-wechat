package com.lunchtasting.server.biz.temporaryEnroll;

import java.util.Map;

public interface TemporaryOrdersReturnBIZ {
	/**
	 * 防微信异步多次
	 * @param out_trade_no
	 * @return
	 */
	boolean verifyOrder(String out_trade_no);
	/**
	 * 插入微信返回的订单数据
	 * @param map
	 * @return
	 */
	boolean insertOrder(Map map);
	/**
	 * 根据openid查询订单
	 * @param openid
	 * @return
	 */
	boolean verifyOrderExist(String openid);
	/**
	 * 更改用户的订单
	 * @param openid
	 * @return
	 */
	boolean updateUserOrder(String outTradeNo,Long userId);
	/**
	 * 判断是totalFee的订单否超过con
	 * @param con
	 * @param totalFee
	 * @return
	 */
	boolean checkButton(int con,int totalFee);
	/**
	 * 得到小组总数
	 * @return
	 */
	int getAllCounut();
}
