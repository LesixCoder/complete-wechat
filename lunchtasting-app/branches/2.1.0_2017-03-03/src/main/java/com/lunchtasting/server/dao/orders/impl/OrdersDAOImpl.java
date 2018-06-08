package com.lunchtasting.server.dao.orders.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.orders.OrdersDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Orders;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class OrdersDAOImpl extends GenericDAOSupport<Long, Orders> implements OrdersDAO {

	@Override
	protected Class<?> getObjectClass() {
		return Orders.class;
	}

	@Override
	protected String getTableName() {
		return "orders";
	}

	@Override
	public Map getOrderDetail(Long orderId, Long userId) {
		if(orderId == null || userId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("orderId", orderId);
		map.put("userId", userId);
		return (Map) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOrderDetail", map);
	}

	@Override
	public Integer getOrderCount(Long userId, Integer status) {
		if(userId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("status", status);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOrderCount", map);
	}

	@Override
	public List queryOrderList(Long userId, Integer status,Integer page,Integer pageSize) {
		if(userId == null){
			return null;
		}
		
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("status", status);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryOrderList", map);
	}

	@Override
	public Map getNoPayOrder(Long orderId, Long userId) {
		if(orderId == null || userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("orderId", orderId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getNoPayOrder", map);
	}

	@Override
	public Integer updateStatus(Long orderId, Integer status) {
		if(orderId == null || status == null){
			return 0;
		}
		
		Map map = new HashMap();
		map.put("orderId", orderId);
		map.put("status", status);
		return (Integer)getSqlMapClientTemplate().update(getNamespace() + ".updateStatus", map);
	}

	@Override
	public Long getByCodeAndPayPrice(String code, Double payPrice) {
		if(ValidatorHelper.isEmpty(code)  || ValidatorHelper.isEmpty(payPrice)){
			return null;
		}
		Map map = new HashMap();
		map.put("code", code);
		map.put("payPrice", payPrice);
		return (Long)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getByCodeAndPayPrice", map);
	}

	@Override
	public Map getRefundOrder(Long orderId, Long userId) {
		if(orderId == null || userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("orderId", orderId);
		map.put("userId", userId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getRefundOrder", map);
	}

	@Override
	public Map getCommentOrder(Long orderId, Long userId) {
		if(orderId == null || userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("orderId", orderId);
		map.put("userId", userId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getCommentOrder", map);
	}

	@Override
	public Integer updateOrderRefund(Long orderId,Integer status) {
		if(orderId == null || status == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("orderId", orderId);
		map.put("status", status);
		return getSqlMapClientTemplate().update(getNamespace() + ".updateOrderRefund", map);
	}


	@Override
	public Map getOrderByOutTradeNo(String outTradeNo) {
		if(ValidatorHelper.isEmpty(outTradeNo)){
			return null;
		}
		Map map = new HashMap();
		map.put("outTradeNo", outTradeNo);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOrderByOutTradeNo", map);
	}

	@Override
	public Map getByOrderIdAndUserId(Long orderId, Long userId) {
		if(orderId == null || userId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("orderId", orderId);
		map.put("userId", userId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getByOrderIdAndUserId", map);
	}

	@Override
	public Integer updateIsPay(Long orderId, Integer payType, Integer status) {
		if(orderId == null || payType == null || status == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("orderId", orderId);
		map.put("payType", payType);
		map.put("status", status);
		return getSqlMapClientTemplate().update(getNamespace() + ".updateIsPay", map);
	}

	@Override
	public Map getOrderRefundNumber(Long orderId) {
		if(orderId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("orderId", orderId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOrderRefundNumber", map);
	}
	
}
