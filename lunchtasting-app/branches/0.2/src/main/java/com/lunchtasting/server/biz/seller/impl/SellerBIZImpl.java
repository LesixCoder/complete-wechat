package com.lunchtasting.server.biz.seller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.seller.SellerBIZ;
import com.lunchtasting.server.dao.seller.SellerDAO;
import com.lunchtasting.server.po.lt.Seller;

@Service
public class SellerBIZImpl implements SellerBIZ {

	@Autowired
	private SellerDAO sellerDAO;

	@Override
	public Seller getByAccountAndPwd(String account, String pwd) {
		return null;
	}
}
