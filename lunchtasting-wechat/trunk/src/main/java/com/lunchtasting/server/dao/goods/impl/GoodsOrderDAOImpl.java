package com.lunchtasting.server.dao.goods.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.goods.GoodsOrderDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.goods.GoodsOrder;

@Repository
public class GoodsOrderDAOImpl extends GenericDAOSupport<Long, GoodsOrder> implements GoodsOrderDAO {

	@Override
	protected Class<?> getObjectClass() {
		return GoodsOrder.class;
	}

	@Override
	protected String getTableName() {
		return "goods_order";
	}

	@Override
	public List queryOrderList(Long userId, Integer status, Integer page,
			Integer pageSize) {
		if(userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("status", status);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return  getSqlMapClientTemplate().queryForList(getNamespace() + ".queryOrderList", map);
	}

	@Override
	public Integer getOrderCount(Long userId, Integer status) {
		if(userId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("status", status);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOrderCount", map);
	}

	@Override
	public Map getOrderDetail(Long orderId,Long userId) {
		if(orderId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("orderId", orderId);
		map.put("userId", userId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOrderDetail", map);
	}

	@Override
	public void createOrderList(Long id, Long orderId, Long goodsSkuId,
			Long userId, Integer number, Double price) {
		if(id == null){
			return;
		}
		Map map = new HashMap();
		map.put("id", id);
		map.put("orderId", orderId);
		map.put("goodsSkuId", goodsSkuId);
		map.put("userId", userId);
		map.put("number", number);
		map.put("price", price);
		getSqlMapClientTemplate().insert(getNamespace() + ".createOrderList", map);
	}

	@Override
	public Map getOrderPayMap(Long orderId) {
		if(orderId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("orderId", orderId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOrderPayMap", map);
	}

	@Override
	public Integer updateOrderPay(Long orderId) {
		if(orderId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("orderId", orderId);
		return (Integer)getSqlMapClientTemplate().update(getNamespace() + ".updateOrderPay", map);
	}

	@Override
	public Integer updateOrderCancel(Long orderId) {
		if(orderId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("orderId", orderId);
		return (Integer)getSqlMapClientTemplate().update(getNamespace() + ".updateOrderCancel", map);	
	}

}
