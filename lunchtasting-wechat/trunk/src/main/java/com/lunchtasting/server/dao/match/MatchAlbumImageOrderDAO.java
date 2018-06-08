package com.lunchtasting.server.dao.match;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.MatchAlbumImageOrder;

public interface MatchAlbumImageOrderDAO extends GenericDAO<MatchAlbumImageOrder> {

	/**
	 * 修改订单已支付
	 * @param orderId
	 * @return
	 */
	Integer updateOrderPay(Long orderId);
}
