package com.perfit.server.dao.orders.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.OrdersList;
import com.lunchtasting.server.vo.OrdersListExhibition;
import com.perfit.server.dao.orders.OrdersListDAO;

@Repository
public class OrdersListDAOImpl extends GenericDAOSupport<Long,OrdersList> implements OrdersListDAO{
	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return OrdersList.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "orders_list";
	}
	@Override
	public List<OrdersList> queryOrdersListList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace()+".queryOrdersListList",map);
	}

	@Override
	public int updateState(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer)getSqlMapClientTemplate().update(getNamespace()+".updateState",map);
	}

	@Override
	public OrdersListExhibition queryOrdersListOne(HashMap map) {
		// TODO Auto-generated method stub
		return (OrdersListExhibition) getSqlMapClientTemplate().queryForObject(getNamespace()+".queryOrdersListOne",map);
	}

	@Override
	public List<OrdersList> queryOrdersListListByCode(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace()+".queryOrdersListListByCode",map);
	}
}
