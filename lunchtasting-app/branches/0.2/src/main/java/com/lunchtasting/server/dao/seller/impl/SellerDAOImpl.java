package com.lunchtasting.server.dao.seller.impl;

import java.util.HashMap;
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

}
