package com.lunchtasting.server.biz.timer.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunchtasting.server.biz.timer.TimerCourseBIZ;
import com.lunchtasting.server.dao.gym.CourseOrderDAO;
import com.lunchtasting.server.dao.user.UserDAO;
import com.lunchtasting.server.dao.user.UserDepositDAO;
import com.lunchtasting.server.po.lt.UserDeposit;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.IdWorker;

@Transactional(rollbackFor = Throwable.class)
@Service
public class TimerCourseBIZImpl implements TimerCourseBIZ {

	@Autowired
	private CourseOrderDAO courseOrderDAO;
	@Autowired
	private UserDepositDAO userDepositDAO;
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void courseUp() {
		courseOrderDAO.timerNotifyOrderUp();
	}

	@Override
	public void courseFinish() {
		courseOrderDAO.timerNotifyOrderFinish();
	}

	@Override
	public void courseBonus() throws Exception {
		List orderList = courseOrderDAO.queryNoBonusCourseOrderList();
		if(orderList != null){
			for(int i =0 ;i<orderList.size();i++){
				HashMap map = (HashMap) orderList.get(i);
				
				long orderId = Long.parseLong(map.get("id").toString());
				long userId = Long.parseLong(map.get("user_id").toString());
				long inviteUserId = Long.parseLong(map.get("invite_user_id").toString());
				double payPrice = Double.parseDouble(map.get("pay_price").toString());
				int status = Integer.parseInt(map.get("status").toString());
				Date createTime =  DateUtil.convertStringTODate(
							map.get("create_time").toString(), DateUtil.YYYY_MM_DD_HH_MM_SS);
				
				/**
				 * 创建分红记录
				 */
				double scale = 0.1;
				double bonus = payPrice * scale;
				
				UserDeposit deposit = new UserDeposit();
				deposit.setId(IdWorker.getId());
				deposit.setUserId(inviteUserId);
				deposit.setBizId(orderId);
				deposit.setBizType(1);
				deposit.setMoney(bonus);
				deposit.setBonusTime(createTime);
				userDepositDAO.create(deposit);
				
				/**
				 * 分红给邀请用户
				 */
				Integer result = userDAO.updateAddDeposit(inviteUserId, bonus);
				if(result == null || result == 0){
					throw new Exception();
				}
				
				/**
				 * 修改订单已分红
				 */
				Integer result2 = courseOrderDAO.updateOrderIsBonus(orderId);
				if(result2 == null || result2 == 0){
					throw new Exception();
				}
			}
		}
	}

	@Override
	public void courseUpNotifiyMeessage() {
		
	}

//	@Override
//	public void courseFullRefund() throws Exception {
//		
//		
//	}
}
