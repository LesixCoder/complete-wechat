//package com.lunchtasting.server.biz.quartz.impl;
//
//import java.util.HashMap;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.lunchtasting.server.biz.quartz.ScheduleJobBIZ;
//import com.lunchtasting.server.dao.scheduleJob.ScheduleJobDAO;
//import com.lunchtasting.server.po.lt.ScheduleJob;
//@Service
//public class ScheduleJobBIZImpl implements ScheduleJobBIZ{
//	@Autowired
//	private ScheduleJobDAO scheduleJobDAO;
//	
//	@Override
//	public List<ScheduleJob> getScheduleJobList() {
//		// TODO Auto-generated method stub
//		return scheduleJobDAO.getScheduleJobList();
//	}
//
//	@Override
//	public List queryScheduleJobList(HashMap map) {
//		// TODO Auto-generated method stub
//		return scheduleJobDAO.queryScheduleJobList(map);
//	}
//
//	@Override
//	public boolean addScheduleJob(ScheduleJob scheduleJob) {
//		// TODO Auto-generated method stub
//		Integer con = scheduleJobDAO.queryScheduleJobCount(scheduleJob.getJobName(),scheduleJob.getJobGroup());
//		if(con!=null && con==0){
//			scheduleJobDAO.create(scheduleJob);
//		}else{
//			return false;
//		}
//		if(scheduleJob.getJobId()!=0&&scheduleJob.getJobId()!=null){
//			return true;
//		}
//		return false;
//	}
//}
