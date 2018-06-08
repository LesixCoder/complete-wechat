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
import com.lunchtasting.server.biz.user.UserAdminBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.CommonHelper;
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
	private UserAdminBIZ userAdminBIZ;
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
		
		UserAdmin userAdmin = (UserAdmin)session.getAttribute(VariableHelper.LOGIN_SESSION_USER);
		if(userAdmin == null){
			userAdmin = cookieHandlingBIZ.getUserByCookieName(request, response,VariableHelper.LOGIN_COOKIE_USER);
			if(userAdmin != null){
				session.setAttribute(VariableHelper.LOGIN_SESSION_USER, userAdmin);
			}
		}
		
		/**
		 * 需要登录的请求
		 */
		if(!noAuthList.contains(url)){
			if(userAdmin == null){
				/**
				 * session 不为null 注销掉  过滤jsessionid
				 */
				if(session != null){
					session.invalidate();  
				}
				/**
				 * 跳转到登陆页
				 */
//				request.getSession().setAttribute("goUrl", requestUrl);
				response.sendRedirect("/login");
				return false;
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
		noAuthList.add("/login");
		noAuthList.add("/doLogin");
		noAuthList.add("/logout");
		noAuthList.add("/testImage");

		return noAuthList;
	}

}
