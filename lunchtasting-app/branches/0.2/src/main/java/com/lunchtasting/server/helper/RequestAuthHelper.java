package com.lunchtasting.server.helper;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;

import com.lunchtasting.server.util.MD5;
import com.lunchtasting.server.util.StringEncrypt;

/**
 * 登录鉴权帮助类
 * @author xq
 *
 */
public class RequestAuthHelper {
	
	/**
	 * 客户端头部信息解析
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getHeadersInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		return map;
	}
	
	/**
	 * 获得json格式头部请求信息
	 * @param request
	 * @return
	 */
	public static String getHeadersJson(HttpServletRequest request) {
		return JSONObject.toJSONString(getHeadersInfo(request));
	}

	/**
	 * 输出json格式前端传递参数
	 * 
	 * @param request
	 * @return
	 */
	public static String getParameterJson(HttpServletRequest request) {
		Enumeration enu = request.getParameterNames();
		Map map = new HashMap();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			if (!paraName.equals("filename")) {
				map.put(paraName, request.getParameter(paraName));
			}
		}
		return JSONObject.toJSONString(map);
	}

	/**
	 * 获得accessToken
	 * @param userId
	 * @return
	 */
	public static String getAccessToken(Long userId){
		MD5 md5 = new MD5();
		return md5.getMD5ofStr(md5.getMD5ofStr(System.currentTimeMillis()+"//" +userId));
	}
	
	/**
	 * 获得头部请求加密字符串
	 * @param headers
	 * @param authToken
	 * @param accessToken
	 * @return
	 */
	public static String getUserAuthCode(HttpServletRequest request, String authToken, String accessToken) {
		String result = null;
		List<String> list = new ArrayList<String>();
		if(StringUtils.isEmpty(request.getHeader("system_type"))){
			list.add(request.getHeader("system_type").toString());
		}
		if(StringUtils.isEmpty(request.getHeader("system_version"))){
			list.add(request.getHeader("system_version").toString());
		}
		if(StringUtils.isEmpty(request.getHeader("screen_width"))){
			list.add(request.getHeader("screen_width").toString());
		}
		if(StringUtils.isEmpty(request.getHeader("screen_height"))){
			list.add(request.getHeader("screen_height").toString());
		}
		if(StringUtils.isEmpty(request.getHeader("app_version"))){
			list.add(request.getHeader("app_version").toString());
		}
		if(StringUtils.isEmpty(request.getHeader("auth_id"))){
			list.add(request.getHeader("auth_id").toString());
		}
		if(StringUtils.isEmpty(request.getHeader("platform"))){
			list.add(request.getHeader("platform").toString());
		}
		list.add(authToken);
		list.add(accessToken);
		
		result = StringUtils.join(list, " | ");
		result = StringEncrypt.SHA256(result);
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(getAccessToken(11l));
	}
}
