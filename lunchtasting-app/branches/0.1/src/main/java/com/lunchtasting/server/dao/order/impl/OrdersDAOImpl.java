package com.lunchtasting.server.dao.order.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.order.OrdersDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Orders;

@Repository
public class OrdersDAOImpl extends GenericDAOSupport<Long, Orders> implements OrdersDAO{

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return Orders.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "orders";
	}

	@Override
	public List queryOrdersList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryOrdersList", map);
	}

	@Override
	public int submitOrder(Orders order) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().insert(getNamespace() + ".submitOrder", order);
	}

	@Override
	public int updateState(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update(getNamespace() + ".updateState", map);
	}

}
