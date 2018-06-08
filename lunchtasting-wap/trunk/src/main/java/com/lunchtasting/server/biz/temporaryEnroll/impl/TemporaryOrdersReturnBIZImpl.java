package com.lunchtasting.server.biz.temporaryEnroll.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.temporaryEnroll.TemporaryOrdersReturnBIZ;
import com.lunchtasting.server.dao.temporaryEnroll.EnrollGroupDAO;
import com.lunchtasting.server.dao.temporaryEnroll.TemporaryOrdersReturnDAO;
import com.lunchtasting.server.dao.user.TemporaryUserWeChatDao;
import com.lunchtasting.server.po.temporaryEnroll.TemporaryOrdersReturn;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;
@Service
public class TemporaryOrdersReturnBIZImpl implements TemporaryOrdersReturnBIZ {
	@Autowired
	private TemporaryOrdersReturnDAO temporaryOrdersReturnDao;
	@Autowired
	private TemporaryUserWeChatDao temporaryUserWeChatDao;
	@Autowired
	private EnrollGroupDAO enrollGroupDAO;
	@Override
	public boolean verifyOrder(String out_trade_no) {
		HashMap map = new HashMap();
		map.put("outTradeNo",out_trade_no);
		TemporaryOrdersReturn order = temporaryOrdersReturnDao.getOrders(map);
		if(order==null){
			//不存在
			return true;
		}
		return false;
	}
	@Override
	public boolean insertOrder(Map map) {
		TemporaryOrdersReturn order = orderHelp(map);
		if(order!=null){
			order.setId(IdWorker.getId()); 
			temporaryOrdersReturnDao.create(order);
			return true;
		}
		return false;
	}
	@Override
	public boolean verifyOrderExist(String openid) {
		HashMap map = new HashMap();
		map.put("outTradeNo",openid);
		TemporaryOrdersReturn order = temporaryOrdersReturnDao.getOrders(map);
		if(order!=null && order.getId()!=0){
			//存在
			return true;
		}
		return false;
	} 
	/**
	 * map到TemporaryOrdersReturn
	 * @param map
	 * @return
	 */
	private TemporaryOrdersReturn orderHelp(Map map){
		TemporaryOrdersReturn order = null;
		try {
			order = new TemporaryOrdersReturn();
			order.setBankType(map.get("bank_type").toString());
/*			String cash_fee = map.get("cash_fee");
			if(ValidatorHelper.isNumber(cash_fee)){
				order.setCashFee(Integer.parseInt(cash_fee));
			}*/
			order.setNonceStr(map.get("nonce_str").toString());
			order.setOpenid(map.get("openid").toString());
			order.setOutTradeNo(map.get("out_trade_no").toString());
			order.setSign(map.get("sign").toString());
			order.setTimeEnd(map.get("time_end").toString());
			order.setTotalFee(Integer.parseInt(map.get("total_fee").toString()));
			order.setTradeType(map.get("trade_type").toString());
			order.setTransactionId(map.get("transaction_id").toString());
		} catch (Exception e) {
			order=null;
		}
		return order;
	}
	@Override
	public boolean updateUserOrder(String outTradeNo, Long userId) {
		// TODO Auto-generated method stub
		return temporaryUserWeChatDao.updateOutTradeNoById(outTradeNo, userId);
	}
	@Override
	public boolean checkButton(int con, int totalFee) {
		// TODO Auto-generated method stub
		Integer connt = temporaryOrdersReturnDao.checkButton(totalFee);
		if(connt!=null){
			if(connt<con){
				return true;
			}
		}
		return false;
	}
	@Override
	public int getAllCounut() {
		// TODO Auto-generated method stub
		return enrollGroupDAO.getAllCounut();
	}
}
