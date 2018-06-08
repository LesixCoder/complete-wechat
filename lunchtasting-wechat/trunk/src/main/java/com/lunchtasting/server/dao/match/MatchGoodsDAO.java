package com.lunchtasting.server.dao.match;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.MatchGoods;

public interface MatchGoodsDAO extends GenericDAO<MatchGoods> {

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
	List queryUserSelectGoodsList(Long ticketId,String goodsStr);
	
	/**
	 * 获得用户选中商品的价格
	 * @param goodsStr
	 * @return
	 */
	Double getUserSelectGoodsPrice(String goodsStr);
}
