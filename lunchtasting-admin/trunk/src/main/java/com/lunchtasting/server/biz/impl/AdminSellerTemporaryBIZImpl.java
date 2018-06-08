package com.lunchtasting.server.biz.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lunchtasting.server.biz.AdminSellerTemporaryBIZ;
import com.lunchtasting.server.dao.AdminSellerTemporaryDao;
import com.lunchtasting.server.po.lt.SellerTemporary;

@Transactional(rollbackFor = Throwable.class)
@Service
public class AdminSellerTemporaryBIZImpl implements AdminSellerTemporaryBIZ{
	
	@Autowired
	private AdminSellerTemporaryDao adminSellerTemporaryDao;
	
	@Override
	public Long addSellerTemporary(SellerTemporary sellerTemporary) throws Exception{
		// TODO Auto-generated method stub
		return adminSellerTemporaryDao.addSellerTemporary(sellerTemporary);
	}

	@Override
	public List querySellerTemporary() {
		// TODO Auto-generated method stub
		return adminSellerTemporaryDao.querySellerTemporary();
	}

}
