//package com.lunchtasting.server.quartz;
//
//import org.apache.log4j.Logger;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//
//import com.lunchtasting.server.po.lt.ScheduleJob;
//
//public class QuartzJobFactory implements Job{
//    public final Logger log = Logger.getLogger(this.getClass());
//    public QuartzJobFactory(){
//         
//    }
//    @Override
//    public void execute(JobExecutionContext context) throws JobExecutionException {
//        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
//        TaskUtils.invokMethod(scheduleJob);
//    }
//}