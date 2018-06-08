package com.lunchtasting.server.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.biz.user.CookieHandlingBIZ;
import com.lunchtasting.server.biz.user.UserBIZ;
import com.lunchtasting.server.biz.user.UserWeChatBIZ;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.helper.WeChatConfig;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.helper.WeChatHelper;
import com.lunchtasting.server.po.lt.UserWeChat;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
public class LoginController {

	@Autowired
	private UserBIZ userBIZ;	
	@Autowired
	private UserWeChatBIZ userWeChatBIZ;
	@Autowired
	private CookieHandlingBIZ cookieBIZ;
	
	/**
	 * 微信登陆获取code
	 * @param request
	 * @param response
	 * @return chenchen
	 * @throws IOException 
	 */
	@RequestMapping(value = "/wechat/getCode")
	public void getCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
				"appid="+WeChatConfig.APP_ID +    
				"&redirect_uri=http%3A%2F%2F"+URLEncoder.encode(WeChatConfig.rootUrl+"/wechat/login") +
				"&response_type=code" +
				"&scope=snsapi_userinfo" +
				"&state=STATE#wechat_redirect"; 
		
		response.sendRedirect(url);
	}
	
	/**
	 * 微信登录
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/wechat/login" ) 	
	public String weChatLogin(Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String code = request.getParameter("code");
		
		if(ValidatorHelper.isEmpty(code)){
			model.addAttribute("msg", "微信登录失败！");
			return VariableHelper.ERROR_JSP;		
		}
		
		try {
			
			/**
			 * 通过code获取openId
			 */
			JSONObject atObject = WeChatHelper.getOpenID(code);
			if(atObject == null){
				return VariableHelper.ERROR_JSP;
			}
			
			/**
			 * 判断用户微信是否登录过
			 */
			UserWeChat userWeChat = userWeChatBIZ.getByUnionId(atObject.getString("unionid"));
			//UserWeChat userWeChat = userWeChatBIZ.getByOpenId(atObject.getString("openid"));
			JSONObject userObject = null;
			Long userId = null;
			if(userWeChat == null){
				userObject = WeChatHelper.getWebchatUser(atObject.getString("access_token"), atObject.getString("openid"));
				if(userObject == null){
					return VariableHelper.ERROR_JSP;
				}

			}else{
				userId = userWeChat.getUserId();

			}
			
			/**
			 * 登录
			 */
			Map dataMap = userBIZ.loginWeChat(atObject,userObject,userId,request);
			if(dataMap != null){
				
				/**
				 * 保存session和cookie
				 */
				request.getSession().setAttribute(VariableHelper.LOGIN_SESSION_USER, userId);
				cookieBIZ.saveCookie(request, response, VariableHelper.LOGIN_COOKIE_USER, userId+"");
				
				/**
				 * 判断是否有直接跳转地址
				 */
				String goUrl = (String)request.getSession().getAttribute("goUrl");
				System.out.println("goUrl = " + goUrl);
				if(ValidatorHelper.isNotEmpty(goUrl)){
					response.sendRedirect(goUrl);
					return null;
				}else{
					return "";
				}
			}
			
		} catch (Exception e) { 
			e.printStackTrace();
			return VariableHelper.ERROR_JSP;
		}
		return VariableHelper.ERROR_JSP;	
	}
	
	/**
	 * 微信登陆获取code(补充获取微信图片)
	 * @param request
	 * @param response
	 * @return chenchen
	 * @throws IOException 
	 */
	@RequestMapping(value = "/wechat/getCode2")
	public void getCode2(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
				"appid="+WeChatConfig.APP_ID +    
				"&redirect_uri=http%3A%2F%2F"+URLEncoder.encode(WeChatConfig.rootUrl+"/wechat/getUser") +
				"&response_type=code" +
				"&scope=snsapi_userinfo" +
				"&state=STATE#wechat_redirect"; 
		
		response.sendRedirect(url);
	}
	
	@RequestMapping(value = "/wechat/getUser" ) 	
	public String weChatGetUser(Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String code = request.getParameter("code");
		System.out.println("wechat/getUser begin");
		if(ValidatorHelper.isEmpty(code)){
			model.addAttribute("msg", "微信登录失败！");
			return VariableHelper.ERROR_JSP;		
		}
		
		try {
			
			/**
			 * 通过code获取openId
			 */
			JSONObject atObject = WeChatHelper.getOpenID(code);
			if(atObject == null){
				return VariableHelper.ERROR_JSP;
			}
			
			
			/**
			 * 判断用户微信是否登录过
			 */
			UserWeChat userWeChat = userWeChatBIZ.getByUnionId(atObject.getString("unionid"));
			if(userWeChat != null){
				JSONObject userObject = WeChatHelper.getWebchatUser(atObject.getString("access_token"), atObject.getString("openid"));;
				if(userObject != null){
					String imgName = QiNiuStorageHelper.getQiNiuImgName(QiNiuStorageHelper.USER_IMG_PREFIX);
					boolean result = QiNiuStorageHelper.urlUpload(imgName, userObject.getString("headimgurl"));
					if(result){
						userBIZ.updateImgUrl(userWeChat.getUserId(), imgName);
					}
				}
			}
			
			String defaultUrl = (String)request.getSession().getAttribute("defaultUrl");
			System.out.println("defaultUrl = " + defaultUrl);
			if(ValidatorHelper.isNotEmpty(defaultUrl)){
				response.sendRedirect(defaultUrl);
				return null;
			}else{
				return "";
			}
			
			
		} catch (Exception e) { 
			e.printStackTrace();
			return VariableHelper.ERROR_JSP;
		}
	}
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/logout")
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//request.getSession().setAttribute(VariableHelper.LOGIN_SESSION_USER,null);
		request.getSession().removeAttribute(VariableHelper.LOGIN_SESSION_USER);
		cookieBIZ.logout(request, response, VariableHelper.LOGIN_COOKIE_USER);
		//response.sendRedirect(CommonHelper.gotoWebRoot(request)+"login");
		return null;
	}
	
	/**
	 * 假登录，测试用
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/fake/login")
	public void fakeLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		long userId  = 921558320634920960L;
		
		/**
		 * 保存session和cookie
		 */
		request.getSession().setAttribute(VariableHelper.LOGIN_SESSION_USER, userId);
		//cookieBIZ.saveCookie(request, response, VariableHelper.LOGIN_COOKIE_USER, userId+"");
	}
	
}
