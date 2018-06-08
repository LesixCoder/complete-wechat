package com.lunchtasting.server.biz.seller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.seller.SellerDeviceBIZ;
import com.lunchtasting.server.dao.seller.SellerDeviceDAO;

@Service
public class SellerDeviceBIZImpl implements SellerDeviceBIZ {
	
	@Autowired
	private SellerDeviceDAO deviceDAO;

	@Override
	public String getAccessTokenByAuthId(String authId) {
		return deviceDAO.getAccessTokenByAuthId(authId);
	}

}
