package com.lunchtasting.server.biz.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lunchtasting.server.biz.AdminActivityBIZ;
import com.lunchtasting.server.dao.AdminActivityDao;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.po.lt.Activity;

@Service
public class AdminActivityBIZImpl implements AdminActivityBIZ {
	@Autowired
	private AdminActivityDao adminActivityDao;
	public Long addActivity(Activity activity) {
		// TODO Auto-generated method stub
		return adminActivityDao.addActivity(activity);
	}
	public Activity queryActivityById(String id) {
		// TODO Auto-generated method stub
		return adminActivityDao.queryActivityById(id);
	}
	public Long updateActivity(Activity activity) {
		// TODO Auto-generated method stub
		return adminActivityDao.updateActivity(activity);
	}
	public HashMap queryActivity(HashMap map) {
		HashMap result = new HashMap();
	    List<Activity> activityList;
	    int totalCount;
    	 try{
    		 activityList = adminActivityDao.queryActivityList(map);
    		 for(int i = 0;i<activityList.size();i++){
    			 activityList.get(i).setNewId(activityList.get(i).getId().toString());
    		 }
    		 totalCount = adminActivityDao.queryActivityListCount(map);
    	 }catch(Exception e){
			 e.printStackTrace();
			 result.put("result","1");
		     result.put("descript","查询失败");
		     result.put("totalCount",0);
		     result.put("page",new PageBean(null, Integer.parseInt(map.get("curPage").toString()), 0, Integer.parseInt(map.get("pageSize").toString())));
		     return result;
    	 }
    	 if(activityList != null && activityList.size() > 0){
    		 result.put("result","0");
		     result.put("descript","查询成功");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(activityList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }else{
    		 result.put("result","0");
		     result.put("descript","没有更多");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(activityList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }
    	 return result;
	}
	public Long deleteActivity(HashMap map) {
		// TODO Auto-generated method stub
		return (long) adminActivityDao.deleteActivity(map);
	}
	@Override
	public int topActivity(HashMap map) {
		// TODO Auto-generated method stub
		return adminActivityDao.topActivity(map);
	}
	@Override
	public List queryActivityNotLimit() {
		// TODO Auto-generated method stub
		return adminActivityDao.queryActivityNotLimit();
	}

}
