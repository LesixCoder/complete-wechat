package com.lunchtasting.server.biz.user.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.mortbay.util.UrlEncoded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.biz.user.UserBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.user.UserDAO;
import com.lunchtasting.server.dao.user.UserSmsDAO;
import com.lunchtasting.server.dao.user.UserWeChatDAO;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.helper.WeChatHelper;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.po.lt.UserDevice;
import com.lunchtasting.server.po.lt.UserWeChat;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

@Transactional(rollbackFor = Throwable.class)
@Service
public class UserBIZImpl implements UserBIZ {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserSmsDAO userSmsDAO;
	@Autowired
	private UserWeChatDAO userWeChatDAO;
	@Override
	public User getById(Long userId) {
		return userDAO.getById(userId);
	}
	
	@Override
	public Map loginWeChat(JSONObject atObject,JSONObject userObject,Long userId
			, HttpServletRequest request) throws Exception{
		
		/**
		 * 判断用户是否微信登录过
		 * 没登录 注册新用户
		 * 登录过  修改设备信息和微信授权信息
		 */
		if(userId == null){
			
			/**
			 * 注册用户
			 */
			User user = new User();
			if(ValidatorHelper.isNotEmpty(userObject.getString("nickname"))){
				String name = new String(userObject.getString("nickname").getBytes("ISO8859-1"), "UTF-8");
				user.setName(CommonHelper.filterEmoji(name));
			}
			if(ValidatorHelper.isNotEmpty(userObject.getInteger("sex"))){
				user.setSex(userObject.getInteger("sex"));
			}
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
			userId = user.getId();
			
			/**
			 * 保存微信授权信息
			 */
			UserWeChat weChat = getUserWeChat(userId,atObject);
			userWeChatDAO.create(weChat);
			
			/**
			 * 保存设备信息（app登录需要，其他信息默认为空）
			 */
			userDAO.createUserDevice(IdWorker.getId(), userId);
			
		}else{

			/**
			 * 修改登录时间
			 */
			userDAO.updateLoginTime(userId);
			
			/**
			 * 更新信息
			 */
			UserWeChat weChat = getUserWeChat(userId, atObject);
			userWeChatDAO.updateWeChat(weChat);
//			if(result == 0){
//				return null;
//			}
		}
		HashMap map = new HashMap();
		map.put("userId",userId);
		return map;
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
		weChat.setUnionId(atObject.getString("unionid"));
		weChat.setScope(atObject.getString("scope"));
		weChat.setType(2);
		return weChat;
	}
	
//	@Override
//	public Map loginWeChat(JSONObject atObject,
//			JSONObject userObject,Long userId,String deviceToken, HttpServletRequest request) throws Exception {
//		
//		/**
//		 * 判断用户是否微信登录过
//		 * 没登录 注册新用户
//		 * 登录过  修改设备信息和微信授权信息
//		 */
//		String authId = "";
//		String authCode = "";
//		if(userId == null){
//			
//			/**
//			 * 注册用户
//			 */
//			User user = new User();
//			if(ValidatorHelper.isNotEmpty(userObject.getString("nickname"))){
//				String name = new String(userObject.getString("nickname").getBytes("ISO8859-1"), "UTF-8");
//				user.setName(name);
//			}
//			if(ValidatorHelper.isNotEmpty(userObject.getInteger("sex"))){
//				user.setSex(userObject.getInteger("sex"));
//			}
////			if(ValidatorHelper.isNotEmpty(userObject.getString("province"))){
////				user.setProvince(userObject.getString("province"));
////			}
////			if(ValidatorHelper.isNotEmpty(userObject.getString("city"))){
////				user.setCity(userObject.getString("city"));
////			}
////			if(ValidatorHelper.isNotEmpty(userObject.getString("country"))){
////				user.setCountry(userObject.getString("country"));
////			}
//			if(ValidatorHelper.isNotEmpty(userObject.getString("headimgurl"))){
//				String imgName = QiNiuStorageHelper.getQiNiuImgName(QiNiuStorageHelper.USER_IMG_PREFIX);
//				boolean result = QiNiuStorageHelper.urlUpload(imgName, userObject.getString("headimgurl"));
//				if(result){
//					user.setImgUrl(imgName);
//				}
//			}else{
//				user.setImgUrl(QiNiuStorageHelper.DEFAULT_USER_IMG);
//			}
//			user.setIdentity(StateEnum.USER_IDENTITY_ORDINARY.getValue());
//			user.setSignature("这家伙很懒，什么都没留下！");
//			user.setId(IdWorker.getId());
//			userDAO.create(user);
//			if(ValidatorHelper.isEmpty(user.getId())){
//				return null;
//			}
//			userId = user.getId();
//			
//			/**
//			 * 保存微信授权信息
//			 */
//			UserWeChat weChat = getUserWeChat(userId,atObject);
//			userWeChatDAO.create(weChat);
//			if(ValidatorHelper.isEmpty(weChat.getId())){
//				throw new Exception();
//			}
//			
//		}else{
//			
//			/**
//			 * 修改登录时间
//			 */
//			userDAO.updateLoginTime(userId);
//			
//			/**
//			 * 更新信息
//			 */
//			UserWeChat weChat = getUserWeChat(userId, atObject);
//			int result = userWeChatDAO.updateWeChat(weChat);
//			if(result == 0){
//				return null;
//			}
//			
//		}
//		
//		Map map = new HashMap();
//		map.put("auth_code", authCode);
//		map.put("auth_id", authId);
//		map.put("user_id", userId);
//		return map;
//	}
	
	
	@Override
	public Boolean updateUserInfo(Long userId,String name,String feeling,String signature,String hobby,String tags) {
		int result = userDAO.updateUserInfo(userId, name, feeling, signature, hobby, tags);
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
			
//			/**
//			 * 标签
//			 */
//			if(ValidatorHelper.isMapNotEmpty(map.get("tags"))){
//				String tags = map.get("tags").toString();
//				String [] spilt = tags.split(",");
//				List tagList = new ArrayList();
//				Map tagMap = null;
//				for(String tag : spilt){
//					tagMap = new HashMap();
//					tagMap.put("tag", tag);
//					tagMap.put("random", Utils.getRandomNumber(1,6));
//					tagList.add(tagMap);
//				}
//				map.put("tag_list", tagList);
//			}
//			
//			/**
//			 * 星座 年龄
//			 */
//			if(ValidatorHelper.isMapNotEmpty(map.get("birth"))){
//				String birth = map.get("birth").toString();
//				try {
//					Date date = DateUtil.convertStringTODate(birth,DateUtil.DATE_PATTERN_YYYY_MM_DD);
//					String constellation = CommonHelper.getConstellation(date.getMonth()+1, date.getDate());
//					map.put("constellation", constellation);
//					int age = CommonHelper.getAge(date);
//					map.put("age", age);
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//				
//			}
			
		}
		return map;
	}

	@Override
	public Map getEditInfo(Long userId) {
		Map map = userDAO.getEditInfo(userId);
		if(map != null){

			map.put("img_url", SysConfig.IMG_VISIT_URL + map.get("img_url")
					+ QiNiuStorageHelper.MODEL0+"w/300/h/300");
			
		}
		return map;
	}

	@Override
	public List queryUserVisitorList(Long desUserId, Integer page,
			Integer pageSize) {
		return userDAO.queryUserVisitorList(desUserId, page, pageSize);
	}

	@Override
	public List queryUserCommentList(Long userId, Integer page, Integer pageSize) {
		return userDAO.queryUserCommentList(userId, page, pageSize);
	}

	@Override
	public Integer getUserCommentCount(Long userId) {
		return userDAO.getUserCommentCount(userId);
	}

	@Override
	public Long createUserComment(Long userId, Long srcUserId,
			String content, Long parentId, Long parentSrcUserId) {
		
		long id = IdWorker.getId();
		userDAO.createUserComment(IdWorker.getId(), userId, srcUserId
				, content, parentId, parentSrcUserId);
		return id;
	}

	@Override
	public Map getMatchUser(Long userId, Long matchId) {
		return userDAO.getMatchUser(userId, matchId);
	}

	@Override
	public void updateImgUrl(Long userId, String imgUrl) {
		userDAO.updateImgUrl(userId, imgUrl);
	}

	@Override
	public void updateInviteUserId(Long userId, Long inviteUserId) {
		userDAO.updateInviteUserId(userId, inviteUserId);
	}
}
