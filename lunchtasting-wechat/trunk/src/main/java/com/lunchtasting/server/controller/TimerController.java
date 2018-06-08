package com.lunchtasting.server.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.gym.CourseOrderBIZ;
import com.lunchtasting.server.biz.timer.TimerCourseBIZ;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/timer")
public class TimerController {

	@Autowired
	private TimerCourseBIZ timerCourseBIZ;
	
	/**
	 * 修改通知课程上课（进行中）
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/course/up")
	@ResponseBody
	public void courseUp(HttpServletRequest request) throws IOException{
		
		System.out.println("timer courseUp begin !!!");
		
		timerCourseBIZ.courseUp();
		
		System.out.println("timer courseUp end !!!");

	}
	
	/**
	 * 通知课程已结束
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/course/finish")
	@ResponseBody
	public void courseFinish(HttpServletRequest request) throws IOException{
		
		System.out.println("timer courseFinish begin !!!");
		
		timerCourseBIZ.courseFinish();
		
		System.out.println("timer courseFinish end !!!");
		
	}
	
	/**
	 * 计算课程提现分红
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping(value = "/course/bonus")
	@ResponseBody
	public void courseBonus(HttpServletRequest request) throws Exception{
		
		System.out.println("timer courseBonus begin !!!");
		
		timerCourseBIZ.courseBonus();
		
		System.out.println("timer courseBonus end !!!");
	}
	
	
	/**
	 * 用户上课短信通知
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/course/begin_up_message")
	@ResponseBody
	public Object courseBeGinUpMessage(HttpServletRequest request) throws Exception{
		
		String msg = "【CrazyDog咆哮狗】“加蛋蛋炒饭”您好，咆哮狗肌械训练营—将于2017年10月29日15:00—16:00开始。" +
				"建议您提前30分钟到场，接受体测服务。现场提供更衣室和存包服务，建议您携带一条毛巾并着运动服饰。上课地址：" +
				"（北京市朝阳区朝阳公园路19号佳隆国际大厦地下一层B1-110 CrossFit ShiFu美式综合体能训练馆。）";
		
		
		
		return 100;
	}
	
//	/**
//	 * 课程满团退款
//	 * @param request
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/course/full_refund")
//	@ResponseBody
//	public void courseFullRefund(HttpServletRequest request) throws Exception{
//		
//		System.out.println("timer courseFullRefund begin !!!");
//		
//		timerCourseBIZ.courseFullRefund();
//		
//		System.out.println("timer courseFullRefund end !!!");
//	}
	
	
//	/**
//	 * 折扣扣款,满团情况下退还部分费用
//	 * @param request
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/course/discount_refund")
//	@ResponseBody
//	public Object courseDiscountRefund(HttpServletRequest request) throws IOException{
//		
//		
//		
//		return 1;
//	}
	
//	/**
//	 * 全额退款，不满足开团的情况下全额退款
//	 * @param request
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/course/full_refund")
//	@ResponseBody
//	public Object courseFullRefund(HttpServletRequest request) throws IOException{
//		return 1;
//	}
	
	
}
