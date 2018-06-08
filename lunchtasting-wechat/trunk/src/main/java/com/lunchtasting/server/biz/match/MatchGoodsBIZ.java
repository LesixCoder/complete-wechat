package com.lunchtasting.server.biz.match;

import java.util.List;
import java.util.Map;

public interface MatchGoodsBIZ {
	
	/**
	 * 查询某个票务下面的套餐商品列表
	 * @param ticketId
	 * @return
	 */
	List queryMatchTicketGoodsList(Long ticketId);
	
	/**
	 * 查询用户选择的商品列表
	 * @param ticketId
	 * @param goodsStr
	 * @return
	 */
	Map getUserSelectGoods(Long ticketId,String goodsStr);
	
	/**
	 * 获得用户选中商品的价格
	 * @param ticketId
	 * @param goodsStr
	 * @return
	 */
	Double getUserSelectGoodsPrice(Long ticketId,String goodsStr);
}
