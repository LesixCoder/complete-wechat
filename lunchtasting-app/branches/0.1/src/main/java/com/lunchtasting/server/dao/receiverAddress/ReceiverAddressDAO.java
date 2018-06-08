package com.lunchtasting.server.dao.receiverAddress;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.ReceiverAddress;

public interface ReceiverAddressDAO {
	/**
	 * 会员查询地址
	 * @param map
	 * @return
	 */
	List queryReceiverAddress(HashMap map);
	
	/**
	 * 修改默认收货地址
	 * @param receiverAddress
	 * @return
	 */
	int updateDefaultAddress(ReceiverAddress receiverAddress);
	
	
	/**
	 * 增加收货地址
	 * @param receiverAddress
	 * @return
	 */
	int addReceiverAddress(ReceiverAddress receiverAddress);
	
	/**
	 * 删除收货地址
	 * @param receiverAddress
	 * @return
	 */
	int deleteReceiverAddress(ReceiverAddress receiverAddress);
	
}
