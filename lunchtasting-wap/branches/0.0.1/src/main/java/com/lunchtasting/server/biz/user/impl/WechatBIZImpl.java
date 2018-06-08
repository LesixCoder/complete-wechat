package com.lunchtasting.server.biz.user.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.biz.user.WechatBIZ;
import com.lunchtasting.server.dao.user.UserDAO;
import com.lunchtasting.server.dao.user.UserWeChatDAO;
import com.lunchtasting.server.helper.WebchatHelper;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.po.lt.UserWeChat;

@Service
public class WechatBIZImpl implements WechatBIZ{
	@Autowired
	private UserWeChatDAO userWeChatDAO;
	@Autowired
	private UserDAO userDAO;
	@Transactional
	@Override
	public HashMap webchatLogin(String code) throws Exception {
		User user=null;
		UserWeChat userWeChat=null;
		JSONObject openJson = WebchatHelper.getWebchatOnpenID(code);
		if(openJson==null){
			return null;
		}
		
		String ACCESS_TOKEN = (String)openJson.get("access_token");
		String OPENID = (String)openJson.get("openid");
		/**
		 * 得到用户信息。
		 */
		JSONObject jsonUser = WebchatHelper.getWebchatUser(ACCESS_TOKEN, OPENID);
		if(jsonUser==null){
			return null;
		}
		UserWeChat chat =userWeChatDAO.getByOpenId(OPENID);
		if(chat==null || chat.getId()==0 || chat.getId()==null){
			/**
			 * 新建用户
			 */
			user = getUser(jsonUser);
			user.setSource(3);//3微信登录注册
			userDAO.create(user);
			if(user.getId()==null||user.getId()==0){
				return null;
			}
			/**
			 * 新建微信授权表
			 */
			Long userId=user.getId();
			userWeChat= getUserWeChat(userId, openJson);
			userWeChatDAO.create(userWeChat);
		}else{
			/**
			 * 更新用户信息
			 */
			user = getUser(jsonUser);
			user.setId(chat.getUserId());
			userDAO.updateUserResult(user); //修改方法
			/**
			 * 更新wechat认证信息
			 */
			userWeChat= getUserWeChat(chat.getUserId(),openJson);
			Integer ttt =userWeChatDAO.updateWeChat(userWeChat);
			if(ttt==0 || ttt==null){
				throw new Exception();
			}
		}
		HashMap nicemap = new HashMap();
		nicemap.put("user",user);
		nicemap.put("userWeChat",userWeChat);
		return nicemap;
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
	/**
	 * 封装user数据
	 */
	public User getUser(JSONObject jsonUser){
		User wechat = new User();
		String openid =(String)jsonUser.get("openid");
		String nickname =(String)jsonUser.get("nickname");
		String sexName =(String)jsonUser.get("sex");
		int sex =0;
		if(sexName.equals("1")){
			sex=1;
		}else if(sexName.equals("2")){
			sex=2;
		}
		String province =(String)jsonUser.get("province");
		String city =(String)jsonUser.get("city");
		String country =(String)jsonUser.get("country");
		String headimgurl =(String)jsonUser.get("headimgurl");
		String unionid = null;
		if(jsonUser.get("unionid")!=null){
			unionid=(String)jsonUser.get("unionid");
		}
		wechat.setName(nickname);
		wechat.setSex(sex);
		wechat.setImgUrl(headimgurl);
		return wechat;
	}
}
