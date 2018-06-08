package com.lunchtasting.server.biz.quartz;

import java.util.List;

import com.lunchtasting.server.po.lt.ScheduleJob;


public interface QuartzBIZ {
	
	List<ScheduleJob> getAllJob()throws Exception ;
	
	List<ScheduleJob> getRunningJob() throws Exception;
	
	void addJob (ScheduleJob scheduleJob)throws Exception;
}
