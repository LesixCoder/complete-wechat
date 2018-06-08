package com.lunchtasting.server.helper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cassandra.cli.CliParser.newColumnFamily_return;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.config.TenpayConfig;
import com.lunchtasting.server.util.HttpUtil;
import com.lunchtasting.server.util.MD5Util;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;




public class WebchatHelper {
	
	private static String characterEncoding = "UTF-8";
	
	/**
	 * 得到OpenId和密钥
	 * @param code
	 * @return
	 */
	public static String getOpenID(String code){
		HttpsClient client =  new  HttpsClient();
		String appSecret=KeyStaiticCommonHelper.appSecret;
		String appid=KeyStaiticCommonHelper.APP_ID;
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
		return jsonObject.getString("openid");
	}
	
	public static 	JSONObject getWebchatOnpenID(String code){
		HttpsClient client =  new  HttpsClient();
		String appSecret=KeyStaiticCommonHelper.appSecret;
		String appid=KeyStaiticCommonHelper.APP_ID;
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
		String appid=KeyStaiticCommonHelper.APP_ID;
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
