package com.perfit.server.dao.activity;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Activity;

public interface ActivityDAO extends  GenericDAO<Activity>{
	//保存活动
	Long addActivity(Activity activity);
	//更新活动
	Long updateActivity(Activity activity);
	//查询活动ById
	Activity queryActivityById(String id);
	
	int queryActivityListCount(HashMap map);
	
	List<Activity> queryActivityList(HashMap map);
	
	int deleteActivity(HashMap map);
	
	Integer authenticationUser(HashMap map);
}
