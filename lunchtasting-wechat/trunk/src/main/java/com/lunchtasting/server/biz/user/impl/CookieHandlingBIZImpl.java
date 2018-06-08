package com.lunchtasting.server.biz.user.impl;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.codec.Base64;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import com.lunchtasting.server.biz.user.CookieHandlingBIZ;
import com.lunchtasting.server.dao.user.UserDAO;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.util.ValidatorHelper;



@Service
public class CookieHandlingBIZImpl implements CookieHandlingBIZ {
	
	
	private SecureRandom random  = null;
	
	@Autowired
	private UserDAO userDAO;
		
	//定义加密用分隔符 
	private static final String DELIMITER = "//:/";
	//cookie有效期 单位天
	private static final int COOKIE_DAY = 7;
	
	private int seriesLength = 16;
	private int tokenLength = 16;
	
	
	
	public CookieHandlingBIZImpl() {
		super();
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
	}
	
		
	@Override
	public Long getUserByCookieName(HttpServletRequest request,HttpServletResponse response,
			String cookieName) {
		if(ValidatorHelper.isEmpty(cookieName)){
			return null;
		}
		
		String userId = getCookieValueByName(request, cookieName);
		if(ValidatorHelper.isEmpty(userId) || userId.equals("null")){
			return null;
		}
		
		/**
		 * 判断用户是否存在
		 */
		Long id = userDAO.getByUserId(Long.parseLong(userId));
		if(id == null){
			
			/**
			 * 不存在此user 注销cookie
			 */
			deletesCookie(request, response, cookieName);
			return null;
		}
	
		return id;
	}
	
