package com.lunchtasting.server.dao.goods;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.goods.Goods;

public interface GoodsDAO extends GenericDAO<Goods> {
	
	/**
	 * 查询商品列表
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryGoodsList(Integer page,Integer pageSize);
	
	/**
	 * 查询商品总数
	 * @return
	 */
	Integer getGoodsCount();
	
	/**
	 * 获得商品详情
	 * @param goodsId
	 * @return
	 */
	Map getGoodsDetail(Long goodsId);
	
	/**
	 * 查询商品sku属性列表
	 * @param goodsId
	 * @return
	 */
	List queryGoodsSkuPropertyList(Long goodsId);
	
	/**
	 * 获得商品sku属性类别字符串
	 * @param goodsId
	 * @return
	 */
	String getGoodsSkuPropertStr(Long goodsId);
	
	/**
	 * 获得商品所有的sku商品列表
	 * @param goodsId
	 * @return
	 */
	List queryGoodsSkuList(Long goodsId);
	
	/**
	 * 获得需要支付的商品详情
	 * @param goodsSkuId
	 * @return
	 */
	Map getGoodsPayMap(Long goodsSkuId);
	
	/**
	 * 查询商品规格属性列表
	 * @param goodsId
	 * @return
	 */
	List queryGoodsPropertyList(Long goodsId);
	
	/**
	 * 通过选择属性查询sku关联属性列表
	 * @param optionIds
	 * @return
	 */
	List getGoodsSkuByOptionIds(String optionIds);
}
