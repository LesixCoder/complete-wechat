package com.lunchtasting.server.dao.payment.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.payment.TenpayRefundDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.payment.TenpayRefund;

@Repository
public class TenpayRefundDAOImpl extends GenericDAOSupport<Long, TenpayRefund> implements TenpayRefundDAO {

	@Override
	protected Class<?> getObjectClass() {
		return TenpayRefund.class;
	}

	@Override
	protected String getTableName() {
		return "tenpay_refund";
	}
	
	@Override
	public void createTenpayRefund(Map map) {
		if(map == null){
			return;
		}
		getSqlMapClientTemplate().insert(getNamespace() + ".createTenpayRefund", map);
	}

}
