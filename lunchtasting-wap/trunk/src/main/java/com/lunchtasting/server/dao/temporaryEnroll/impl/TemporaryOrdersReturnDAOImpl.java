package com.lunchtasting.server.dao.temporaryEnroll.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.temporaryEnroll.TemporaryOrdersReturnDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.temporaryEnroll.TemporaryOrdersReturn;

@Repository
public class TemporaryOrdersReturnDAOImpl extends GenericDAOSupport<Long, TemporaryOrdersReturn> implements TemporaryOrdersReturnDAO{
	
	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return TemporaryOrdersReturn.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "temporary_orders_return";
	}

	@Override
	public TemporaryOrdersReturn getOrders(HashMap map) {
		// TODO Auto-generated method stub
		return (TemporaryOrdersReturn) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOrders", map);
	}

	@Override
	public Integer checkButton(int totalFee) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("totalFee",totalFee);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".checkButton", map);
	}

}
