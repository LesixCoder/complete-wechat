package com.lunchtasting.server.quartz;

import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.quartz.QuartzBIZ;
import com.lunchtasting.server.biz.quartz.ScheduleJobBIZ;
import com.lunchtasting.server.biz.quartz.impl.QuartzBIZImpl;
import com.lunchtasting.server.po.lt.ScheduleJob;
public class QuartzJobInit {
	private static final Logger log = Logger.getLogger(QuartzJobInit.class);
	@Autowired
	private QuartzBIZ quartzBIZ;
	@Autowired
	private ScheduleJobBIZ scheduleJobBIZ;
	public void init(){
		try {
			List<ScheduleJob> list = scheduleJobBIZ.getScheduleJobList();
			for (ScheduleJob scheduleJob : list) {
				quartzBIZ.addJob(scheduleJob);
			}
/*			ScheduleJob job = new ScheduleJob();
	        job.setJobName("test0101");
	        job.setJobGroup("test");
	        job.setIsConcurrent("1");
	        job.setJobStatus("1");
	        job.setMethodName("hehehe2");
	        job.setCronExpression("0/10 * * * * ?");
	        job.setBeanClass("com.lunchtasting.server.quartz.QuartzJobInit");
	        scheduleJobBIZ.addScheduleJob(job);*/
/*	        
	        quartzBIZ.addJob(job);
	        ScheduleJob job2 = new ScheduleJob();
	        job2.setJobName("test0102");
	        job2.setJobGroup("test");
	        job2.setIsConcurrent("1");
	        job2.setJobStatus("1");
	        job2.setMethodName("hehehe3");
	        job2.setCronExpression("0 32 10 26 8 ? 2016");
	        job2.setBeanClass("com.lunchtasting.server.quartz.QuartzJobInit");*/
	     
	      //  quartzBIZ.addJob(job2);
			log.info("=============预加载成功==============");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("=============预加载出错==============");
			e.printStackTrace();
		}
		System.out.println("=====================");
		System.out.println("heheheheheheheheh!");
		System.out.println("=====================");
	}
	public void hehehe2(ScheduleJob scheduleJob){
		System.out.println("=====================");
		System.out.println("sssssssssssssss!"+scheduleJob.getJobId());
		System.out.println("=====================");
	}
	
	public void hehehe3(ScheduleJob scheduleJob){
		System.out.println("=====================");
		System.out.println("测试直接调用"+scheduleJob.getJobName());
		System.out.println("=====================");
	}
}
