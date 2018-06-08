package com.lunchtasting.server.biz.activity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.activity.ActivityEnrollBIZ;
import com.lunchtasting.server.dao.activity.ActivityEnrollDAO;
import com.lunchtasting.server.po.lt.ActivityEnroll;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class ActivityEnrollBIZImpl implements ActivityEnrollBIZ {
	
	@Autowired
	private ActivityEnrollDAO enrollDAO;
	
	@Override
	public Integer getEnrollCount(Long activityId) {
		return enrollDAO.getEnrollCount(activityId);
	}

	@Override
	public Boolean createEnroll(Long userId, Long activityId, String name,
			String phone) {
		
		ActivityEnroll enroll = new ActivityEnroll();
		enroll.setUserId(userId);
		enroll.setActivityId(activityId);
		enroll.setName(name);
		enroll.setPhone(phone);
		enrollDAO.create(enroll);
		if(ValidatorHelper.isNotEmpty(enroll.getId())){
			return true;
		}
		return false;
	}

	@Override
	public Boolean checkIsEnroll(Long activityId, Long userId) {
		Long id = enrollDAO.getIsEnroll(activityId, userId);
		if(null != id){
			return true;
		}
		return false;
	}

	@Override
	public Long getEnrollId(Long activityId, Long userId) {
		return enrollDAO.getEnrollId(activityId, userId);
	}

}
