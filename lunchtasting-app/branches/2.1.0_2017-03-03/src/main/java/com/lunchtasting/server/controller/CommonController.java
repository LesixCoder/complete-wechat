package com.lunchtasting.server.controller;

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
}
