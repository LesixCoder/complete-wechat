package com.lunchtasting.server.dao.user;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.TemporaryUserWeChat;

public interface TemporaryUserWeChatDao extends GenericDAO<TemporaryUserWeChat>{
	Long addTemp(TemporaryUserWeChat temporaryUserWeChat);
	Long updateTemp(TemporaryUserWeChat temporaryUserWeChat);
	TemporaryUserWeChat getByOpenId(String openId);
	TemporaryUserWeChat getByUserId(Long userId);
	/**
	 * 更新订单号
	 * @param out_trade_no
	 * @param userId
	 * @return
	 */
	boolean updateOutTradeNoById(String out_trade_no,Long userId);
}
