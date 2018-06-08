package com.lunchtasting.server.biz.match;

import com.lunchtasting.server.po.lt.MatchAlbumImageOrder;

public interface MatchAlbumImageOrderBIZ {

	MatchAlbumImageOrder getById(Long id);
	
	/**
	 * 创建图片打赏订单
	 * @param userId
	 * @param imageId
	 * @param price
	 * @param code
	 * @return
	 */
	Long createImageOrder(Long userId,Long imageId,Double price,String code);
	
	/**
	 * 判断订单是否已经支付
	 * @param orderId
	 * @return
	 */
	Boolean checkOrderPay(Long orderId);
	
	
	/**
	 * 修改订单已支付
	 * @param orderId
	 */
	Boolean updateOrderPay(Long orderId);
}
