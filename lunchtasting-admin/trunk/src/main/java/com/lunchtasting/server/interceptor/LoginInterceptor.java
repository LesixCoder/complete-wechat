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

import com.lunchtasting.server.biz.AdminUserBIZ;
import com.lunchtasting.server.biz.CookieHandlingBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.Utils;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.po.lt.UserAdmin;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 普通用户请求鉴权
 * @author xq
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {


	@Autowired
	private AdminUserBIZ userAdminBIZ;
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
		noAuthList = getNoAuthList();

		
		UserAdmin admin = new UserAdmin();
		admin = (UserAdmin)session.getAttribute(VariableHelper.LOGIN_SESSION_USER);
		
		/**
		 * session为null 看是否保存cookies
		 */
		if(admin == null){
			admin = cookieHandlingBIZ.getUserByCookieName(request, response,VariableHelper.LOGIN_COOKIE_USER);
			if(admin != null){
				session.setAttribute(VariableHelper.LOGIN_SESSION_USER, admin);
			}
		}
		
		
		/**
		 * 访问首页且用户存在 就直接登录
		 */
		if(url.equalsIgnoreCase("/")){
			if(admin != null){
				response.sendRedirect(Utils.gotoWebRoot(request)+"goToIndex" );
			}
		}
		
		/**
		 * 需要登录的请求
		 */
		if(!noAuthList.contains(url)){
			if(admin == null){
				/**
				 * session 不为null 注销掉  过滤jsessionid
				 */
				if(session != null){
					session.invalidate();  
				}
				/**
				 * 跳转到登陆页
				 */
				response.sendRedirect(Utils.gotoWebRoot(request));
			}
		}
		
		return true;
	}

//	@Override
//	public void afterCompletion(HttpServletRequest request,
//			HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		//super.afterCompletion(request, response, handler, ex);
//		String className = handler.getClass().getSimpleName().toString();
//	}

	/**
	 * 无需登录鉴权的请求
	 * 
	 * @return
	 */
	private List getNoAuthList() {
		noAuthList = new ArrayList<String>();
		noAuthList.add("/");
		noAuthList.add("/adminUserLogin");
		noAuthList.add("/go");
		noAuthList.add("/match/byId");
		return noAuthList;
	}

}
