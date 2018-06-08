package com.lunchtasting.server.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.biz.user.UserWeChatBIZ;
import com.lunchtasting.server.biz.user.UserBIZ;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.WeChatHelper;
import com.lunchtasting.server.po.lt.UserWeChat;
import com.lunchtasting.server.util.HttpUtil;
import com.lunchtasting.server.util.PropertiesLoader;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 微信模块
 * @author xq
 */
@Controller
@RequestMapping("/user/weChat")
public class WeChatController {
	
	@Autowired
	private UserBIZ usersInfoBIZ;
	@Autowired
	private UserWeChatBIZ userWeChatBIZ;
	
//	/**
//	 * 微信登录参数初始化
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
//	
//	@RequestMapping(value = "/init" ,method=RequestMethod.POST)
//	@ResponseBody
//	public Object weChatInit(HttpServletRequest request) throws Exception {
//        PropertiesLoader loader = new PropertiesLoader("application.properties");
//        
//		Map dataMap = new HashedMap();
//		dataMap.put("appId", loader.getProperty("WeChat.APP_ID"));
//		dataMap.put("secret", loader.getProperty("WeChat.SECRET"));
//		
//		return MapResult.build(MapResult.CODE_SUCCESS, "成功！", dataMap, request);
//	}
	
	
	/**
	 * 微信登录
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login" ,method=RequestMethod.POST)
	@ResponseBody
	public Object WeChatLogin(HttpServletRequest request) throws Exception {
		String code = request.getParameter("code");
		if(ValidatorHelper.isEmpty(code)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			/**
			 * 通过code获取access_token
			 */
			JSONObject atObject = WeChatHelper.getAccessToken(code);
			if(atObject == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, "微信登录失败！", request);
			}
			
			/**
			 * 判断用户微信是否登录过
			 */
			UserWeChat userWeChat = userWeChatBIZ.getByOpenId(atObject.getString("openid"));
			JSONObject userObject = null;
			Long userId = null;
			if(userWeChat == null){
				userObject = WeChatHelper.getUserInfo(atObject.getString("access_token"), atObject.getString("openid"));
				if(userObject == null){
					return MapResult.build(MapResult.CODE_PARAM_ERROR, "微信登录失败！", request);
				}
			}else{
				userId = userWeChat.getUserId();
			}
			
			/**
			 * 登录
			 */
			Map dataMap = usersInfoBIZ.loginWeChat(atObject, userObject,userId, request);
			if(dataMap != null){
				return MapResult.build(MapResult.CODE_SUCCESS, "成功！", dataMap, request);
			}

		} catch (Exception e) {
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
	
}
