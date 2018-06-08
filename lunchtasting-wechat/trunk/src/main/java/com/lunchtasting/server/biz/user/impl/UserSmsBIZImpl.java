package com.lunchtasting.server.biz.user.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.user.UserSmsBIZ;
import com.lunchtasting.server.dao.user.UserSmsDAO;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.po.lt.UserSms;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.SmsUtil;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;


@Service
public class UserSmsBIZImpl implements UserSmsBIZ {
	
	@Autowired
	private UserSmsDAO userSmsDAO;
	
	
	public UserSms getByPhoneAndType(String phone,Integer type) {
		return userSmsDAO.getByPhoneAndType(phone,type);
	}

	@Override
	public Integer updateCodeExpire(Long id) {
		return userSmsDAO.updateCodeExpire(id);
	}

	@Override
	public Boolean createUserSms(String phone, Integer type) {
		
		/**
		 * 文案拼装
		 */
		String code = Utils.getRandomNumber(6);
		String content = "【CrazyDog咆哮狗】本次报名验证码："+code+"。请勿告知他人!";
		UserSms userSms = userSmsDAO.getByPhoneAndType(phone, type);
		if(userSms == null){
			userSms = new UserSms();
			userSms.setId(IdWorker.getId());
			userSms.setCode(code);
			userSms.setPhone(phone);
			userSms.setType(type);
			userSms.setContent(content);
			userSmsDAO.create(userSms);
			if(ValidatorHelper.isEmpty(userSms.getId())){
				return false;
			}
		}else{
			content = userSms.getContent();
		}
		
		/**
		 * 发送短信
		 */
		SmsUtil.sendMsg(content, phone);
		
		return true;
	}

	@Override
	public void doSendMatchSms(String content) {
		List list = userSmsDAO.querySmsPhoneList();
		if(ValidatorHelper.isNotEmpty(list)){
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				String phone = map.get("phone").toString();
				SmsUtil.sendMsg(content, phone);
				System.out.println(phone);
			}
		}
	}

}
