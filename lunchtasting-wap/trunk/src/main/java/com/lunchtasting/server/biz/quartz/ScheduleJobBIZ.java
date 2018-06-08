package com.lunchtasting.server.biz.quartz;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.ScheduleJob;

public interface ScheduleJobBIZ {
	/**
	 * 得到init的数据
	 * @return
	 */
	List<ScheduleJob> getScheduleJobList();
	
	/**
	 * 后台查询
	 * @param map
	 * @return
	 */
	List queryScheduleJobList(HashMap map);
	
	/**
	 * 增加一个任务
	 */
	boolean addScheduleJob(ScheduleJob scheduleJob);
}
