package com.lunchtasting.server.dao.payment.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.payment.AlipayNotifityDAO;
import com.lunchtasting.server.dao.payment.TenpayNotifityDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.payment.TenpayNotifity;

@Repository
public class TenpayNotifityDAOImpl extends GenericDAOSupport<Long, TenpayNotifity> implements TenpayNotifityDAO {

	@Override
	protected Class<?> getObjectClass() {
		return TenpayNotifity.class;
	}

	@Override
	protected String getTableName() {
		return "tenpay_notifity";
	}
	
	@Override
	public Integer getNumber(Integer bizType, String outTradeNo) {
		if(bizType == null || outTradeNo == null){
			return 0;
		}
		
		Map map = new HashMap();
		map.put("bizType", bizType);
		map.put("outTradeNo", outTradeNo);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getNumber", map);
	}

	@Override
	public void createTenpayNotifity(Map map) {
		if(map == null){
			return;
		}
		getSqlMapClientTemplate().insert(getNamespace() + ".createTenpayNotifity", map);	
	}

	@Override
	public Map getTenpayNotifity(Integer bizType, String outTradeNo) {
		if(bizType == null || outTradeNo == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("bizType", bizType);
		map.put("outTradeNo", outTradeNo);
		return (Map) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getTenpayNotifity", map);
	}

}
