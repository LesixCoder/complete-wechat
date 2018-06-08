package com.perfit.server.dao.seller.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Seller;
import com.perfit.server.dao.seller.SellerDAO;
@Repository
public class SellerDAOImpl extends GenericDAOSupport<Long,Seller> implements SellerDAO {
	@Override
	protected Class<?> getObjectClass() {
		return Seller.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "seller";
	}
	@Override
	public Seller sellerUsersLogin(String account,String password) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("account",account );
		map.put("password",password);
		return (Seller) getSqlMapClientTemplate().queryForObject(getNamespace() + ".sellerUsersLogin",map);
	}

	@Override
	public Seller sellerById(Long id) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("id",id );
		return (Seller) getSqlMapClientTemplate().queryForObject(getNamespace() + ".sellerById",map);
	}

	@Override
	public Long updateSeller(Seller seller) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".updateSeller", seller);
	}
}
 