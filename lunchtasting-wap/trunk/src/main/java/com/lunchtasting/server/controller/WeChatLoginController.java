package com.lunchtasting.server.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.temporaryEnroll.TemporaryEnrollBIZ;
import com.lunchtasting.server.biz.temporaryEnroll.TemporaryOrdersReturnBIZ;
import com.lunchtasting.server.biz.user.UserSmsBIZ;
import com.lunchtasting.server.biz.user.WeChatLoginBIZ;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.KeyStaiticCommonHelper;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.po.lt.TemporaryUserWeChat;
import com.lunchtasting.server.po.lt.UserSms;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/wechat")
public class WeChatLoginController {
	@Autowired
	private TemporaryOrdersReturnBIZ temporaryOrdersReturnBiz;
	@Autowired
	private WeChatLoginBIZ weChatLoginBIZ;
	
	@Autowired
	private UserSmsBIZ userSmsBIZ;
	
	@Autowired
	private TemporaryEnrollBIZ temporaryEnrollBIZ;
	/**
	 * 微信登陆  取code
	 * @param request
	 * @param response
	 * @return chenchen
	 * @throws IOException 
	 */
	@RequestMapping(value = "/wechatgetCode")
	public String wechatInvoking(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
				"appid="+KeyStaiticCommonHelper.APP_ID +    
				"&redirect_uri=http%3A%2F%2F"+URLEncoder.encode(KeyStaiticCommonHelper.rootUrl+"/wechat/wechatUserLogin") +
				"&response_type=code" +
				"&scope=snsapi_userinfo" +
				"&state=STATE#wechat_redirect"; 
		response.sendRedirect(url);
		return null;
	}
	
	/**
	 * 微信登陆 
	 * @param request
	 * @param response
	 * @return chenchen
	 * @throws Exception 
	 */
	@RequestMapping(value = "/wechatUserLogin")
	public String wechatLogin(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String code = null ;
		HashMap map = null;
		try {
			code= request.getParameter("code");
		} catch (Exception e) {
			return "/temporaryEnroll/index";//返回首页
		}
		try{
			map =weChatLoginBIZ.webchatLogin(code);
		}catch (Exception e) {
			e.printStackTrace();
			return "/temporaryEnroll/index"; //失败页面
		}
		if(map==null){
			return "/temporaryEnroll/index";//失败页面
		}
		request.getSession().setAttribute(KeyStaiticCommonHelper.TEMPORARY_LOGIN_SESSION_USER,map.get("user"));
		//等有微信登录对象后处
		String oldurl = (String) request.getSession().getAttribute("oldurl");
		if(oldurl!=null && !oldurl.equals("")){
			request.getSession().setAttribute("pf",false);
			request.getSession().removeAttribute("oldurl");
			response.sendRedirect(oldurl);
			return null;
		}
		return "temporaryEnroll/signUp";//成功
	}
	
	/**
	 * 用户发送验证码短信
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/smsCode" ,method=RequestMethod.POST)
	@ResponseBody
	public Object smsCode(Model model,HttpServletRequest request) throws Exception {
		String phone = request.getParameter("phone");
		String type = request.getParameter("type");
		if(ValidatorHelper.isEmpty(phone) || !ValidatorHelper.isNumber(type)){
			model.addAttribute("msg", "手机号码不能为空!");
			model.addAttribute("result", 1);
			return model;
		}
		try {
			int intType = Integer.parseInt(type);
			/**
			 * 判断是否是手机号
			 */
			Pattern pattern = Pattern.compile("^((\\(\\d{2,3}\\))|(\\d{3}\\-))?1[3,4,5,6,7,8]{1}\\d{9}$"); 
		    Matcher matcher = pattern.matcher(phone);
		    if(!(matcher.matches())){
		    	model.addAttribute("msg", "您输入的不是手机号！！");
		    	model.addAttribute("result", 2);
				return model;
		    }
			
			/**
			 * 报名短信，判断号码是否已经报名过
			 */
				int count = temporaryEnrollBIZ.checkUser(phone);
				if(count > 0){
					model.addAttribute("msg", "您已报名！");
					model.addAttribute("result", 4);
					return model;
				}
			
			/**
			 * 创建并发送短信验证码
			 */
			Boolean result = userSmsBIZ.createUserSms(phone, intType);
			if(result){
				model.addAttribute("msg", "验证码发送成功!");
		    	model.addAttribute("result", 3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/**
	 * 判断验证码是否存在或正确
	 */
	@RequestMapping(value = "/checkCode" ,method=RequestMethod.POST)
	@ResponseBody
	public Object checkCode(Model model,HttpServletRequest request){
		String verCode = request.getParameter("verCode");
		String telphone = request.getParameter("telphone");
		UserSms userSms = userSmsBIZ.getByPhoneAndType(telphone, StateEnum.SMS_TEMPORARY_ACTIVITY.getValue());
		if(ValidatorHelper.isEmpty(userSms) || !verCode.equals(userSms.getCode())
				|| userSms.getStatus().intValue() != 0){
			model.addAttribute("msg", "验证码有误!");
			model.addAttribute("result", 1);
			return model;
		}
    	model.addAttribute("result", 3);
		return model;
	}
	
	@RequestMapping(value = "/share")
	public String share() {
		return "temporaryEnroll/share";
	}
	
	@RequestMapping(value = "/recruit")
	public String recruit() {
		return "temporaryEnroll/recruit";
	}
}
