package com.lunchtasting.server.dao;
import java.util.HashMap;
import java.util.List;
import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Activity;

public interface AdminActivityDao extends GenericDAO<Activity>{
	//保存活动
	Long addActivity(Activity activity);
	//更新活动
	Long updateActivity(Activity activity);
	//查询活动ById
	Activity queryActivityById(String id);
	
	int queryActivityListCount(HashMap map);
	
	List<Activity> queryActivityList(HashMap map);
	
	int deleteActivity(HashMap map);
	
	int topActivity(HashMap map);
	
	List queryActivityNotLimit();
}
