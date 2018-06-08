package com.lunchtasting.server.controller.temporary;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antlr.grammar.v3.ANTLRv3Parser.alternative_return;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.temporaryEnroll.TemporaryCinemaBIZ;
import com.lunchtasting.server.biz.temporaryEnroll.TemporaryEnrollBIZ;
import com.lunchtasting.server.helper.KeyStaiticCommonHelper;
import com.lunchtasting.server.helper.WeChatHelper;
import com.lunchtasting.server.biz.temporaryEnroll.TemporaryOrdersReturnBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.po.lt.Article;
import com.lunchtasting.server.po.lt.TemporaryUserWeChat;
import com.lunchtasting.server.po.temporaryEnroll.TemporaryCinema;
import com.lunchtasting.server.po.temporaryEnroll.TemporaryEnroll;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/saishi")
public class TemporaryEnrollController {
	@Autowired
	private TemporaryEnrollBIZ temporaryEnrollBIZ;
	@Autowired
	private TemporaryOrdersReturnBIZ temporaryOrdersReturnBiz;
	@Autowired
	private TemporaryCinemaBIZ temporaryCinemaBIZ;
	
	@RequestMapping(value = "/crossfit")
	public String index(HttpServletResponse response,HttpServletRequest request,Model model){
		try {
			if(request.getParameter("orderId")!=null){
				TemporaryEnroll enroll = temporaryEnrollBIZ.getTemporaryEnrollByUserId(Long.parseLong(request.getParameter("orderId")));
					if(enroll!=null && enroll.getState()==0){
						request.getSession().setAttribute("orderId",request.getParameter("orderId"));
					}else if(enroll!=null && enroll.getState()==1){
						response.sendRedirect("/saishi/share?orderId="+enroll.getId());
						if(request.getSession()!=null){
							request.getSession().removeAttribute("orderId");
						}
					}
			}
			if(temporaryOrdersReturnBiz.checkButton(20, 6000)){
				model.addAttribute("niaoshow",1);
			}else{
				model.addAttribute("niaoshow",0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "temporaryEnroll/index";
	}
	@RequestMapping(value = "/crossfit2")
	public String indextest(HttpServletResponse response,HttpServletRequest request,Model model){
		try {
			if(request.getParameter("orderId")!=null){
				TemporaryEnroll enroll = temporaryEnrollBIZ.getTemporaryEnrollByUserId(Long.parseLong(request.getParameter("orderId")));
					if(enroll!=null && enroll.getState()==0){
						request.getSession().setAttribute("orderId",request.getParameter("orderId"));
					}else if(enroll!=null && enroll.getState()==1){
						response.sendRedirect("/saishi/share?orderId="+enroll.getId());
						if(request.getSession()!=null){
							request.getSession().removeAttribute("orderId");
						}
					}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "temporaryEnroll/index2";
	}
	@RequestMapping(value = "/project1")
	public String project1(){
		return "temporaryEnroll/project1";
	}
	@RequestMapping(value = "/project2")
	public String project2(){
		return "temporaryEnroll/project2";
	}
	@RequestMapping(value = "/project3")
	public String project3(){
		return "temporaryEnroll/project3";
	}
	@RequestMapping(value = "/project4")
	public String project4(){
		return "temporaryEnroll/project4";
	}
	@RequestMapping(value = "/signUp")
	public String signUp(HttpServletResponse response,HttpServletRequest request,Model model) {
		try {
			TemporaryUserWeChat user = (TemporaryUserWeChat)request.getSession().getAttribute(KeyStaiticCommonHelper.TEMPORARY_LOGIN_SESSION_USER);
			Boolean pf =(Boolean)request.getSession().getAttribute("pf");
			if(pf==null){
				pf=true;
			}
			if(user==null||pf){
				String oldurl = "/saishi/signUp";
				if(ValidatorHelper.isNumber(request.getParameter("type"))){
					oldurl=oldurl+"?type="+request.getParameter("type");
				}
				request.getSession().setAttribute("oldurl", oldurl);
				response.sendRedirect("/wechat/wechatgetCode");
				
				return null;
			}
			request.getSession().setAttribute("pf", true);
			//用户已经报过名 跳转自己的分享页面
			if(user.getOutTradeNo()!=null&&!temporaryOrdersReturnBiz.verifyOrder(user.getOutTradeNo())){
				response.sendRedirect("/saishi/share?orderId="+user.getId());
				return null;
			}
			//
			if(request.getSession().getAttribute("orderId")!=null){
				model.addAttribute("orderId", request.getSession().getAttribute("orderId"));
				request.getSession().removeAttribute("orderId");
			}
			//半价票链接
			if(ValidatorHelper.isNumber(request.getParameter("type"))){
				model.addAttribute("type", request.getParameter("type"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "temporaryEnroll/index";
		}
		//多此一举 但是安全
		System.out.println("request.getParameter(type)=="+request.getParameter("type"));
		if(request.getParameter("type")!=null && request.getParameter("type").equals("521")){
			model.addAttribute("type",521);
		}
		
		return "temporaryEnroll/signUp";
	}
	@RequestMapping(value = "/share")
	public String share(Model model,HttpServletRequest request,HttpServletResponse response) {
			String userId = request.getParameter("orderId");
			if(ValidatorHelper.isNumber(userId)){
				model.addAttribute("orderIdkkk", userId);
			}
			
			/*String ticket;
			ticket = WeChatHelper.getTicket(request);
			String timestamp = WeChatHelper.getTimeStamp();
			String nonceStr = WeChatHelper.getNonceStr();
			String url = "http://wap.mperfit.com/saishi/share";
			model.addAttribute("appId", KeyStaiticCommonHelper.APP_ID);
			model.addAttribute("timestamp", timestamp);
			model.addAttribute("nonceStr", nonceStr);
			SortedMap params = WeChatHelper.getSign(nonceStr,ticket,timestamp,url);
			String sign = WeChatHelper.sign(params);
			model.addAttribute("signature", sign);*/
			return "temporaryEnroll/share";
	}
	@RequestMapping(value = "/getData")
	@ResponseBody
	public Model getData(Model model,HttpServletRequest request){
		String userId = request.getParameter("orderId");
		Long groupId =null;
		if(userId==null || userId.equals("")){
			model.addAttribute("result",1);
			model.addAttribute("url","/saishi/crossfit");
			return model;
		}
		TemporaryEnroll temporaryEnroll =  temporaryEnrollBIZ.getTemporaryEnrollByUserId(Long.parseLong(userId));
		if(temporaryEnroll==null){
			model.addAttribute("result",1);
			model.addAttribute("url","/saishi/crossfit");
			return model;
		}
		TemporaryUserWeChat user = (TemporaryUserWeChat)request.getSession().getAttribute(KeyStaiticCommonHelper.TEMPORARY_LOGIN_SESSION_USER);
		if(user==null){
			model.addAttribute("show",2);
		}else{
			if(user.getId().equals(Long.parseLong(userId))){
				model.addAttribute("show",1);
			}else{
				model.addAttribute("show",2);
			}
		}
		if(temporaryEnroll.getState()==1&&temporaryEnroll.getGroupId()!=null){
			groupId=temporaryEnroll.getGroupId();
		}
		List list = temporaryEnrollBIZ.show(Long.parseLong(userId), groupId);
		model.addAttribute("result",2);
		model.addAttribute("list",list);
		return model;
	}
	
	/**
	 * 保存
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 * @throws IOException 
	 */
	@RequestMapping(value = "/saveCinema")
	@ResponseBody
	public Model saveCinema(Model model,HttpServletRequest request){
		String userId = request.getParameter("userId");
		TemporaryCinema ac = new TemporaryCinema();
		ac.setUserId(Long.parseLong(userId));
		ac.setId(IdWorker.getId());
		try {
			while (true) {
				String r = Utils.getRandomNumber(4);
				if(temporaryCinemaBIZ.checkRandom(r) == 0){
					ac.setRandom(r);
					break;
				}
			}
			temporaryCinemaBIZ.addCinema(ac);
			model.addAttribute("result", 2);
			model.addAttribute("watch", ac.getRandom());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("result", 1);
			return model;
		}
		return model;
	}
	
	@RequestMapping(value = "/watch")
	public String watch(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		TemporaryUserWeChat user = (TemporaryUserWeChat)request.getSession().getAttribute(KeyStaiticCommonHelper.TEMPORARY_LOGIN_SESSION_USER);
		if(user==null || user.getId()==0){
			String oldurl = "/saishi/watch";
			request.getSession().setAttribute("oldurl", oldurl);
			response.sendRedirect("/wechat/wechatgetCode");
			return null;
		}
		TemporaryCinema t =  temporaryCinemaBIZ.getCinema(user.getId()+"");
		if(t != null){
			model.addAttribute("random", t.getRandom());
		}
		model.addAttribute("userId", user.getId());
		return "temporaryEnroll/watch";
	}
}
