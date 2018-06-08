package com.lunchtasting.server.biz.payment.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.payment.AlipayNotifityBIZ;
import com.lunchtasting.server.dao.payment.AlipayNotifityDAO;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.po.lt.payment.AlipayNotifity;
import com.lunchtasting.server.util.IdWorker;


@Service
public class AlipayNotifityBIZImpl implements AlipayNotifityBIZ {

	@Autowired
	private AlipayNotifityDAO alipayNotifityDAO;

	@Override
	public Boolean checkNotifity(Integer bizType,String tradeStatus, String outTradeNo) {
		Integer result = alipayNotifityDAO.getNumber(bizType,tradeStatus, outTradeNo);
		if(result != null && result > 0){
			return true;
		}
		return false;
	}

	@Override
	public void createAlipayNotifity(Map map) {
		map.put("id", IdWorker.getId());
		map.put("biz_type", StateEnum.PAY_COURSE.getValue());
		alipayNotifityDAO.createAlipayNotifity(map);
	}

	@Override
	public Map getAlipayNotifity(Integer bizType, String outTradeNo, String tradeStatus) {
		return alipayNotifityDAO.getAlipayNotifity(bizType, outTradeNo, tradeStatus);
	}
	
	
}
