package com.perfit.server.dao.orders.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.OrdersSettlement;
import com.perfit.server.dao.orders.OrdersSettlementDAO;
@Repository
public class OrdersSettlementDAOImpl extends GenericDAOSupport<Long,OrdersSettlement> implements OrdersSettlementDAO {

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return OrdersSettlement.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "orders_settlement";
	}
	@Override
	public List getOrdersSettlementList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryOrdersSettlementList",map);
	}

	@Override
	public int getOrdersSettlementCount(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOrdersSettlementCount",map);
	}

}
