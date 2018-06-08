package com.lunchtasting.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.AdminSellerDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Article;
import com.lunchtasting.server.po.lt.Seller;
@Repository
public class AdminSellerDaoImpl extends GenericDAOSupport<Long,Seller> implements AdminSellerDao{

	@Override
	public List<Seller> querySellerList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".querySellerList",map);
	}

	@Override
	public int querySellerListCount(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getSellerCount",map);
	}

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return Seller.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "seller";
	}

	@Override
	public Long addSeller(Seller seller) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create",seller);
	}

	@Override
	public Long updateSeller(Seller seller) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".update", seller);
	}

	@Override
	public Seller querySellerById(String id) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("id", id);
		return (Seller) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getById",map);
	}

	@Override
	public Long deleteSeller(HashMap map) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".deleteSeller", map);
	}

	@Override
	public Long selectSellerExist(String account) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("account", account);
		return (Long) getSqlMapClientTemplate().queryForObject(getNamespace() + ".querySellerExist", map);
	}

	@Override
	public List<Seller> querySellerNotLimit(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".querySellerNotLimit", map);
	}

	@Override
	public Seller querySellerByName(String name) {
		HashMap map = new HashMap();
		map.put("name", name);
		return (Seller) getSqlMapClientTemplate().queryForObject(getNamespace() + ".querySellerByName",map);
	}

	@Override
	public Seller getSellerNextSettDate(String id) {
		HashMap map = new HashMap();
		map.put("id", id);
		return (Seller) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getSellerNextSettDate",map);
	}

}
