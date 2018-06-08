package com.lunchtasting.server.dao.orders.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.orders.OrdersRefundDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.OrdersRefund;

@Repository
public class OrdersRefundDAOImpl extends GenericDAOSupport<Long, OrdersRefund> implements OrdersRefundDAO {

	@Override
	protected Class<?> getObjectClass() {
		return OrdersRefund.class;
	}

	@Override
	protected String getTableName() {
		return "orders_refund";
	}

	@Override
	public Map getOrderRefundDetail(Long refundId) {
		if(refundId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("refundId", refundId);
		return (Map) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOrderRefundDetail", map);
	}

	@Override
	public List queryAuditOrderRefundList() {
		return (List) getSqlMapClientTemplate().queryForList(getNamespace() + ".queryAuditOrderRefundList");
	}

	@Override
	public Integer updateRefundStatus(Long refundId, Integer status) {
		if(refundId == null || status == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("refundId", refundId);
		map.put("status", status);
		return  getSqlMapClientTemplate().update(getNamespace() + ".updateRefundStatus", map);
	}

	@Override
	public Integer getOrderRefundCount(Long userId) {
		if(userId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOrderRefundCount", map);
	}

	@Override
	public List queryOrderRefundList(Long userId, Integer page,
			Integer pageSize) {
		if(userId == null){
			return null;
		}
		
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryOrderRefundList", map);
	}


}
