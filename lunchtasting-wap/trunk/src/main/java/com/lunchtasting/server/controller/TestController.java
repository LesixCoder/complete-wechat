package com.lunchtasting.server.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.biz.temporaryEnroll.TempooraryInvitationCodeBIZ;
import com.lunchtasting.server.biz.temporaryEnroll.TemporaryEnrollBIZ;
import com.lunchtasting.server.biz.temporaryEnroll.TemporaryOrdersReturnBIZ;
import com.lunchtasting.server.helper.KeyStaiticCommonHelper;
import com.lunchtasting.server.po.lt.TemporaryUserWeChat;
import com.lunchtasting.server.po.temporaryEnroll.TemporaryEnroll;

@Controller
public class TestController {
	@Autowired
	private TemporaryEnrollBIZ temporaryEnrollBIZ;
	
	@Autowired
	private TemporaryOrdersReturnBIZ temporaryOrdersReturnBiz;
	@Autowired
	private TempooraryInvitationCodeBIZ codeBIZ;
/*	@RequestMapping(value = "test1")
	@ResponseBody
	public Object activity(HttpServletRequest request){
		TemporaryEnroll temporaryEnroll =new TemporaryEnroll();
		temporaryEnroll.setId((long) 6666);
		temporaryEnrollBIZ.create(temporaryEnroll);
		return temporaryEnroll;
	}
	@RequestMapping(value = "test12")
	@ResponseBody
	public Object test12(HttpServletRequest request){
		return temporaryEnrollBIZ.apply(1, "zhaoyi");
	}
	@RequestMapping(value = "test2")
	@ResponseBody
	public Object test13(HttpServletRequest request){
		return temporaryEnrollBIZ.applyTo(Long.parseLong("808930785993162752"),1, "ceshi", "ceshi", Long.parseLong("808930785993162752"));
	}
	@RequestMapping(value = "test3")
	@ResponseBody
	public Object test122(HttpServletRequest request){
		return temporaryEnrollBIZ.verifySex(1, Long.parseLong("808944952741462016"));
	}
	@RequestMapping(value = "test4")
	@ResponseBody
	public Object test12211(HttpServletRequest request){
		return temporaryOrdersReturnBiz.checkButton(2, 6000);
	}
	@RequestMapping(value = "test5")
	@ResponseBody
	public Object test1222211(HttpServletRequest request){
		System.out.println("=============");
		return codeBIZ.verdictCode(request.getParameter("code"));
	}
	@RequestMapping(value = "test6")
	@ResponseBody
	public Object test122223331(HttpServletRequest request){
		System.out.println("=============");
		return codeBIZ.employCode(request.getParameter("code"),Long.parseLong("121321321321"));
	}
	
	@RequestMapping(value = "test123")
	@ResponseBody
	public Object shshssh(HttpServletRequest request){
		TemporaryUserWeChat user = new TemporaryUserWeChat();
		user.setId(Long.parseLong("809261323111104512"));
		user.setOpenId("o-szbwIZ7RFZsK4RVNjnfDyPRsJQ");
		request.getSession().setAttribute(KeyStaiticCommonHelper.TEMPORARY_LOGIN_SESSION_USER,user );
		return "厉害了";
	}*/
	@RequestMapping(value = "hahahahahahahahhahahahahahahahaah")
	@ResponseBody
	public Object activity(){
		try {
			codeBIZ.add(20);
		} catch (Exception e) {
			// TODO: handle exception 
			return "tm的有BUG";
		}
		
		return "成功插入数据";
	}
	@RequestMapping(value = "test852")
	
	@ResponseBody
	public Object actividssdsdsadty(){
		return temporaryOrdersReturnBiz.getAllCounut();
	}
	
	@RequestMapping(value = "peidui")
	@ResponseBody
	public Object sdhiuwshdjk(HttpServletRequest request){
		Long userId = Long.parseLong(request.getParameter("u1"));
		Long userId2 = Long.parseLong(request.getParameter("u2"));
		return temporaryEnrollBIZ.applyhaha(userId,userId2);
	}
	public static void main(String[] args) {
		
		String s="{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}";
		JSONObject obj = JSON.parseObject(s);
		System.out.println("测试!测试!测试!测试!测试!测试!测试!测试!测试!测试!测试!测试!测试!测试!测试!测试!测试!测试!测试!测试!测试!");
	}
}
