package com.lunchtasting.server.dao.receiverAddress.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.receiverAddress.ReceiverAddressDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Meals;
import com.lunchtasting.server.po.lt.ReceiverAddress;

@Repository
public class ReceiverAddressDAOImpl extends GenericDAOSupport<Long, ReceiverAddress> implements ReceiverAddressDAO{

	@Override
	protected Class<?> getObjectClass() {
		return ReceiverAddress.class;
	}

	@Override
	protected String getTableName() {
		return "receiver_address";
	}


	@Override
	public List queryReceiverAddress(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryReceiverAddress", map);
	}

	@Override
	public int updateDefaultAddress(ReceiverAddress receiverAddress) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update(getNamespace() + ".updateDefaultAddress", receiverAddress);
	}

	@Override
	public int addReceiverAddress(ReceiverAddress receiverAddress) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().insert(getNamespace() + ".updateDefaultAddress", receiverAddress);
	}

	@Override
	public int deleteReceiverAddress(ReceiverAddress receiverAddress) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().delete(getNamespace() + ".deleteReceiverAddress", receiverAddress);
	}

}
