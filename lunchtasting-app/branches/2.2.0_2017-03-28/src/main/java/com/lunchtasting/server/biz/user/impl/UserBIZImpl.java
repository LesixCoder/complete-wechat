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
import com.lunchtasting.server.biz.user.UserBIZ;
import com.lunchtasting.server.biz.youmi.YoumiIosBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.user.UserDAO;
import com.lunchtasting.server.dao.user.UserDeviceDAO;
import com.lunchtasting.server.dao.user.UserSmsDAO;
import com.lunchtasting.server.dao.user.UserWeChatDAO;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.RequestAuthHelper;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.po.lt.UserDevice;
import com.lunchtasting.server.po.lt.UserWeChat;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Transactional(rollbackFor = Throwable.class)
@Service
public class UserBIZImpl implements UserBIZ {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserDeviceDAO userDeviceDAO;
	@Autowired
	private UserSmsDAO userSmsDAO;
	@Autowired
	private UserWeChatDAO userWeChatDAO;
	@Autowired
	private YoumiIosBIZ youmiIosBIZ;
	@Override
	public User getById(Long userId) {
		return userDAO.getById(userId);
	}

	@Override
	public Boolean checkRegisterPhone(String phone) {
		Long userId = userDAO.getUserIdByPhone(phone);
		if(ValidatorHelper.isEmpty(userId)){
			return false;
		}
		return true;
	}

	@Override
	public User getByPhoneAndPwd(String phone, String pwd) {
		return userDAO.getByPhoneAndPwd(phone, pwd);
	}
	
	@Override
	public User getByAccountAndPwd(String account, String pwd) {
		return userDAO.getByAccountAndPwd(account, pwd);
	}

	@Override
	public Map registerUser(String phone, String password,String deviceToken, Long smsId,
				HttpServletRequest request)
			throws Exception {
		
		/**
		 * 创建用户信息
		 */
		User user = new User();
		long id = IdWorker.getId();
		user.setId(id);
		user.setPhone(phone);
		user.setPassword(password);
		user.setIdentity(StateEnum.USER_IDENTITY_ORDINARY.getValue());
		
		String nameId = ""+id;
		user.setName("PERFIT"+nameId.substring(nameId.length()-4,nameId.length()));
		
		user.setImgUrl(QiNiuStorageHelper.DEFAULT_USER_IMG);
		user.setSignature("这家伙很懒，什么都没留下！");
		userDAO.create(user);
		if(ValidatorHelper.isEmpty(user.getId())){
			return null;
		}
		
		/**
		 * 创建设备信息
		 */
		String authId = EncryptAuth.encode(user.getId());
		String accessToken = RequestAuthHelper.getAccessToken(user.getId());
		UserDevice device = getUserDevice(user.getId(),authId,accessToken,request);
		device.setId(IdWorker.getId());
		device.setDeviceToken(deviceToken);
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
		String authCode = RequestAuthHelper.getLoginAuthCode(request,authId, VariableConfig.AUTH_TOKEN, accessToken);
		Map map = new HashMap();
		map.put("auth_code", authCode);
		map.put("auth_id", authId);
		map.put("user_id", user.getId());
		return map;
	}
	
	@Override
	public Map login(Long userId,String deviceToken,HttpServletRequest request) throws Exception{
		
		/**
		 * 修改登录时间
		 */
		userDAO.updateLoginTime(userId);
		
		/**
		 * 修改设备信息
		 */
		String authId = EncryptAuth.encode(userId);
		String accessToken = RequestAuthHelper.getAccessToken(userId);
		String authCode = RequestAuthHelper.getLoginAuthCode(request, authId,VariableConfig.AUTH_TOKEN, accessToken);
		UserDevice device = getUserDevice(userId,authId,accessToken, request);
		device.setDeviceToken(deviceToken);
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
		map.put("user_id", userId);
		return map;
	}
	
