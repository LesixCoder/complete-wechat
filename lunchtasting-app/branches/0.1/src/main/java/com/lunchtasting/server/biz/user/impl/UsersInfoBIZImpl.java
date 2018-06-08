package com.lunchtasting.server.biz.user.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.mortbay.util.UrlEncoded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.biz.user.UsersInfoBIZ;
import com.lunchtasting.server.dao.user.UserDeviceDAO;
import com.lunchtasting.server.dao.user.UserSmsDAO;
import com.lunchtasting.server.dao.user.UserWeChatDAO;
import com.lunchtasting.server.dao.user.UsersInfoDAO;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.RequestAuthHelper;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.po.lt.UserDevice;
import com.lunchtasting.server.po.lt.UserWeChat;
import com.lunchtasting.server.po.lt.UsersInfo;
import com.lunchtasting.server.util.ValidatorHelper;

@Transactional(rollbackFor = Throwable.class)
@Service
public class UsersInfoBIZImpl implements UsersInfoBIZ {

	@Autowired
	private UsersInfoDAO usersInfoDAO;
	@Autowired
	private UserDeviceDAO userDeviceDAO;
	@Autowired
	private UserSmsDAO userSmsDAO;
	@Autowired
	private UserWeChatDAO userWeChatDAO;

	@Override
	public Boolean checkRegisterPhone(String phone) {
		String checkPhone = usersInfoDAO.getByPhone(phone);
		if(StringUtils.isEmpty(checkPhone)){
			return false;
		}
		return true;
	}

	@Override
	public UsersInfo getByPhoneAndPwd(String phone, String pwd) {
		return usersInfoDAO.getByPhoneAndPwd(phone, pwd);
	}

	@Override
	public Map registerUser(String phone, String password, Long smsId,
				HttpServletRequest request)
			throws Exception {
		
		/**
		 * 创建用户信息
		 */
		UsersInfo user = new UsersInfo();
		user.setTelephone(phone);
		user.setUserName(phone);
		user.setPassword(password);
		usersInfoDAO.create(user);
		if(ValidatorHelper.isEmpty(user.getUserId())){
			return null;
		}
		
		/**
		 * 创建设备信息
		 */
		String authId = EncryptAuth.encode(user.getUserId());
		String accessToken = RequestAuthHelper.getAccessToken(user.getId());
		UserDevice device = getUserDevice(user.getId(),authId,accessToken,request);
		userDeviceDAO.create(device);
		if(ValidatorHelper.isEmpty(device.getId())){
			throw new Exception();
		}
		
		/**
		 * 修改验证码已失效
		 */
		userSmsDAO.updateCodeExpire(smsId);
		
		/**
		 * 结果返回
		 */
		String authCode = RequestAuthHelper.getAuthCode(request, VariableConfig.AUTH_TOKEN, accessToken);
		Map map = new HashMap();
		map.put("auth_code", authCode);
		map.put("auth_id", authId);
		return map;
	}
	
	@Override
	public Map login(Long userId, HttpServletRequest request) throws Exception{
		
		/**
		 * 修改登录时间
		 */
		
		/**
		 * 修改设备信息
		 */
		String authId = EncryptAuth.encode(userId);
		String accessToken = RequestAuthHelper.getAccessToken(userId);
		String authCode = RequestAuthHelper.getAuthCode(request, VariableConfig.AUTH_TOKEN, accessToken);
		UserDevice device = getUserDevice(userId,authId,accessToken, request);
		int result = userDeviceDAO.updateDevice(device);
		if(result == 0){
			throw new Exception();
		}
		
		/**
		 * 结果返回
		 */
		Map map = new HashMap();
		map.put("auth_code", authCode);
		map.put("auth_id", authId);
		return map;
	}

