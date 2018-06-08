package com.perfit.server.biz.seller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lunchtasting.server.po.lt.Seller;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.po.lt.UserAdmin;

public interface CookieHandlingBIZ {
	
	/**
	 * 通过cookie名 获得user
	 * @param request
	 * @param response
	 * @param cookieName
	 * @return
	 */
	Seller getUserByCookieName(HttpServletRequest request,HttpServletResponse response,String cookieName);	
	
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
