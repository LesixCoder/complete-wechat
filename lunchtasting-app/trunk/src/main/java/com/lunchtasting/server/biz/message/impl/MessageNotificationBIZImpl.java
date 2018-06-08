package com.lunchtasting.server.biz.message.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.message.MessageNotificationBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.message.MessageNotificationDAO;
import com.lunchtasting.server.dao.user.UserDAO;
import com.lunchtasting.server.enumeration.SysEnum.MessageNotificatioType;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.po.lt.MessageNotification;
import com.lunchtasting.server.push.PushDataHerlper;
import com.lunchtasting.server.push.PushService;
import com.lunchtasting.server.push.impl.UMengPushServiceImpl;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class MessageNotificationBIZImpl implements MessageNotificationBIZ {

	@Autowired
	private MessageNotificationDAO notificationDAO;
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public Boolean pushMessage(Long srcUserId, Long desUserId, Long bizId,
			Integer bizType, String title, String content, String imgUrl) {
		
		/**
		 * 创建消息
		 */
		MessageNotification msg = new MessageNotification();
		msg.setId(IdWorker.getId());
		msg.setSrcUserId(srcUserId);
		msg.setDesUserId(desUserId);
		msg.setBizId(bizId);
		msg.setBizType(bizType);
		msg.setTitle(title);
		msg.setContent(content);
		msg.setImgUrl(imgUrl);
		notificationDAO.create(msg);
		
		/**
		 * 推送消息
		 */
		MessageNotificatioType msgEnum = MessageNotificatioType.getEnumByType(bizType);
		if(msgEnum != null){
			
			/**
			 * 查询要推送的用户信息
			 */
			Map userMap = userDAO.getUserMssagePush(srcUserId);
			if(userMap != null){
				int platform = Integer.parseInt(userMap.get("platform").toString());
				String deviceToken = userMap.get("device_token").toString();
				
				/**
				 * deviceToken 为空，用户打开通知
				 */
				if(ValidatorHelper.isNotEmpty(deviceToken)){
					
					/**
					 * msgType 1静默消息  2通知消息
					 */
					try {
						PushService pushService = new UMengPushServiceImpl();
						String jsonData = PushDataHerlper.getNotificationData(bizId, 
								bizType, title, content, 1, msgEnum.getMsgType());
						pushService.buildSingleNotification(deviceToken
								, platform, "通知", jsonData);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		return true;
	}

	@Override
	public Integer getNotificationCount(Long userId) {
		return notificationDAO.getNotificationCount(userId);
	}

	@Override
	public List queryNotificationList(Long userId, Integer page,
			Integer pageSize)  throws Exception {
		List list = notificationDAO.queryNotificationList(userId, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				int bizType = Integer.parseInt(map.get("biz_type").toString());
				
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL1+"w/750/h/460");
				
				/**
				 * 活动、比赛增加一个字段
				 */
				if(bizType == MessageNotificatioType.MATCH_BEGIN.getType()){
					map.put("biz_content", "赛事已经开始");
				}
				if(bizType == MessageNotificatioType.ACTIVITY_BEGIN.getType()){
					map.put("biz_content", "活动已经开始");
				}
				
				/**
				 * 时间
				 */
				Date time = DateUtil.convertStringTODate(map.get("create_time").toString(),DateUtil.YYYY_MM_DD_HH_MM_SS);
				map.put("time",CommonHelper.getPerfitTime(time));
				map.remove("create_time");
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	}

	@Override
	public Integer getCommentNotificationCount(Long userId) {
		return notificationDAO.getCommentNotificationCount(userId);
	}

	@Override
	public List queryCommentNotificationList(Long userId, Integer page,
			Integer pageSize) throws Exception{
		List list = notificationDAO.queryCommentNotificationList(userId, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				map.put("user_img_url", SysConfig.IMG_VISIT_URL+map.get("user_img_url")
						+ QiNiuStorageHelper.MODEL1+"w/750/h/460");
				
				
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL1+"w/750/h/460");
				
				
				/**
				 * 时间
				 */
				Date time = DateUtil.convertStringTODate(map.get("create_time").toString(),DateUtil.YYYY_MM_DD_HH_MM_SS);
				map.put("time",CommonHelper.getPerfitTime(time));
				map.remove("create_time");
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	}

	@Override
	public Integer getLikeNotificationCount(Long userId) {
		return notificationDAO.getLikeNotificationCount(userId);
	}

	@Override
	public List queryLikeNotificationList (Long userId, Integer page,
			Integer pageSize) throws Exception{
		List list = notificationDAO.queryLikeNotificationList(userId, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				map.put("user_img_url", SysConfig.IMG_VISIT_URL+map.get("user_img_url")
						+ QiNiuStorageHelper.MODEL1+"w/750/h/460");
				
				
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL1+"w/750/h/460");
				
				/**
				 * 时间
				 */
				Date time = DateUtil.convertStringTODate(map.get("create_time").toString(),DateUtil.YYYY_MM_DD_HH_MM_SS);
				map.put("time",CommonHelper.getPerfitTime(time));
				map.remove("create_time");
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	}

	@Override
	public Integer getUnreadNotificationCount(Long userId) {
		return notificationDAO.getUnreadNotificationCount(userId);
	}

	@Override
	public Map getUnreadMap(Long userId) {
		return notificationDAO.getUnreadMap(userId);
	}

	@Override
	public void updateIsRead(Long userId, Integer bizType) {
		notificationDAO.updateIsRead(userId, bizType);
	}

	@Override
	public void updateNotifyIsRead(Long userId) {
		notificationDAO.updateNotifyIsRead(userId);
	}

	@Override
	public Boolean checkSameFriend(Long srcUserId, Long desUserId) {
		if(srcUserId == null || desUserId == null){
			return false;
		}
		Integer result = notificationDAO.getSameFriendCount(srcUserId, desUserId);
		if(result != null && result > 0){
			return false;
		}
		return true;
	}

	@Override
	public Boolean checkSameNoteLike(Long srcUserId, Long desUserId, Long bizId) {
		if(srcUserId == null || desUserId == null || bizId == null){
			return false;
		}
		Integer result = notificationDAO.getSameNoteLikeCount(srcUserId, desUserId, bizId);
		if(result != null && result > 0){
			return false;
		}
		return true;
	}

	
}
