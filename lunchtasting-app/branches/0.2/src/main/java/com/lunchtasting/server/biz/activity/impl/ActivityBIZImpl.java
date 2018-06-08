package com.lunchtasting.server.biz.activity.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lunchtasting.server.biz.activity.ActivityBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.activity.ActivityDAO;
import com.lunchtasting.server.po.lt.Activity;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class ActivityBIZImpl implements ActivityBIZ {
	
	@Autowired
	private ActivityDAO activityDAO;
	
	@Override
	public Activity getById(Long id) {
		return activityDAO.getById(id);
	}


	@Override
	public Integer getIndexCount() {
		return activityDAO.getIndexCount();
	}


	@Override
	public List queryIndexList(Integer page, Integer pageSize) {
		List list = activityDAO.queryActivityList(page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				int bizType = Integer.parseInt(map.get("biz_type").toString());
				if(bizType == 1){
					/**
					 * 时间
					 */
					map.put("time", map.get("begin_time")+"-"+ map.get("end_time"));
				}
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url"));
				
				
				map.remove("begin_time");
				map.remove("end_time");
				newList.add(map);
			}
			return newList;
		}
		return list;
	}
	
	@Override
	public Integer getActivityCount() {
		return activityDAO.getActivityCount();
	}

	@Override
	public List queryActivityList(Integer page, Integer pageSize) {
		List list = activityDAO.queryActivityList(page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 时间
				 */
				map.put("time", map.get("begin_time")+"-"+ map.get("end_time"));
				map.remove("begin_time");
				map.remove("end_time");
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url"));
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	}

	@Override
	public Map getActivityDetail(Long activityId) {
		return null;
	}
	
}
