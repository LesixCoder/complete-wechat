package com.lunchtasting.server.biz.user.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunchtasting.server.biz.user.UserDepositDrawBIZ;
import com.lunchtasting.server.dao.user.UserDAO;
import com.lunchtasting.server.dao.user.UserDepositDrawDAO;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.po.lt.UserDepositDraw;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Transactional(rollbackFor = Throwable.class)
@Service
public class UserDepositDrawBIZImpl implements UserDepositDrawBIZ {

	@Autowired
	private UserDepositDrawDAO drawDAO;
	@Autowired
	private UserDAO userDAO;

	@Override
	public Boolean checkDraw(Long userId) {
		Integer result  = drawDAO.getNowDrawCount(userId,3);
		if(result > 0){
			return true;
		}
		return false;
	}
	

	@Override
	public Boolean createUserDepositDraw(Long userId, Double money
			,String account,String phone,String name) throws Exception {
		if(userId == null || money == null){
			return false;
		}
		
		UserDepositDraw draw = new UserDepositDraw();
		draw.setId(IdWorker.getId());
		draw.setUserId(userId);
		draw.setMoney(money);
		draw.setAccount(account);
		draw.setPhone(phone);
		draw.setName(name);
		draw.setStatus(1);
		drawDAO.create(draw);
		
		/**
		 * 减少用户钱包存款
		 */
		Integer result = userDAO.updateReduceDeposit(userId, money);
		if(result == null || result == 0){
			throw new Exception();
		}
		
		return true;
	}


	@Override
	public List queryDrawList(Long userId, Integer page,
			Integer pageSize) {
		List list = drawDAO.queryDrawList(userId, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				
				map.put("money", CommonHelper.getDobule(
							Double.parseDouble(map.get("money").toString())));
				
				try {
					map.put("create_time", DateUtil.convertDateToString(
							DateUtil.convertStringTODate(map.get("create_time").toString(), "yyyy-MM-dd HH:mm")
								,"yyyy-MM-dd HH:mm"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				newList.add(map);
			}
			return newList;
		}
		return list;
	}


	@Override
	public Integer getDrawCount(Long userId) {
		return drawDAO.getDrawCount(userId);
	}


	@Override
	public Integer getNowDrawCount(Long userId, Integer day) {
		return drawDAO.getNowDrawCount(userId, day);
	}

	
}
