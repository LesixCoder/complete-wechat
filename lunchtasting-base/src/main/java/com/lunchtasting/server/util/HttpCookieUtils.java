package com.lunchtasting.server.util;



import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpCookieUtils {
	
	private static final Log log = LogFactory.getLog(HttpCookieUtils.class);
	
	private static final String encode(String value) {
		try {
			return URLEncoder.encode(value, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error(e);
		}
		return null;
	}
	
	private static final String decode(String value) {
		try {
			return URLDecoder.decode(value, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error(e);
		}
		return null;
	}
	
	public static void setValue(HttpServletResponse response,
			String domain, String name, Object value, int expiry) 
	{
		setValue(response, domain, name, String.valueOf(value), expiry);
	}
	
	public static void setValue(HttpServletResponse response,
			String domain, String name, String value, int expiry) 
	{
		if (ValidatorHelper.isEmpty(response)) {
			log.error("set Cookie failed, name = " + name + ", HttpServletResponse is null");
			return;
		}
		Cookie cookie = new Cookie(name, encode(value));
		if (!ValidatorHelper.isEmpty(domain)) {
			cookie.setDomain(domain.trim());
			cookie.setSecure(false);
		}
		cookie.setPath("/");
		cookie.setMaxAge(expiry);
		response.addCookie(cookie);
		if(log.isDebugEnabled()){
			log.debug("set cookie name: " + cookie.getName() 
					+ ", value: " + cookie.getValue() + ", domain: " 
					+ cookie.getDomain() + ", maxAge: " + cookie.getMaxAge());
		}
	}
	
	public static String getValue(final HttpServletRequest request,
			final String name, final String def) 
	{
		if(request == null || ValidatorHelper.isEmpty(request.getCookies())){
			if(log.isWarnEnabled()){
				log.warn("...... HttpCookieUtils#getValue(HttpServletRequest, " +
					"String, String): request is null OR cookies is empty");
			}
			return def;
		}
		Cookie[] cookies = request.getCookies();
		String value = def;
		for (int i = 0; i < cookies.length; i++) {
			Cookie c = cookies[i];
			if (name.equals(c.getName())) {
				value = decode(c.getValue());
				if(log.isWarnEnabled()){
					log.debug("get cookie  name: " + c.getName() + 
							", value: " + value + ", domain: " + c.getDomain());
				}
				break;
			}
		}
		if(value == null) {
			value = def;
		}
		return value;
	}
}