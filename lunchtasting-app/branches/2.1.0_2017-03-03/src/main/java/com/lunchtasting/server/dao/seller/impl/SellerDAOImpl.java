package com.lunchtasting.server.dao.seller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.seller.SellerDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Seller;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class SellerDAOImpl extends GenericDAOSupport<Long, Seller> implements SellerDAO {

	@Override
	protected Class<?> getObjectClass() {
		return Seller.class;
	}

	@Override
	protected String getTableName() {
		return "seller";
	}

	@Override
	public Seller getByAccountAndPwd(String account, String pwd) {
		if(ValidatorHelper.isEmpty(account) || ValidatorHelper.isEmpty(pwd)){
			return null;
		}
		Map map = new HashMap();
		map.put("account", account);
		map.put("pwd", pwd);
		return (Seller) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getByAccountAndPwd", map);
	}

	@Override
	public Integer getSellerCount(Long areaId) {
		Map map = new HashMap();
		map.put("areaId", areaId);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getSellerCount", map);
	}

	@Override
	public List querySellerList(Long areaId, Double longitude, Double latitude,
			Integer page, Integer pageSize) {
		Map map = new HashMap();
		map.put("areaId", areaId);
		map.put("longitude", longitude);
		map.put("latitude", latitude);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".querySellerList", map);
	}

	@Override
	public Map getSellerDetail(Long sellerId,Long userId) {
		if(ValidatorHelper.isEmpty(sellerId)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("sellerId", sellerId);
		map.put("userId", userId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getSellerDetail", map);	}

	@Override
	public List queryRecommendSellerList(Integer size) {
		if(size == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("size", size);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryRecommendSellerList", map);	
	}

}

