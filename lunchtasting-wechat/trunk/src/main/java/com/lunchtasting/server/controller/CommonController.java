package com.lunchtasting.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.user.UserSmsBIZ;

/**
 * 公共模块
 * Created on 2017-6-21
 * @author xuqian
 *
 */
@Controller
@RequestMapping("/conmon")
public class CommonController {
	
	@Autowired
	private UserSmsBIZ userSmsBIZ;
	
//	@RequestMapping(value = "/sendSms")
//	@ResponseBody
//	public Object SendSms(Model model, HttpServletRequest request){
//		//String content = "【CrazyDog咆哮狗】尊敬的咆哮狗参赛用户您好，原定于6月24日本周6举行的咆哮狗户外障碍赛因天气原因，主办方为了保障赛事安全性，故推迟到周日(6月25日)举行，具体行程时间不变，感谢您的理解。咆哮狗户外障碍赛组委会!";
//		String content = "【CrazyDog咆哮狗】咆哮狗户外障碍赛，勇敢的咆哮狗挑战者，挑战倒计时1天！参赛时间：2017年6月25日14:00。参赛地点：黄草湾郊野公园，公园南门或东门进入活动签到处。（切勿从西、北门进入路途相对较远）乘车路线：{公交车站}黄草湾郊野公园途径公交车311、386。{地铁站}15号线关庄站B口出。自驾停车位置：公园南门。请各位参赛者务必提前抵达会场，以免耽误您的比赛。特此短信通知。关注官方微信账号“crazyDog咆哮狗”输入关键词“参赛须知”可查询比赛详细信息。有任何疑问请拨打热线：18518481875、17701085590。“益”起奔跑，为爱咆哮。";
//		userSmsBIZ.doSendMatchSms(content);
//		return 2;
//	}
}
