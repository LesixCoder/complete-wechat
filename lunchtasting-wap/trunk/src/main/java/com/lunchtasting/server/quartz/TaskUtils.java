//package com.lunchtasting.server.quartz;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.Date;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.quartz.impl.triggers.CronTriggerImpl;
//
//import com.lunchtasting.server.po.lt.ScheduleJob;
//
//public class TaskUtils {
//    public final static Logger log = Logger.getLogger(TaskUtils.class);  
//    
//    /** 
//     * 通过反射调用scheduleJob中定义的方法 
//     *  
//     * @param scheduleJob 
//     */  
//    public static void invokMethod(ScheduleJob scheduleJob) {  
//        Object object = null;  
//        Class clazz = null;  
//        if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {  
//            object = SpringUtils.getBean(scheduleJob.getSpringId());  
//        } else if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {  
//            try {  
//                clazz = Class.forName(scheduleJob.getBeanClass());  
//                object = clazz.newInstance();  
//            } catch (Exception e) {  
//                // TODO Auto-generated catch block  
//                e.printStackTrace();  
//            }  
//  
//        }  
//        if (object == null) {  
//            log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");  
//            return;  
//        }  
//        clazz = object.getClass();  
//        Method method = null;  
//        try {  
//            method = clazz.getDeclaredMethod(scheduleJob.getMethodName(),ScheduleJob.class);  
//        } catch (NoSuchMethodException e) {  
//            log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");  
//        } catch (SecurityException e) {  
//            // TODO Auto-generated catch block  
//            e.printStackTrace();  
//        }  
//        if (method != null) {  
//            try {  
//                method.invoke(object,scheduleJob);  
//            } catch (IllegalAccessException e) {  
//                // TODO Auto-generated catch block  
//                e.printStackTrace();  
//            } catch (IllegalArgumentException e) {  
//                // TODO Auto-generated catch block  
//                e.printStackTrace();  
//            } catch (InvocationTargetException e) {  
//                // TODO Auto-generated catch block  
//                e.printStackTrace();  
//            }  
//        }  
//          
//    }  
//    /**     
//	 * 判断cron时间表达式正确性     
//	 * @param cronExpression     
//	 * @return      
//	 */     
//	public static boolean isValidExpression(final String cronExpression){     
////		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);     
//		CronTriggerImpl trigger = new CronTriggerImpl();        
//        try {     
//			trigger.setCronExpression(cronExpression);     
//			Date date = trigger.computeFirstFireTime(null);       
//	        return date != null && date.after(new Date());        
//		} catch (Exception e) {     
//		}      
//        return false;     
//	}     
//}
