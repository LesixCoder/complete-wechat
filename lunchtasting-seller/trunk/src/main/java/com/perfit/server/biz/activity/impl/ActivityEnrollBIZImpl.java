package com.perfit.server.biz.activity.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfit.server.biz.activity.ActivityEnrollBIZ;
import com.perfit.server.dao.activity.ActivityEnrollDAO;
@Service
public class ActivityEnrollBIZImpl implements ActivityEnrollBIZ{
	@Autowired
	private ActivityEnrollDAO activityEnrollDAO;
	
	@Override
	public List queryEnrollerList(HashMap map) {
		// TODO Auto-generated method stub
		HashMap result = new HashMap();
		int totalCount;
		List activityEnrollList;
		activityEnrollList = activityEnrollDAO.queryEnrollerList(map);
		return activityEnrollList;
	}

	@Override
	public Integer queryEnrollerListCount(Long activityId) {
		return  activityEnrollDAO.queryEnrollerListCount(activityId);
	}
}
