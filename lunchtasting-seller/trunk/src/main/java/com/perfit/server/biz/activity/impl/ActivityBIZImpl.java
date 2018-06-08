package com.perfit.server.biz.activity.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lunchtasting.server.po.lt.Activity;
import com.lunchtasting.server.util.IdWorker;
import com.perfit.server.biz.activity.ActivityBIZ;
import com.perfit.server.dao.activity.ActivityDAO;
import com.perfit.server.helper.PageBean;

@Service
public class ActivityBIZImpl implements ActivityBIZ {
	@Autowired
	private ActivityDAO activityDao;
	public Long addActivity(Activity activity) {
		// TODO Auto-generated method stub
		activity.setId(IdWorker.getId());
		return activityDao.addActivity(activity);
	}
	public Activity queryActivityById(String id) {
		// TODO Auto-generated method stub
		return activityDao.queryActivityById(id);
	}
	public Long updateActivity(Activity activity) {
		// TODO Auto-generated method stub
		return activityDao.updateActivity(activity);
	}
	public HashMap queryActivity(HashMap map) {
		HashMap result = new HashMap();
	    List activityList;
	    int totalCount;
    	activityList = activityDao.queryActivityList(map);
    	totalCount = activityDao.queryActivityListCount(map);
    	result.put("activityList",activityList);
    	result.put("totalCount",totalCount);
    	return result;
	}
	public Boolean deleteActivity(HashMap map) {
		int pf = activityDao.deleteActivity(map);
		if(pf==1){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public boolean authenticationUser(int activityId, int sellerId) {
		HashMap map = new HashMap();
		map.put("activityId",activityId);
		map.put("sellerId",sellerId);
		Integer pf =activityDao.authenticationUser(map);
		if(pf!=null && pf==1){
			return true;
		}
		return false;
	}

}
