package com.lunchtasting.server.helper;

import java.util.HashMap;

import org.apache.cassandra.cli.CliParser.newColumnFamily_return;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;




public class WebchatHelper {
	/**
	 * 得到OpenId和密钥
	 * @param code
	 * @return
	 */
	public static 	JSONObject getWebchatOnpenID(String code){
		HttpsClient client =  new  HttpsClient();
		String appSecret=KeyStaiticCommonHelper.appSecret;
		String appid=KeyStaiticCommonHelper.appid;
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
				"appid=" + appid +
				"&secret=" + appSecret +
				"&code=" + code +
				"&grant_type=authorization_code";
		String result = client.call(url);
		if(result.length()==0||result==null||result.equals("")){
			return null;
		}
		JSONObject jsonObject = JSON.parseObject(result);
		if(jsonObject.getString("access_token")==null && jsonObject.getString("openid")==null){
			return null;
		}
		return jsonObject;
	}
	
		
	/**
	 * 得到用户信息
	 * @param code
	 * @return
	 */
	public static 	JSONObject getWebchatUser (String ACCESS_TOKEN,String OPENID){
		HttpsClient client =  new  HttpsClient();
		String appSecret=KeyStaiticCommonHelper.appSecret;
		String appid=KeyStaiticCommonHelper.appid;
		String url = "https://api.weixin.qq.com/sns/userinfo?" +
				"access_token="+ACCESS_TOKEN +
				"&openid="+OPENID +
				"&lang=zh_CN";
		String str = client.call(url);
		if(str.length()==0 || str==null || str.equals("")){
			return null;
		}
		JSONObject jsonObject = JSON.parseObject(str);
		return jsonObject;
	}
}
