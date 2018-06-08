package com.lunchtasting.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.lunchtasting.server.dao.AdminOrdersDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Orders;

@Repository
public class AdminOrdersDaoImpl extends GenericDAOSupport<Long,Orders> implements AdminOrdersDao{

	@Override
	public List queryOrdersList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryOrdersList",map);
	}

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
	public int getOrdersCount(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOrdersCount",map);
	}

	@Override
	public List queryOrdersListForSettl(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryOrdersNotLimit",map);
	}

}
