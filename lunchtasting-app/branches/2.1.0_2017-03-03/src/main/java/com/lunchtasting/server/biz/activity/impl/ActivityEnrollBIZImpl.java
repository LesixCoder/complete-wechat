package com.lunchtasting.server.biz.activity.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.activity.ActivityEnrollBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.activity.ActivityEnrollDAO;
import com.lunchtasting.server.dao.user.UserSmsDAO;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.po.lt.ActivityEnroll;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class ActivityEnrollBIZImpl implements ActivityEnrollBIZ {
	
	@Autowired
	private ActivityEnrollDAO enrollDAO;
	@Autowired
	private UserSmsDAO smsDAO;
	
	@Override
	public Integer getEnrollCount(Long activityId) {
		return enrollDAO.getEnrollCount(activityId);
	}

	@Override
	public Boolean createEnroll(Long userId,Long activityId,String name,String phone
			,Integer sex,Integer age,String remark,Long smsId) {
		
		/**
		 * 修改验证码已失效
		 */
		smsDAO.updateCodeExpire(smsId);
		
		
		ActivityEnroll enroll = new ActivityEnroll();
		enroll.setId(IdWorker.getId());
		enroll.setUserId(userId);
		enroll.setActivityId(activityId);
		enroll.setName(name);
		enroll.setPhone(phone);
		enroll.setSex(sex);
		enroll.setAge(age);
		enroll.setRemark(remark);
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

	@Override
	public Integer getUserEnrollerCount(Long userId) {
		return enrollDAO.getUserEnrollerCount(userId);
	}

	@Override
	public List queryUserEnrollerList(Long userId,Integer page,Integer pageSize) throws ParseException {
		List list = enrollDAO.queryUserEnrollerList(userId, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL0+"w/330/h/216");
				
				/**
				 * 时间
				 */
				map.put("time",CommonHelper.getActivityTime(
							map.get("begin_time").toString(), map.get("end_time").toString(),1));
			
				
				/**
				 * 状态
				 */
				int status = CommonHelper.getActivityStatus(
						map.get("begin_time").toString(), map.get("end_time").toString());
				if(status == 1){
					
					/**
					 * 几天开始
					 */
					String differDay = CommonHelper.getActivityDifferDay(map.get("begin_time").toString());
					map.put("differ_day", differDay);
				}
				map.put("status", status);
				
				map.remove("begin_time");
				map.remove("end_time");
				newList.add(map);
			}
			return newList;
		}
		return list;
	}

}
