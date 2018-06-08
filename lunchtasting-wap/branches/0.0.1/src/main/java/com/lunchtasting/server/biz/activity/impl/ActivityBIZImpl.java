package com.lunchtasting.server.biz.activity.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.activity.ActivityBIZ;
import com.lunchtasting.server.biz.user.UserBIZ;
import com.lunchtasting.server.dao.activity.ActivityDAO;
import com.lunchtasting.server.dao.activity.ActivityEnrollDAO;
import com.lunchtasting.server.dao.article.ArticleDAO;
import com.lunchtasting.server.dao.user.UserDAO;
import com.lunchtasting.server.po.lt.Activity;
import com.lunchtasting.server.po.lt.ActivityEnroll;
import com.lunchtasting.server.po.lt.User;

@Service
public class ActivityBIZImpl implements ActivityBIZ {
	
	@Autowired
	private ActivityDAO activityDAO;
	
	@Autowired
	private ArticleDAO articleDAO;
	
	@Autowired
	private ActivityEnrollDAO activityEnrollDAO;

	@Override
	public List queryList(HashMap map) {
		// TODO Auto-generated method stub
		Integer TABLEtype = (Integer)map.get("TABLEtype");//获取查询范围
		if(TABLEtype==0){
			return activityDAO.queryAllList(map);
		}else if(TABLEtype==1){
			return activityDAO.queryActivityList(map);
		}else if(TABLEtype==2){
			return articleDAO.queryArticleList(map);  //8.23 需要修改 文章
		}else{
			return activityDAO.queryAllList(map);
		}
	}

	@Override
	public HashMap addActivityenroll(HashMap map) {
		HashMap rmap =new HashMap();
		//判断是否报名过此活动
		HashMap map1 =  new HashMap();
		map1.put("userId", map.get("userId"));
		map1.put("ActivityId", map.get("ActivityId"));
		int con = activityEnrollDAO.queryActivityByMapCount(map1);
		if(con==0){
			ActivityEnroll ac = new ActivityEnroll();
			ac.setUserId((Long)map.get("userId"));
			ac.setActivityId((Long)map.get("activityId"));
			ac.setName((String) map.get("name"));
			ac.setPhone((String)map.get("phone"));
			activityEnrollDAO.create(ac);
		}else if(con>0){
			rmap.put("result",0);
			rmap.put("describe","用户已经报名过此活动。");
			return rmap;
		}
		rmap.put("result",1);
		rmap.put("describe","报名成功");
		return rmap;
	}

	@Override
	public Activity queryActivityById(Long id) {
		if(id==null|| id==0){
			return null;
		}
		HashMap map = new HashMap();
		map.put("id", id);
		Activity activity =activityDAO.queryActivityById(map);
		if(activity==null||activity.getId()==0){
			return null;
		}
		return activity;
	}

	@Override
	public Integer queryApplyNum(Long activityId) {
		HashMap map = new HashMap();
		map.put("activityId", activityId);
		int i = activityEnrollDAO.queryActivityByMapCount(map);
		return i;
	}
}

