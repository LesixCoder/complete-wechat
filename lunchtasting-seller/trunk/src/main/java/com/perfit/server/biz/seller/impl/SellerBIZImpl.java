package com.perfit.server.biz.seller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.po.lt.Seller;
import com.perfit.server.biz.seller.SellerBIZ;
import com.perfit.server.dao.seller.SellerDAO;
@Service
public class SellerBIZImpl implements SellerBIZ{
	@Autowired
	private SellerDAO sellerDAO;
	
	@Override
	public Seller sellerUsersLogin(String account,String passwrod){
		Seller seller = sellerDAO.sellerUsersLogin(account, passwrod);
		if(seller==null || seller.getId()==0){
			return null;
		}
		return seller;
	}

	@Override
	public Seller sellerById(Long id) {
		// TODO Auto-generated method stub
		Seller seller = sellerDAO.sellerById(id);
		if(seller==null || seller.getId()==0){
			return null;
		}
		return seller;
	}

	@Override
	public boolean updateSeller(Seller seller) {
		boolean tff= false;
		Long tf = sellerDAO.updateSeller(seller);
		if(tf==1){
			return true;
		}
		return tff;
	}
}
