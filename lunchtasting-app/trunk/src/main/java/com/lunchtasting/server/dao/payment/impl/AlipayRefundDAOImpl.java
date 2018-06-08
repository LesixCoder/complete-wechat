package com.lunchtasting.server.dao.payment.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.payment.AlipayRefundDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.payment.AlipayRefund;

@Repository
public class AlipayRefundDAOImpl extends GenericDAOSupport<Long, AlipayRefund> implements AlipayRefundDAO {

	@Override
	protected Class<?> getObjectClass() {
		return AlipayRefund.class;
	}

	@Override
	protected String getTableName() {
		return "alipay_refund";
	}

	@Override
	public void createAlipayRefund(Map map) {
		if(map == null){
			return;
		}
		getSqlMapClientTemplate().insert(getNamespace() + ".createAlipayRefund", map);
		
	}

}
