package com.lunchtasting.server.dao.scheduleJob;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.model.BasicPOModel;
import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.ScheduleJob;

public interface ScheduleJobDAO extends GenericDAO<ScheduleJob>{
	/**
	 * init查询的加载列表
	 * @return
	 */
	
	List<ScheduleJob> getScheduleJobList();
	/**
	 * 后台查询列表
	 * @param map
	 * @return
	 */
	List queryScheduleJobList(HashMap map);
	
	/**
	 * name和组名的唯一性
	 */
	Integer queryScheduleJobCount(String jobName,String jobGroup);
}
