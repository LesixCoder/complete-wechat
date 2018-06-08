package com.perfit.server.filter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lunchtasting.server.po.lt.Seller;
import com.lunchtasting.server.po.lt.User;
import com.perfit.server.biz.seller.CookieHandlingBIZ;
import com.perfit.server.biz.seller.SellerBIZ;
import com.perfit.server.helper.CommonHelper;
import com.perfit.server.helper.VariableHelper;


/**
 * 登录授权过滤器
 * @author Admin
 *
 */
public class LoginFilter implements Filter {
	


	private FilterConfig config;
	@Autowired
	private SellerBIZ sellerBIZ;
	@Autowired
	private CookieHandlingBIZ cookieHandlingBIZ;
	
	/**
	 * 登录不过滤的请求
	 */
	private List<String> loginList = new ArrayList<String>();
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();		
		String url = request.getRequestURI();
		Seller user = new Seller();
		user = (Seller)session.getAttribute(VariableHelper.LOGIN_SESSION_USER);
		/**
		 * session为null 看是否保存cookies
		 */
		if(user == null){
			user = cookieHandlingBIZ.getUserByCookieName(request, response,VariableHelper.LOGIN_COOKIE_USER);
			if(user != null){
				session.setAttribute(VariableHelper.LOGIN_SESSION_USER, user);
			}
		}
		/**
		 * 访问首页且用户存在 就直接登录
		 */
		if(url.equalsIgnoreCase("/")){
			if(user != null){
				response.sendRedirect(CommonHelper.gotoWebRoot(request)+"goSeller");
				return;
			}
		}
		/**
		 * 需要过滤的请求
		 */
		if(!loginList.contains(url)){
			if(user == null){
				/**
				 * session 不为null 注销掉  过滤sessionid
				 */
				if(session != null){
					session.invalidate();  
				}
				//session.setAttribute("url", url);
				/**
				 * 跳转到登陆页
				 */
				response.sendRedirect(CommonHelper.gotoWebRoot(request)+"login");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		initCaptchaService(config);
		loginNotFilter();
	}
	
	/**
	 * 从beans-service获取CaptchaService实例.
	 */
	protected void initCaptchaService(final FilterConfig config) {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		sellerBIZ = (SellerBIZ) context.getBean("sellerBIZ");
		cookieHandlingBIZ=(CookieHandlingBIZ)context.getBean("cookieHandlingBIZ");
	}
	
	/**
	 * 定义登录不过滤的请求
	 */
	protected void loginNotFilter(){
		loginList.add("/");
		loginList.add("/sellerLogin");
		loginList.add("/login");
		loginList.add("/404/404html.html");
		loginList.add("/employCodeByCode");
		loginList.add("/getOrdersListCode");
		
		
	}
	
}
