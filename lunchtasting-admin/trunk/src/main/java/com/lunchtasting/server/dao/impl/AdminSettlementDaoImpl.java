package com.lunchtasting.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.lunchtasting.server.dao.AdminSettlementDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.OrdersSettlement;
@Repository
public class AdminSettlementDaoImpl extends GenericDAOSupport<Long,OrdersSettlement> implements AdminSettlementDao{

	@Override
	public List querySettlementList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryOrdersSettlementList",map);
	}

	@Override
	public int getSettlementCount(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOrdersSettlementCount",map);
	}

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
	public Long addSettlement(OrdersSettlement ordersSettlement) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create",ordersSettlement);
	}

	@Override
	public int getById(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getById",map);
	}

	@Override
	public Long saveRemark(HashMap map) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".saveRemark",map);
	}

	@Override
	public int getYearWeek(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getYearWeek",map);
	}

	@Override
	public int getSellCrea(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getSellCrea",map);
	}

	@Override
	public Long updateSellerSett(String id,String date) {
		HashMap map = new HashMap();
		map.put("sellerId", id);
		map.put("settlementDate", date);
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".updateSellerSett",map);
	}

	@Override
	public List querySettlementNotLimit(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".querySettlementNotLimit",map);
	}

	@Override
	public int updateOrdersList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update(getNamespace() + ".updateOrdersList",map);
	}

}
