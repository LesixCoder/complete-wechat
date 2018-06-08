package com.lunchtasting.server.biz.payment.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.payment.TenpayNotifityBIZ;
import com.lunchtasting.server.dao.payment.TenpayNotifityDAO;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.util.IdWorker;

@Service
public class TenpayNotifityBIZImpl implements TenpayNotifityBIZ {
	
	@Autowired
	private TenpayNotifityDAO tenpayNotifityDAO;
	
	@Override
	public Boolean checkNotifity(Integer bizType, String outTradeNo) {
		Integer result = tenpayNotifityDAO.getNumber(bizType, outTradeNo);
		if(result != null && result > 0){
			return true;
		}
		return false;
	}

	@Override
	public void createTenpayNotifity(Map map) {
		map.put("id", IdWorker.getId());
		map.put("biz_type", StateEnum.PAY_COURSE.getValue());
		tenpayNotifityDAO.createTenpayNotifity(map);
	}

	@Override
	public Map getTenpayNotifity(Integer bizType, String outTradeNo) {
		return tenpayNotifityDAO.getTenpayNotifity(bizType, outTradeNo);
	}
}
