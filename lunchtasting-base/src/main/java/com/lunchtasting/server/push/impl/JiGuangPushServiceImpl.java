//package com.lunchtasting.server.push.impl;
//
//import java.util.logging.Logger;
//
//import com.lunchtasting.server.config.SysConfig;
//import com.lunchtasting.server.enumeration.StateEnum;
//import com.lunchtasting.server.push.PushService;
//
//import cn.jpush.api.JPushClient;
//import cn.jpush.api.common.resp.APIConnectionException;
//import cn.jpush.api.common.resp.APIRequestException;
//import cn.jpush.api.push.PushResult;
//import cn.jpush.api.push.model.Message;
//import cn.jpush.api.push.model.Options;
//import cn.jpush.api.push.model.Platform;
//import cn.jpush.api.push.model.PushPayload;
//import cn.jpush.api.push.model.audience.Audience;
//import cn.jpush.api.push.model.notification.IosNotification;
//import cn.jpush.api.push.model.notification.Notification;
//
//public class JiGuangPushServiceImpl extends AbstractPushServiceImpl {
//	
//	private Logger logger = Logger.getLogger(SysConfig.TOOL_LOGGER);
//
//	@Override
//	protected Boolean processBuildPush(Integer platform, String msg) {
//		System.out.println(platform + "//" + msg);
//		return null;
//	}
//
//	@Override
//	protected Boolean processBuildPush(Integer platform, String msg,
//			String target) {
//
//		/**
//		 * 具体业务处理
//		 */
//		Platform jgPlatform = getPlatform(platform);
//
//		JPushClient jpushClient = new JPushClient(SysConfig.PUSH_JIGUANG_MASTERSECRET, SysConfig.PUSH_JIGUANG_MASTERSECRET, 3);
//		PushPayload payload = buildPushObject_all_all_alert(msg);
//
//		try {
//			PushResult result = jpushClient.sendPush(payload);
//			// LOG.info("Got result - " + result);
//
//		} catch (APIConnectionException e) {
//			// Connection error, should retry later
//			// LOG.error("Connection error, should retry later", e);
//
//		} catch (APIRequestException e) {
//			// Should review the error, and fix the request
//			// LOG.error("Should review the error, and fix the request", e);
//			// LOG.info("HTTP Status: " + e.getStatus());
//			// LOG.info("Error Code: " + e.getErrorCode());
//			// LOG.info("Error Message: " + e.getErrorMessage());
//		}
//
//		// buildPushObject_all_alias_alert(jgPlatform,msg,target);
//
//		return true;
//	}
//
//	/**
//	 * 获得推送平台
//	 * 
//	 * @return
//	 */
//	private Platform getPlatform(Integer type) {
//		if (StateEnum.PUSH_ALL.getValue().intValue() == type.intValue()) {
//			return Platform.all();
//		}
//		if (StateEnum.PUSH_ANDROID.getValue().intValue() == type.intValue()) {
//			return Platform.android();
//		}
//		if (StateEnum.PUSH_IOS.getValue().intValue() == type.intValue()) {
//			return Platform.ios();
//		}
//		return null;
//	}
//
//	/**
//	 * 快捷地构建推送对象：所有平台，所有设备，内容为 ALERT 的通知。
//	 * 
//	 * @param msg
//	 * @return
//	 */
//	private PushPayload buildPushObject_all_all_alert(String msg) {
//		return PushPayload.alertAll(msg);
//	}
//
//	/**
//	 * 构建推送对象：所有平台，推送目标是别名为 "alias1"，通知内容为 ALERT。
//	 * 
//	 * @return
//	 */
//	private PushPayload buildPushObject_all_alias_alert(Platform platform,
//			String msg, String target) {
//		return PushPayload.newBuilder().setPlatform(Platform.all())
//				.setAudience(Audience.alias("alias1")).setNotification(
//						Notification.alert(msg)).build();
//	}
//
//	/**
//	 * 构建推送对象：平台是 iOS，推送目标是 "tag1", "tag_all" 的交集， 推送内容同时包括通知与消息 - 通知信息是
//	 * ALERT，角标数字为 5，通知声音为 "happy"， 并且附加字段 from = "JPush"；消息内容是 MSG_CONTENT。通知是
//	 * APNs 推送通道的， 消息是 JPush 应用内消息通道的。APNs 的推送环境是“生产”（如果不显式设置的话，Library
//	 * 会默认指定为开发）
//	 * 
//	 * @return
//	 */
//	private PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage(
//			String msg) {
//		return PushPayload.newBuilder().setPlatform(Platform.ios())
//				.setAudience(Audience.tag_and("tag1", "tag_all"))
//				.setNotification(
//						Notification.newBuilder().addPlatformNotification(
//								IosNotification.newBuilder().setAlert(msg)
//										.setBadge(5).setSound("happy.caf")
//										.addExtra("from", "JPush").build())
//								.build()).setMessage(Message.content(""))
//				.setOptions(
//						Options.newBuilder().setApnsProduction(true).build())
//				.build();
//	}
//	
////	/**
////	 * 构建推送对象：平台是 Andorid 与 iOS，推送目标是 （"tag1" 与 "tag2" 的并集）且（"alias1" 与 "alias2" 的并集），
////	 * 推送内容是 - 内容为 MSG_CONTENT 的消息，并且附加字段 from = JPush。
////	 * @return
////	 */
////	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
////		return PushPayload.newBuilder().setPlatform(Platform.android_ios())
////				.setAudience(
////						Audience.newBuilder().addAudienceTarget(
////								AudienceTarget.tag("tag1", "tag2"))
////								.addAudienceTarget(
////										AudienceTarget
////												.alias("alias1", "alias2"))
////								.build()).setMessage(
////						Message.newBuilder().setMsgContent(MSG_CONTENT)
////								.addExtra("from", "JPush").build()).build();
////	}
//	
//	
//
//	public static void main(String[] args) {
//		PushService pushService = new JiGuangPushServiceImpl();
//		
//		pushService.buildPush(1, "22");
//	}
//
//}
