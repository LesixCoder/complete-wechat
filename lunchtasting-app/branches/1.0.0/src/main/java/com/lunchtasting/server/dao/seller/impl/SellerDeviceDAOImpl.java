package com.lunchtasting.server.dao.seller.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.seller.SellerDeviceDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.SellerDevice;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class SellerDeviceDAOImpl extends GenericDAOSupport<Long, SellerDevice> implements SellerDeviceDAO {

	@Override
	protected Class<?> getObjectClass() {
		return SellerDevice.class;
	}

	@Override
	protected String getTableName() {
		return "seller_device";
	}

	@Override
	public String getAccessTokenByAuthId(String authId) {
		if(StringUtils.isEmpty(authId)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("authId", authId);
		return (String) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getAccessTokenByAuthId", map);
	}

	@Override
	public Integer updateDevice(SellerDevice device) {
		if(device == null){
			return 0;
		}
		return getSqlMapClientTemplate().update(getNamespace() + ".updateDevice", device);
	}

	@Override
	public SellerDevice getBySellerId(Long sellerId) {
		if(ValidatorHelper.isEmpty(sellerId)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("sellerId", sellerId);
		return (SellerDevice) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getBySellerId", map);
	}

	@Override
	public Long getDeviceId(Long sellerId) {
		if(ValidatorHelper.isEmpty(sellerId)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("sellerId", sellerId);
		return (Long) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getDeviceId", map);	
	}

}