	@Override
	public void saveCookie(HttpServletRequest request,
			HttpServletResponse response, String cookieName,String cookieValue) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(cookieName) || StringUtils.isEmpty(cookieValue)){
			return;
		}
		createCookie(response, request, cookieName, cookieValue, COOKIE_DAY);
		
	}
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response,String cookieName) {
		// TODO Auto-generated method stub
		deletesCookie(request, response, cookieName);
	}
	
	
	
	
	/** 
     * 创建cookie 
     * @param response 回应 
     * @param nameValues 存入cookie的键值对 
     * @param days 设置cookie的有效期 
     */  
	private void createCookie(HttpServletResponse response,HttpServletRequest request,  
            String name,String value,int days) {  
    	
            //加密
    		String series = generateSeriesData();   		
            value = encodeCookie(new String[]{value, series });
            // 生成新的cookie   
            Cookie cookie = new Cookie(name, value);  
            // 设置有效日期   
            cookie.setMaxAge(days * 24 * 60 * 60);  
            // 设置路径（根目录）   
            cookie.setPath(getCookiePath(request));  
            cookie.setSecure(false);
            // 把cookie放入响应中   
            response.addCookie(cookie);  
          
    }  

	
    /** 
     * 读取Cookie 
     * @param request 
     * @return Hashtable 返回cookie的键值对 
     */  
    private Hashtable<String, String> getCookies(  
            HttpServletRequest request) {  
        Cookie[] cookies = request.getCookies();  
        Hashtable<String, String> cookieHt = new Hashtable<String, String>();  
        if (cookies.length > 0) {  
            for (int i = 0; i < cookies.length; i++) {  
                Cookie cookie = cookies[i];  
                //解密
                String[] cookieTokens = decodeCookie(cookie.getValue());
                cookieHt.put(cookie.getName(), cookieTokens[0]);  
            }  
        }  
        return cookieHt;  
    }  
    
    
    /** 
     * 修改cookie中指定键的值 
     * @param request 
     * @param name 指定的键 
     * @param value 值 
     */  
    private void setCookieValueByName(HttpServletRequest request,  
            String name, String value) {  
        Cookie[] cookies = request.getCookies();  
        if (cookies.length > 0) {  
            for (int i = 0; i < cookies.length; i++) {  
                if (name.equalsIgnoreCase(cookies[i].getName())) {  
                	//加密
                	String series = generateSeriesData();   		
                    value = encodeCookie(new String[]{value, series });                    
                    cookies[i].setValue(value);  
                }  
            }  
        }  
    }  
    
    
    /** 
     * 得到指定键的值 
     * @param request 
     * @param name  指定的键 
     * @return String 值 
     */  
    private String getCookieValueByName(HttpServletRequest request,  
            String name) {  
        Cookie[] cookies = request.getCookies();  
        if (cookies != null && cookies.length > 0) {  
            for (int i = 0; i < cookies.length; i++) {  
                if (name.equalsIgnoreCase(cookies[i].getName())) {  
                	String resValue = cookies[i].getValue();  
                    //解密
                    String[] cookieTokens = decodeCookie(resValue);
                    if(cookieTokens == null || cookieTokens.length<0){
                    	 return null;
                    }
                    return cookieTokens[0];
                }  
            }  
        }  
        return null;  
    }  
    
    
    /** 
     * 销毁cookie 
     * @param request 
     * @param response 
     */  
    private void deletesCookie(HttpServletRequest request,  
            HttpServletResponse response,String cookieName) {  
    	/*
        Cookie[] cookies = request.getCookies();  
        if (cookies != null) {  
            for (int i = 0; i < cookies.length; i++) {  
                Cookie cookie = cookies[i];  
                // 销毁   
                cookie.setMaxAge(0);  
                response.addCookie(cookie);  
            }  
        }*/
    	Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);
		cookie.setPath(getCookiePath(request));
		response.addCookie(cookie);
    }  
    
    //获得cookies保存路径
	private String getCookiePath(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		return contextPath.length() > 0 ? contextPath : "/";
	}
	
	//cookies解密 
	private String[] decodeCookie(String cookieValue) throws InvalidCookieException {
		for (int j = 0; j < cookieValue.length() % 4; j++) {
			cookieValue = cookieValue + "=";
		}

		if (!Base64.isBase64(cookieValue.getBytes())) {
			throw new InvalidCookieException("Cookie token was not Base64 encoded; value was '" + cookieValue + "'");
		}

		String cookieAsPlainText = new String(Base64.decode(cookieValue.getBytes()));

		String[] tokens = org.springframework.util.StringUtils.delimitedListToStringArray(cookieAsPlainText, DELIMITER);

		if ((tokens[0].equalsIgnoreCase("http") || tokens[0].equalsIgnoreCase("https")) && tokens[1].startsWith("//")) {
			// Assume we've accidentally split a URL (OpenID identifier)
			String[] newTokens = new String[tokens.length - 1];
			newTokens[0] = tokens[0] + ":" + tokens[1];
			System.arraycopy(tokens, 2, newTokens, 1, newTokens.length - 1);
			tokens = newTokens;
		}

		return tokens;
	}


    //cookies加密 
	private String encodeCookie(String[] cookieTokens) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cookieTokens.length; i++) {
			sb.append(cookieTokens[i]);

			if (i < cookieTokens.length - 1) {
				sb.append(DELIMITER);
			}
		}

		String value = sb.toString();

		sb = new StringBuilder(new String(Base64.encode(value.getBytes())));

		while (sb.charAt(sb.length() - 1) == '=') {
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb.toString();
	}
	
	
	private String generateSeriesData() {
		byte[] newSeries = new byte[seriesLength];
		random.nextBytes(newSeries);
		return new String(Base64.encode(newSeries));
	}

	private String generateTokenData() {
		byte[] newToken = new byte[tokenLength];
		random.nextBytes(newToken);
		return new String(Base64.encode(newToken));
	}
	
	public static void main(String[] args) {		
		CookieHandlingBIZImpl c = new CookieHandlingBIZImpl();
		String result = "123456789";		
		String series = c.generateSeriesData();   		
		String ec = c.encodeCookie(new String[]{result,series});
		System.out.println(ec);
		String[] cookieTokens = c.decodeCookie(ec);
		System.out.println(cookieTokens[0]);
		
		
	}


}
