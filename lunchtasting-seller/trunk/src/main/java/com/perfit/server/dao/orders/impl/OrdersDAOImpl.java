package com.perfit.server.dao.orders.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Orders;
import com.perfit.server.dao.orders.OrdersDAO;
@Repository
public class OrdersDAOImpl extends GenericDAOSupport<Long,Orders> implements OrdersDAO{

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
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryOrdersList",map);
	}

	@Override
	public int getOrdersCount(HashMap map) {
		// TODO Auto-generated method stub
		return (int) getSqlMapClientTemplate().queryForObject(getNamespace()+".getOrdersCount",map);
	}
	@Override
	public int updateOrdersState(Orders orders){
		return (int) getSqlMapClientTemplate().update(getNamespace()+".updateOrdersState",orders);
	}
}
