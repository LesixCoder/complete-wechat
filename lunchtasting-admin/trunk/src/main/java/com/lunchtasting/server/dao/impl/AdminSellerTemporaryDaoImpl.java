package com.lunchtasting.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.AdminSellerTemporaryDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.SellerTemporary;
@Repository
public class AdminSellerTemporaryDaoImpl extends GenericDAOSupport<Long,SellerTemporary> implements AdminSellerTemporaryDao{

	@Override
	public Long addSellerTemporary(SellerTemporary sellerTemporary) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create",sellerTemporary);
	}

	@Override
	public List querySellerTemporary() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".querySellerTemporary");
	}

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return SellerTemporary.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "seller_temporary";
	}

}