	@Override
	public Map loginWeChat(JSONObject atObject,
			JSONObject userObject,Long userId,String deviceToken, HttpServletRequest request) throws Exception {
		
		/**
		 * 判断用户是否微信登录过
		 * 没登录 注册新用户
		 * 登录过  修改设备信息和微信授权信息
		 */
		String authId = "";
		String authCode = "";
		if(userId == null){
			
			/**
			 * 注册用户
			 */
			User user = new User();
			if(ValidatorHelper.isNotEmpty(userObject.getString("nickname"))){
				String name = new String(userObject.getString("nickname").getBytes("ISO8859-1"), "UTF-8");
				user.setName(name);
			}
			if(ValidatorHelper.isNotEmpty(userObject.getInteger("sex"))){
				user.setSex(userObject.getInteger("sex"));
			}
//			if(ValidatorHelper.isNotEmpty(userObject.getString("province"))){
//				user.setProvince(userObject.getString("province"));
//			}
//			if(ValidatorHelper.isNotEmpty(userObject.getString("city"))){
//				user.setCity(userObject.getString("city"));
//			}
//			if(ValidatorHelper.isNotEmpty(userObject.getString("country"))){
//				user.setCountry(userObject.getString("country"));
//			}
			if(ValidatorHelper.isNotEmpty(userObject.getString("headimgurl"))){
				String imgName = QiNiuStorageHelper.getQiNiuImgName(QiNiuStorageHelper.USER_IMG_PREFIX);
				boolean result = QiNiuStorageHelper.urlUpload(imgName, userObject.getString("headimgurl"));
				if(result){
					user.setImgUrl(imgName);
				}
			}else{
				user.setImgUrl(QiNiuStorageHelper.DEFAULT_USER_IMG);
			}
			user.setIdentity(StateEnum.USER_IDENTITY_ORDINARY.getValue());
			user.setSignature("这家伙很懒，什么都没留下！");
			user.setId(IdWorker.getId());
			userDAO.create(user);
			if(ValidatorHelper.isEmpty(user.getId())){
				return null;
			}
			userId = user.getId();
			
			/**
			 * 保存微信授权信息
			 */
			UserWeChat weChat = getUserWeChat(userId,atObject);
			userWeChatDAO.create(weChat);
			if(ValidatorHelper.isEmpty(weChat.getId())){
				throw new Exception();
			}
			
			/**
			 * 创建设备信息
			 */
			String accessToken = RequestAuthHelper.getAccessToken(userId);
			authId = EncryptAuth.encode(userId);
			authCode = RequestAuthHelper.getLoginAuthCode(request,authId, VariableConfig.AUTH_TOKEN, accessToken);
			UserDevice device = getUserDevice(userId,authId,accessToken,request);
			device.setId(IdWorker.getId());
			device.setDeviceToken(deviceToken);
			userDeviceDAO.create(device);
			if(ValidatorHelper.isEmpty(device.getId())){
				throw new Exception();
			}
			/**
			 * 有米ios
			 */
			if(ValidatorHelper.isNotEmpty(request.getHeader("idfa"))){
				youmiIosBIZ.succeed(request.getHeader("idfa"));
			}
		}else{
			
			/**
			 * 修改登录时间
			 */
			userDAO.updateLoginTime(userId);
			
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
			authId = EncryptAuth.encode(userId);
			authCode = RequestAuthHelper.getLoginAuthCode(request, authId,VariableConfig.AUTH_TOKEN, accessToken);
			UserDevice device = getUserDevice(userId,authId,accessToken, request);
			device.setDeviceToken(deviceToken);
			int result2 = userDeviceDAO.updateDevice(device);
			if(result2 == 0){
				throw new Exception();
			}
		}
		
		Map map = new HashMap();
		map.put("auth_code", authCode);
		map.put("auth_id", authId);
		map.put("user_id", userId);
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
//		device.setDeviceToken(request.getHeader("device_token"));
		device.setChannel(request.getHeader("channel"));
		device.setImei(request.getHeader("imei"));
		device.setAppVersion(request.getHeader("appVersion"));
		/**
		 * iOS专用
		 */
		if(ValidatorHelper.isNotEmpty(request.getHeader("appid"))){
			device.setAppid(request.getHeader("appid"));
		}
		if(ValidatorHelper.isNotEmpty(request.getHeader("idfa"))){
			device.setIdfa(request.getHeader("idfa"));
		}
		
		
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
		weChat.setId(IdWorker.getId());
		weChat.setUserId(userId);
		weChat.setAccessToken(atObject.getString("access_token"));
		weChat.setExpiresIn(atObject.getInteger("expires_in"));
		weChat.setRefreshToken(atObject.getString("refresh_token"));
		weChat.setOpenId(atObject.getString("openid"));
		weChat.setScope(atObject.getString("scope"));
		return weChat;
	}

	@Override
	public Long getUserIdByPhone(String phone) {
		return userDAO.getUserIdByPhone(phone);
	}

	@Override
	public Boolean updatePwd(Long userId, String password, Long codeId)
			throws Exception {
		
		/**
		 * 修改用户密码
		 */
		int result = userDAO.updatePwd(userId, password);
		if(result < 0){
			return false;
		}
		
		userSmsDAO.updateCodeExpire(codeId);
		return true;
	}

	@Override
	public Boolean updateUserInfo(Long userId,String name,String imgUrl
			,String sex,String birth,String profession,String feeling,String signature) {
		
		Integer intSex= null;
		if(ValidatorHelper.isNumber(sex)){
			intSex = Integer.parseInt(sex);
		}
		
		int result = userDAO.updateUserInfo(userId, name, imgUrl, intSex, 
					birth, profession, feeling, signature);
		if(result > 0){
			return true;
		}
		return false;
	}

	@Override
	public void createSuggest(Long userId, String content) {
		userDAO.createSuggest(IdWorker.getId(),userId, content);
	}

	@Override
	public Map getUserDetail(Long userId) {
		Map map = userDAO.getUserDetail(userId);
		if(map != null){
			
			/**
			 * 图片
			 */
			String imgUrl = map.get("img_url").toString();
			if(ValidatorHelper.isNotEmpty(imgUrl)){
				map.put("img_url", SysConfig.IMG_VISIT_URL + imgUrl
						+ QiNiuStorageHelper.MODEL0+"w/200/h/200");
			}else{
				map.put("img_url", VariableConfig.USRE_DEFAULT_IMG);
			}
		}
		return map;
	}

	@Override
	public Map getEditInfo(Long userId) {
		Map map = userDAO.getEditInfo(userId);
		if(map != null){
			
			/**
			 * 图片
			 */
			String imgUrl = map.get("img_url").toString();
			if(ValidatorHelper.isNotEmpty(imgUrl)){
				map.put("img_url", SysConfig.IMG_VISIT_URL + imgUrl
						+ QiNiuStorageHelper.MODEL0+"w/300/h/300");
			}else{
				map.put("img_url", VariableConfig.USRE_DEFAULT_IMG);
			}
		}
		return map;
	}


}
