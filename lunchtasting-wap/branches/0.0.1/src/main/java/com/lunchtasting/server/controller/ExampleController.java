package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.activity.ActivityBIZ;
import com.lunchtasting.server.biz.activity.impl.ActivityBIZImpl;
import com.lunchtasting.server.biz.quartz.QuartzBIZ;
import com.lunchtasting.server.biz.quartz.ScheduleJobBIZ;
import com.lunchtasting.server.biz.user.UserBIZ;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.Activity;
import com.lunchtasting.server.po.lt.ScheduleJob;



/**
 * 例子
 * @author xq
 *
 */
@Controller
public class ExampleController extends BaseController {
	
	@Autowired
	private UserBIZ userBIZ;
	@Autowired
	private ActivityBIZ activityBIZ;
	@Autowired
	private QuartzBIZ quartzBIZ;
	@Autowired
	private ScheduleJobBIZ scheduleJobBIZ;
	/**
	 * 这里返回普通页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/test999")
	public String test1(Model model){
		
		model.addAttribute("msg", "123456");
		return "/index";

	}
	
	
	/**
	 * 这里用于ajax异步请求 返回json数据   表明@ResponseBody标签 则返回json
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/test2")
	@ResponseBody
	public Object test2(HttpServletRequest request,HttpServletResponse response){
		
		return userBIZ.getUserByPhone("18747363122");
	}
	
	
	/**
	 * 返回user集合
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/test3")
	@ResponseBody
	public Object test3(HttpServletRequest request,HttpServletResponse response){
		HashMap map = new HashMap();
		return activityBIZ.queryList(map);
	}
	
	/**
	 * 返回user集合
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/test4")
	@ResponseBody
	public Object test4(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HashMap map = new HashMap();
		//return activityBIZ.queryList(map);
		return quartzBIZ.getAllJob();
		//return quartzBIZ.getRunningJob();
	}
	
	@RequestMapping(value = "/test5")
	@ResponseBody
	public Object test5(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ScheduleJob job2 = new ScheduleJob();
        job2.setJobName("test0103");
        job2.setJobGroup("test");
        job2.setIsConcurrent("1");
        job2.setJobStatus("1");
        job2.setMethodName("hehehe3");
        job2.setCronExpression("0 15 17 29 8 ? 2016");
        job2.setBeanClass("com.lunchtasting.server.quartz.QuartzJobInit");
        if(scheduleJobBIZ.addScheduleJob(job2)){
        	 quartzBIZ.addJob(job2);
        }
		return job2;
	}
	/**
	 * 测试URL
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/test7")
	@ResponseBody
	public Object test7(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String sj2 = request.getServletPath();
		String z = request.getQueryString();
		if(z==null||z.equals("")){
			return request.getContextPath()+request.getServletPath();
		}
		return request.getContextPath()+request.getServletPath()+"?"+z;
	}
}
