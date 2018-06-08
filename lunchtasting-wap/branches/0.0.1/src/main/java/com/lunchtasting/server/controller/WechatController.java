package com.lunchtasting.server.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.user.WechatBIZ;
import com.lunchtasting.server.helper.HttpClient;
import com.lunchtasting.server.helper.HttpsClient;
import com.lunchtasting.server.helper.KeyStaiticCommonHelper;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.helper.WebchatHelper;

/**
 * 微信
 * @author chenchen
 *
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {
	@Autowired
	private WechatBIZ wechatBIZ;
	/**
	 * 微信登陆  调用
	 * @param request
	 * @param response
	 * @return chenchen
	 */
	@RequestMapping(value = "/wechatInvoking")
	public void wechatInvoking(HttpServletRequest request,HttpServletResponse response){
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
				"appid="+KeyStaiticCommonHelper.appid +    //appid
				"&redirect_uri=https%3A%2F%2F"+URLEncoder.encode(KeyStaiticCommonHelper.rootUrl+"/wechat/wechatLogin") +
				"&response_type=code" +
				"&scope=snsapi_userinfo" +
				"&state=666#wechat_redirect"; 
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 微信登陆  code得到
	 * @param request
	 * @param response
	 * @return chenchen
	 * @throws Exception 
	 */
	@RequestMapping(value = "/wechatLogin")
	public String wechatLogin(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String code = null ;
		HashMap map = null;
		try {
			code= request.getParameter("code");
		} catch (Exception e) {
			return "/index";//返回首页
		}
		try{
			map =wechatBIZ.webchatLogin(code);
			
		}catch (Exception e) {
			System.out.println(e.toString());
			return "/index"; //失败页面
		}
		if(map==null){
			return "/index";//失败页面
		}
		request.getSession().setAttribute(VariableHelper.LOGIN_SESSION_USER,map.get("user"));
		//等有微信登录对象后处理
		return null;//成功
	}
}
