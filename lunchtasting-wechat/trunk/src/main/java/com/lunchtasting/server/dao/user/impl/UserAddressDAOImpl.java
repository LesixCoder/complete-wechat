package com.lunchtasting.server.dao.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.user.UserAddressDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.po.lt.UserAddress;

@Repository
public class UserAddressDAOImpl extends GenericDAOSupport<Long, UserAddress> implements UserAddressDAO {

	@Override
	protected Class<?> getObjectClass() {
		return UserAddress.class;
	}

	@Override
	protected String getTableName() {
		return "user_address";
	}

	@Override
	public List queryUserAddressList(Long userId, Integer page, Integer pageSize) {
		if(userId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserAddressList", map);	
	}

	@Override
	public void clearFrequently(Long userId) {
		if(userId == null){
			return;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		getSqlMapClientTemplate().update(getNamespace() + ".clearFrequently", map);	
	}

	@Override
	public Map getFrequentlyAddress(Long userId) {
		if(userId == null){
			return null;
			
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getFrequentlyAddress", map);		
	}

	@Override
	public Map getByAddressId(Long addressId) {
		if(addressId == null){
			return null;
			
		}
		Map map = new HashMap();
		map.put("addressId", addressId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getByAddressId", map);			
	}

	@Override
	public Integer updateFrequently(Long addressId, Long userId) {
		if(addressId == null || userId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("addressId", addressId);
		map.put("userId",userId);
		return getSqlMapClientTemplate().update(getNamespace() + ".updateFrequently", map);			
	}

	@Override
	public Integer removeAddress(Long addressId, Long userId) {
		if(addressId == null || userId == null){
			return 0;
		}
		
		Map map = new HashMap();
		map.put("addressId", addressId);
		map.put("userId",userId);
		return getSqlMapClientTemplate().delete(getNamespace() + ".removeAddress", map);				}

	@Override
	public List queryProvinceList() {
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryProvinceList");			
	}

	@Override
	public List queryCityList(String provinceCode) {
		Map map = new HashMap();
		map.put("provinceCode", provinceCode);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCityList",map);			
	}

	@Override
	public List queryTownList(String cityCode) {
		Map map = new HashMap();
		map.put("cityCode", cityCode);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryTownList",map);		
	}

	@Override
	public List queryAllCityList() {
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryAllCityList");		
	}

}
