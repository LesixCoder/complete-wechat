package com.lunchtasting.server.biz.user;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lunchtasting.server.po.lt.User;

public interface CookieHandlingBIZ {
	
	/**
	 * 通过cookie名 获得user
	 * @param request
	 * @param response
	 * @param cookieName
	 * @return
	 */
	User getUserByCookieName(HttpServletRequest request,HttpServletResponse response,String cookieName);	
	
	/**
	 * 保存cookie
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 */
	void saveCookie(HttpServletRequest request,HttpServletResponse response,String cookieName,String cookieValue);
	
	/**
	 * 注销cookie
	 * @param request
	 * @param response
	 * @param cookieName
	 */
	void logout(HttpServletRequest request, HttpServletResponse response,String cookieName);
	
}
