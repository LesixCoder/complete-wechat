package com.lunchtasting.server.dao.orders.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.orders.OrdersListDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.OrdersList;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class OrdersListDAOImpl extends GenericDAOSupport<Long, OrdersList> implements OrdersListDAO {

	@Override
	protected Class<?> getObjectClass() {
		return OrdersList.class;
	}

	@Override
	protected String getTableName() {
		return "orders_list";
	}

	@Override
	public Integer getOrderListCount(Long orderId,Integer status) {
		if(orderId == null){
			return 0;
		}
		
		Map map = new HashMap();
		map.put("orderId", orderId);
		map.put("status", status);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOrderListCount", map);
	}

	@Override
	public List queryOrderListList(Long orderId,Integer status,Integer page,Integer pageSize) {
		if(orderId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("orderId", orderId);
		map.put("status", status);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryOrderListList", map);
	}

	@Override
	public Integer updateApplyRefund(String ids, Long refundId,Integer status) {
		if(ValidatorHelper.isEmpty(ids) || refundId == null || status == null){
			return null;
		}
		Map map = new HashMap();
		map.put("ids", ids);
		map.put("refundId", refundId);
		map.put("status", status);
		return getSqlMapClientTemplate().update(getNamespace() + ".updateApplyRefund", map);
	}

	@Override
	public Integer updateIsRefund(String ids, Integer status) {
		if(ValidatorHelper.isEmpty(ids)  || status == null){
			return null;
		}
		Map map = new HashMap();
		map.put("ids", ids);
		map.put("status", status);
		return getSqlMapClientTemplate().update(getNamespace() + ".updateIsRefund", map);
	}

}
