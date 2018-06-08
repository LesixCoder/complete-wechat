package com.lunchtasting.server.biz.user.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.biz.user.WeChatLoginBIZ;
import com.lunchtasting.server.dao.user.TemporaryUserWeChatDao;
import com.lunchtasting.server.dao.user.UserDAO;
import com.lunchtasting.server.dao.user.UserWeChatDAO;
import com.lunchtasting.server.helper.WebchatHelper;
import com.lunchtasting.server.po.lt.TemporaryUserWeChat;
import com.lunchtasting.server.po.lt.UserWeChat;
import com.lunchtasting.server.util.IdWorker;
@Service
public class WeChatLoginBIZImpl implements WeChatLoginBIZ{
	@Autowired
	private UserWeChatDAO userWeChatDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TemporaryUserWeChatDao temporaryUserWeChatDAO;
	@Override
	public HashMap webchatLogin(String code) throws Exception {

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
		TemporaryUserWeChat temporaryUserWeChat =temporaryUserWeChatDAO.getByOpenId(OPENID);
		if(temporaryUserWeChat==null || temporaryUserWeChat.getId()==0 || temporaryUserWeChat.getId()==null){
			//新建
			temporaryUserWeChat = getUser(jsonUser);
			temporaryUserWeChat.setId(IdWorker.getId());
			try {
				temporaryUserWeChatDAO.addTemp(temporaryUserWeChat);
			} catch (Exception e) {
				temporaryUserWeChat.setNickName("");
				temporaryUserWeChatDAO.addTemp(temporaryUserWeChat);
			}
			
		}else{
			Long id = temporaryUserWeChat.getId();
			String outTradeNo = temporaryUserWeChat.getOutTradeNo();
			temporaryUserWeChat = getUser(jsonUser);
			temporaryUserWeChat.setId(id);
			temporaryUserWeChat.setOutTradeNo(outTradeNo);
			//更新
			try {
				temporaryUserWeChatDAO.updateTemp(temporaryUserWeChat);
			} catch (Exception e) {
				// TODO: handle exception
				temporaryUserWeChat.setNickName("");
				temporaryUserWeChatDAO.updateTemp(temporaryUserWeChat);
			}
			
		}
		HashMap nicemap = new HashMap();
		nicemap.put("user",temporaryUserWeChat);
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
	public TemporaryUserWeChat getUser(JSONObject jsonUser){
		TemporaryUserWeChat wechat = new TemporaryUserWeChat();
		String openid =(String)jsonUser.get("openid");
		String nickname =(String)jsonUser.get("nickname");
		if(jsonUser.get("sex") != null){
			wechat.setSex((Integer)jsonUser.get("sex"));
		}else{
			wechat.setSex(0);
		}
		String province =(String)jsonUser.get("province");
		String city =(String)jsonUser.get("city");
		String country =(String)jsonUser.get("country");
		String headimgurl =(String)jsonUser.get("headimgurl");
		String unionid = null;
		if(jsonUser.get("unionid")!=null){
			unionid=(String)jsonUser.get("unionid");
		}
		wechat.setCity(city);
		wechat.setHeadImgUrl(headimgurl);
		wechat.setProvince(province);
		wechat.setNickName(nickname);
		wechat.setOpenId(openid);
		return wechat;
	}
}