	@Override
	public Map loginWeChat(JSONObject atObject,
			JSONObject userObject,Long userId, HttpServletRequest request) throws Exception {
		
		/**
		 * 判断用户是否微信登录过
		 * 没登录 注册新用户
		 * 登录过  修改设备信息和微信授权信息
		 */
		String authId = "";
		String authCode = "";
		if(userObject == null){
			
			/**
			 * 注册用户
			 */
			UsersInfo user = new UsersInfo();
			if(ValidatorHelper.isNotEmpty(userObject.getString("nickname"))){
				String name = UrlEncoded.encodeString(userObject.getString("nickname"), "utf-8");
				user.setNickName(name);
				user.setUserName(name);
			}
			if(ValidatorHelper.isNotEmpty(userObject.getInteger("sex"))){
				user.setSex(userObject.getInteger("sex"));
			}
			if(ValidatorHelper.isNotEmpty(userObject.getString("province"))){
				user.setProvince(userObject.getString("province"));
			}
			if(ValidatorHelper.isNotEmpty(userObject.getString("city"))){
				user.setCity(userObject.getString("city"));
			}
			if(ValidatorHelper.isNotEmpty(userObject.getString("country"))){
				user.setCountry(userObject.getString("country"));
			}
			if(ValidatorHelper.isNotEmpty(userObject.getString("headimgurl"))){
				user.setHeadImgUrl(userObject.getString("headimgurl"));
			}
			
			usersInfoDAO.create(user);
			if(ValidatorHelper.isEmpty(user.getUserId())){
				return null;
			}
			
			/**
			 * 保存微信授权信息
			 */
			UserWeChat weChat = getUserWeChat(user.getId(),atObject);
			userWeChatDAO.create(weChat);
			if(ValidatorHelper.isEmpty(weChat.getId())){
				throw new Exception();
			}
			
			/**
			 * 创建设备信息
			 */
			String accessToken = RequestAuthHelper.getAccessToken(user.getId());
			authId = EncryptAuth.encode(user.getUserId());
			authCode = RequestAuthHelper.getAuthCode(request, VariableConfig.AUTH_TOKEN, accessToken);
			UserDevice device = getUserDevice(user.getId(),authId,accessToken,request);
			userDeviceDAO.create(device);
			if(ValidatorHelper.isEmpty(device.getId())){
				throw new Exception();
			}
			
		}else{
			
			/**
			 * 更新信息
			 */
			UserWeChat weChat = getUserWeChat(userId, atObject);
			int result = userWeChatDAO.updateWeChat(weChat);
			if(result == 0){
				return null;
			}
			
			/**
			 * 修改设备信息
			 */
			String accessToken = RequestAuthHelper.getAccessToken(userId);
			authCode = RequestAuthHelper.getAuthCode(request, VariableConfig.AUTH_TOKEN, accessToken);
			authId = EncryptAuth.encode(userId);
			UserDevice device = getUserDevice(userId,authId,accessToken, request);
			int result2 = userDeviceDAO.updateDevice(device);
			if(result2 == 0){
				throw new Exception();
			}
		}
		
		Map map = new HashMap();
		map.put("auth_code", authCode);
		map.put("auth_id", authId);
		return map;
	}
	
	/**
	 * 获得设备实体
	 * @param userId
	 * @param authId
	 * @param accessToken
	 * @param request
	 * @return
	 */
	private UserDevice getUserDevice(Long userId,String authId,String accessToken,HttpServletRequest request){
		UserDevice device = new UserDevice();
		device.setUserId(userId);
		device.setAuthId(authId);
		device.setAccessToken(accessToken);
		device.setSystemType(request.getHeader("systemType"));
		device.setSystemVersion(request.getHeader("systemVersion"));
		device.setScreenWidth(Integer.parseInt(request.getHeader("screenWidth").toString()));
		device.setScreenHeight(Integer.parseInt(request.getHeader("screenHeight").toString()));
		device.setPlatform(request.getHeader("platform").toString());
		device.setDeviceToken(request.getHeader("devicetoken"));
		device.setChannel(request.getHeader("channel"));
		device.setImei(request.getHeader("imei"));
		device.setAppVersion(request.getHeader("appVersion"));
		return device;
	}
	
	/**
	 * 获得微信授权实体
	 * @param userId
	 * @param atObject
	 * @return
	 */
	private UserWeChat getUserWeChat(Long userId,JSONObject atObject){
		UserWeChat weChat = new UserWeChat();
		weChat.setUserId(userId);
		weChat.setAccessToken(atObject.getString("access_token"));
		weChat.setExpiresIn(atObject.getInteger("expires_in"));
		weChat.setRefreshToken(atObject.getString("refresh_token"));
		weChat.setOpenId(atObject.getString("openid"));
		weChat.setScope(atObject.getString("scope"));
		return weChat;
	}

}
