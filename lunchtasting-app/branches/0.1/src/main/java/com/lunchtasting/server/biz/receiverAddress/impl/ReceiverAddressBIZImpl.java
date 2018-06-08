package com.lunchtasting.server.biz.receiverAddress.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.receiverAddress.ReceiverAddressBIZ;
import com.lunchtasting.server.dao.receiverAddress.ReceiverAddressDAO;

@Service 
public class ReceiverAddressBIZImpl implements  ReceiverAddressBIZ{
	@Autowired
	private ReceiverAddressDAO receiverAddressDAO;

	@Override
	public List queryReceiverAddress(HashMap map) {
		if(map.get("userId")==null){
			return null;
		}
		return receiverAddressDAO.queryReceiverAddress(map);
	}
}
