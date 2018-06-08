package com.lunchtasting.server.dao.seller.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.seller.SellerDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Seller;

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
	public Map getShareDetail(Long sellerId) {
		if(sellerId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("sellerId", sellerId);
        return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getShareDetail", map);	}

}
