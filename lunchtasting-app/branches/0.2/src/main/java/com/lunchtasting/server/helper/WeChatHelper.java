package com.lunchtasting.server.helper;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.util.HttpUtil;
import com.lunchtasting.server.util.PropertiesLoader;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 微信模块相关帮助类
 * 
 * @author xq
 * 
 */
public class WeChatHelper {

	private static PropertiesLoader loader = new PropertiesLoader(
			"application.properties");

	private static final Logger logger = Logger.getLogger(WeChatHelper.class);

	/**
	 * 获得微信access_token
	 * 
	 * @param code
	 * @return
	 */
	public static JSONObject getAccessToken(String code) {
		String appId = loader.getProperty("WeChat.APP_ID");
		String secret = loader.getProperty("WeChat.SECRET");
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ appId + "&secret=" + secret + "&code=" + code
				+ "&grant_type=authorization_code";
		String result = HttpUtil.queryStringForGet(url);
		if (ValidatorHelper.isEmpty(result)) {
			logger.info("#WeChatHelper# getAccessToken() request is error = "
					+ result);
			return null;
		}

		JSONObject jsonObject = JSON.parseObject(result);
		/**
		 * 判断是否失败
		 */
		if (ValidatorHelper.isNotEmpty(jsonObject.get("errcode"))) {
			logger.info("#WeChatHelper# getAccessToken() return is error = "
					+ jsonObject.toJSONString());
			return null;
		}

		return jsonObject;
	}

	/**
	 * 获得微信用户信息
	 * 
	 * @param accessToken
	 * @param openid
	 * @return
	 */
	public static JSONObject getUserInfo(String accessToken, String openid) {
		String url = "https://api.weixin.qq.com/sns/userinfo?access_token="
				+ accessToken + "&openid=" + openid + "";
		String result = HttpUtil.queryStringForGet(url);
		if (ValidatorHelper.isEmpty(result)) {
			logger.info("#WeChatHelper# getUserInfo() request is error = "
					+ result);
			return null;
		}

		JSONObject jsonObject = JSON.parseObject(result);
		/**
		 * 判断是否失败
		 */
		if (ValidatorHelper.isNotEmpty(jsonObject.get("errcode"))) {
			logger.info("#WeChatHelper# getUserInfo() return is error = "
					+ jsonObject.toJSONString());
			return null;
		}

		return jsonObject;
	}

	// public static JSONObject getUserInfo(String accessToken,String openid){
	//		
	// }

	public static void main(String[] args) throws UnsupportedEncodingException {
		 String result =
		 "{\"access_token\":\"ACCESS_TOKEN\", \"expires_in\":7200, }";
				
		 JSONObject jsonObject = JSON.parseObject(result);
		 System.out.println(jsonObject.getString("access_token").isEmpty());
		 
		 if(ValidatorHelper.isNotEmpty(jsonObject.getString("access_token"))){
			 System.out.println(11);
		 }
		 
		 
		//JSONObject accessObject = getAccessToken("021e41pn0oHtje1PF6on0L53pn0e41pP");
		//{"scope":"snsapi_userinfo","unionid":"oX6vqwQCPYgFFGpt14sQ_fkA-N-0","openid":"oSevowGDL8VbgZtWlmAji_sgitWM","expires_in":7200,"refresh_token":"jMuwPafNUgxO2UxL4UXukPNpVrdAIgtO0lMwp3Nhtdrmiyw-K3OgHoJD8-0eODv-Q5jEPP_Gaqej5k9lWBLm07VpMwTKjOLbHyieddQoClM","access_token":"YBmI8xuqBAfoqILueaGbFv7HCqKark1A22BsFuhzzC4Q1M0vVY9uIhVES_1cBxl9bJYjfd2_fk6pVFBYuoDF-01JPM9eU59Tt6AoWgMQr-8"}
		//System.out.println(userObject);
		
//		JSONObject userObject = getUserInfo("YBmI8xuqBAfoqILueaGbFv7HCqKark1A22BsFuhzzC4Q1M0vVY9uIhVES_1cBxl9bJYjfd2_fk6pVFBYuoDF-01JPM9eU59Tt6AoWgMQr-8", "oSevowGDL8VbgZtWlmAji_sgitWM");
//		
//		String name = userObject.getString("nickname");
//		String s = new String(name.getBytes("ISO-8859-1"),"gbk");
//		System.out.println(userObject);
//		System.out.println(s);

	}

}
