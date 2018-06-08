package com.lunchtasting.server.biz.activity.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.activity.ActivityOrderBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.activity.ActivityOrderDAO;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.po.lt.ActivityOrder;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class ActivityOrderBIZImpl implements ActivityOrderBIZ {

	@Autowired
	private ActivityOrderDAO activityOrderDAO;
	
	@Override
	public Boolean createActivityOrder(Long activityId, Long userId) {
		
		ActivityOrder order = new ActivityOrder();
		order.setId(IdWorker.getId());
		order.setActivityId(activityId);
		order.setUserId(userId);
		order.setPrice(0d);
		order.setPayPrice(0d);
		order.setStatus(2);
		activityOrderDAO.create(order);
		return true;
	}

	@Override
	public Integer getActivityOrderCount(Long activityId) {
		return activityOrderDAO.getActivityOrderCount(activityId);
	}

	@Override
	public Boolean checkUserEnroll(Long activityId, Long userId) {
		if(activityId == null || userId == null){
			return false;
		}
		
		Integer result = activityOrderDAO.getByActivityIdAndUserId(activityId, userId);
		if(result == null || result == 0){
			return false;
		}
		return true;
	}

	@Override
	public Integer getUserActivityOrderCount(Long userId) {
		return activityOrderDAO.getUserActivityOrderCount(userId);
	}

	@Override
	public List queryUserActivityOrderList(Long userId, Integer page,
			Integer pageSize) throws ParseException {
		List list = activityOrderDAO.queryUserActivityOrderList(userId, page, pageSize);
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
