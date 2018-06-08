package com.lunchtasting.server.dao.payment.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.payment.AlipayNotifityDAO;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.payment.AlipayNotifity;

@Repository
public class AlipayNotifityDAOImpl extends GenericDAOSupport<Long, AlipayNotifity> implements AlipayNotifityDAO {

	@Override
	protected Class<?> getObjectClass() {
		return AlipayNotifity.class;
	}

	@Override
	protected String getTableName() {
		return "alipay_notifity";
	}

	@Override
	public Integer getNumber(Integer bizType,String tradeStatus, String outTradeNo) {
		if(bizType == null || tradeStatus == null || outTradeNo == null){
			return 0;
		}
		
		Map map = new HashMap();
		map.put("bizType", bizType);
		map.put("tradeStatus", tradeStatus);
		map.put("outTradeNo", outTradeNo);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getNumber", map);
	}

	@Override
	public void createAlipayNotifity(Map map) {
		if(map == null){
			return;
		}
		getSqlMapClientTemplate().insert(getNamespace() + ".createAlipayNotifity", map);	
		
	}

	@Override
	public Map getAlipayNotifity(Integer bizType, String outTradeNo, String tradeStatus) {
		if(bizType == null || outTradeNo == null || tradeStatus == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("bizType", bizType);
		map.put("tradeStatus", tradeStatus);
		map.put("outTradeNo", outTradeNo);
		return (Map) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getAlipayNotifity", map);
	}

}
