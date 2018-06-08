package com.lunchtasting.server.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.AdminOrdersListDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.OrdersList;
@Repository
public class AdminOrdersListDaoImpl extends GenericDAOSupport<Long,OrdersList> implements AdminOrdersListDao{

	@Override
	public OrdersList getOrdersListById(Long orderId) {
		HashMap map = new HashMap();
		map.put("orderId", orderId);
		return (OrdersList) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOrdersListById", map);
	}

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

}
