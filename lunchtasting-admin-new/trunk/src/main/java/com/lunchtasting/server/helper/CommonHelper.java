package com.lunchtasting.server.helper;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class CommonHelper {

	public static String gotoWebRoot(HttpServletRequest request) {
		return "http://" + request.getServerName() + request.getContextPath() + "/";
	}
	
	/**
	 * 获得request的所有参数 
	 * @param request
	 * @return
	 */
	public static String getRequestParameter(HttpServletRequest request) {
		Enumeration enu = request.getParameterNames();
		int i = 1;
		StringBuilder strBuilder = new StringBuilder();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			if (!paraName.equals("filename")) {
				if(i == 1){
					strBuilder.append("?" + paraName + "=" + 
							request.getParameter(paraName));
				}else{
					strBuilder.append("&" + paraName + "=" + 
							request.getParameter(paraName));
				}
				i++;
			}
		}
		return strBuilder.toString();
	}
}
