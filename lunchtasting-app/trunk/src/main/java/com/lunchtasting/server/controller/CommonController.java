package com.lunchtasting.server.controller;

import java.nio.Buffer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.Base64;

/**
 * 通用模块
 * Created on 2016-11-23
 * @author xuqian
 *
 */
@Controller
@RequestMapping("/common")
public class CommonController {
	
	/** 
	 * 七牛获得上传图片权限
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/qiniu/getToken" , method=RequestMethod.POST)
	@ResponseBody
	public Object qiniuGetToken(HttpServletRequest request) throws Exception {
		try {
			Map dataMap = new HashMap();
			dataMap.put("token", QiNiuStorageHelper.getUpToken());
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
//	@RequestMapping(value = "/qcloud/ugsb/getSign")
//	@ResponseBody
//	public Object qcloudUgsbGetSign(HttpServletRequest request) throws Exception {
//		
//		try {
//			
//			String secretId = "AKIDCHC009RNvMLoLjs54xirvpaBbtIaliid";
//			String secretKey = "51mR7go4BAARA9JKE0dDjL1ZQOHD5qXZ"; 
//			
//			long current = (new Date()).getTime() / 1000;
//			long expired  = current + 86400l;
//			long random = Math.round(Math.random() * Math.pow(2, 32));
//			
//			String original = "secretId="+secretId+"&currentTimeStamp="+current+"" +
//					"&expireTime="+expired+"&random="+random;
//			
////			Buffer originalBuffer = new Buffer(original, "utf8");
//			
//			byte [] signTmp = HMACSHA1.HmacSHA1Encrypt(secretKey, original);
//			
//			//String sign = Base64.
//			
//			
//			
//			
//			Map dataMap = new HashMap();
//			dataMap.put("token", QiNiuStorageHelper.getUpToken());
//			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
//		}
//	}
}
