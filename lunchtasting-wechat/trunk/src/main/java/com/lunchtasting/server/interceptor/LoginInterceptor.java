package com.lunchtasting.server.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lunchtasting.server.biz.user.CookieHandlingBIZ;
import com.lunchtasting.server.biz.user.UserBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.helper.WeChatConfig;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.po.lt.UserAdmin;
import com.lunchtasting.server.po.lt.UserWeChat;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 普通用户请求鉴权
 * @author xq
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	
	@Autowired
	private UserBIZ userBIZ;
	@Autowired
	private CookieHandlingBIZ cookieHandlingBIZ;
	
	/**
	 * 不需要鉴权请求
	 */
	private List<String> noAuthList = null;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();	
		String url = request.getRequestURI();
		url = url.replace("//", "/");
		String parameter = CommonHelper.getRequestParameter(request);
		String requestUrl = url + parameter;
		noAuthList = getNoAuthList();
		
		Long userId = (Long)session.getAttribute(VariableHelper.LOGIN_SESSION_USER);
		if(userId == null){
			userId = cookieHandlingBIZ.getUserByCookieName(request, response,VariableHelper.LOGIN_COOKIE_USER);
			if(userId != null){
				session.setAttribute(VariableHelper.LOGIN_SESSION_USER, userId);
			}
		}
		
		/**
		 * 需要登录的请求
		 */
		if(!noAuthList.contains(url)){
			if(userId == null){
				/**
				 * session 不为null 注销掉  过滤jsessionid
				 */
				if(session != null){
					session.invalidate();  
				}
				/**
				 * 跳转到登陆页
				 */
				request.getSession().setAttribute("goUrl", requestUrl);
				response.sendRedirect("/wechat/getCode");
				return false;
			}else{
				/**
				 * 补充机制获取微信用户信息
				 */
				User user = userBIZ.getById(userId);
				if(user != null && user.getImgUrl().equals("user/default.jpg")){
					request.getSession().setAttribute("defaultUrl", requestUrl);
					response.sendRedirect("/wechat/getCode2");
					return false;
				}
			}
		}
		
		return true;
	}

	/**
	 * 无需登录鉴权的请求
	 * @return
	 */
	private List getNoAuthList() {
		noAuthList = new ArrayList<String>();
		noAuthList.add("/wechat/getCode");
		noAuthList.add("/wechat/login");
		noAuthList.add("/wechat/getCode2");
		noAuthList.add("/wechat/getUser");
		noAuthList.add("/wxpay/match/notify");
		noAuthList.add("/wxpay/goods/notify");
		noAuthList.add("/wxpay/album_image/notify");
		noAuthList.add("/wxpay/course/notify");
		noAuthList.add("/logout");
		noAuthList.add("/fake/login");
		noAuthList.add("/match/signup/get");
		
		/**
		 * 商品
		 */
		noAuthList.add("/goods");
		noAuthList.add("/goods/detail");
		
		/**
		 * 定时任务
		 */
		noAuthList.add("/timer/course/up");
		noAuthList.add("/timer/course/finish");
		noAuthList.add("/timer/course/bonus");
		return noAuthList;
	}

}
