package com.lunchtasting.server.helper;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;

import com.lunchtasting.server.enumeration.StateEnum;
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
	 * @param request
	 * @param authId
	 * @param authToken
	 * @param accessToken
	 * @return
	 */
	public static String getLoginAuthCode(HttpServletRequest request,String authId,String authToken, String accessToken) {
		String result = null;
		List<String> list = new ArrayList<String>();
		if(StringUtils.isNotEmpty(request.getHeader("systemType"))){
			list.add(request.getHeader("systemType").toString());
		}
		if(StringUtils.isNotEmpty(request.getHeader("systemVersion"))){
			list.add(request.getHeader("systemVersion").toString());
		}
		if(StringUtils.isNotEmpty(request.getHeader("screenWidth"))){
			list.add(request.getHeader("screenWidth").toString());
		}
		if(StringUtils.isNotEmpty(request.getHeader("screenHeight"))){
			list.add(request.getHeader("screenHeight").toString());
		}
		if(StringUtils.isNotEmpty(request.getHeader("appVersion"))){
			list.add(request.getHeader("appVersion").toString());
		}
		if(StringUtils.isNotEmpty(request.getHeader("platform"))){
			list.add(request.getHeader("platform").toString());
		}
		list.add(authId);
		list.add(authToken);
		list.add(accessToken);
		
		result = StringUtils.join(list, " | ");
		result = StringEncrypt.SHA256(result);
		return result;
	}
	
	/**
	 * 获得头部请求加密字符串
	 * @param headers
	 * @param authToken
	 * @param accessToken
	 * @return
	 */
	public static String getAuthCode(HttpServletRequest request, String authToken, String accessToken) {
		String result = null;
		List<String> list = new ArrayList<String>();
		if(StringUtils.isNotEmpty(request.getHeader("systemType"))){
			list.add(request.getHeader("systemType").toString());
		}
		if(StringUtils.isNotEmpty(request.getHeader("systemVersion"))){
			list.add(request.getHeader("systemVersion").toString());
		}
		if(StringUtils.isNotEmpty(request.getHeader("screenWidth"))){
			list.add(request.getHeader("screenWidth").toString());
		}
		if(StringUtils.isNotEmpty(request.getHeader("screenHeight"))){
			list.add(request.getHeader("screenHeight").toString());
		}
		if(StringUtils.isNotEmpty(request.getHeader("appVersion"))){
			list.add(request.getHeader("appVersion").toString());
		}
		if(StringUtils.isNotEmpty(request.getHeader("platform"))){
			list.add(request.getHeader("platform").toString());
		}
		if(StringUtils.isNotEmpty(request.getHeader("authId"))){
			list.add(request.getHeader("authId").toString());
		}
		
		list.add(authToken);
		list.add(accessToken);
		
		result = StringUtils.join(list, " | ");
		result = StringEncrypt.SHA256(result);
		return result;
	}
	
	/**
	 * 判断头部请求是否正确
	 * 
	 * @param request
	 * @return
	 */
	public static boolean checkHeaders(HttpServletRequest request) {
		if (StringUtils.isEmpty(request.getHeader("systemType"))) {
			return false;
		}
		if (StringUtils.isEmpty(request.getHeader("systemVersion"))) {
			return false;
		}
		if (StringUtils.isEmpty(request.getHeader("screenWidth"))) {
			return false;
		}
		if (StringUtils.isEmpty(request.getHeader("screenHeight"))) {
			return false;
		}
		if (StringUtils.isEmpty(request.getHeader("appVersion"))) {
			return false;
		}
		if (StringUtils.isEmpty(request.getHeader("platform"))) {
			return false;
		}
//		if (request.getHeader("platform").toString().equals(
//				StateEnum.PLATFORM_IOS.getValue().toString())) {
//			if (StringUtils.isEmpty(request.getHeader("device_token"))) {
//				return false;
//			}
//		}
		if (request.getHeader("platform").toString().equals(
				StateEnum.PLATFORM_ANDROID.getValue().toString())) {
			if (StringUtils.isEmpty(request.getHeader("imei"))) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(getAccessToken(11l));
	}
}
