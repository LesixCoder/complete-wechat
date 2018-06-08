
package com.lunchtasting.server.push.impl;

import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.push.PushDataHerlper;
import com.lunchtasting.server.push.PushService;

import java.util.logging.Logger;

import push.AndroidNotification;
import push.PushClient;
import push.android.AndroidBroadcast;
import push.android.AndroidUnicast;
import push.ios.IOSBroadcast;
import push.ios.IOSUnicast;

public class UMengPushServiceImpl extends AbstractPushServiceImpl {
	
	private Logger logger = Logger.getLogger(SysConfig.LOGGER_TOOL);
	private PushClient client = new PushClient();

	@Override
	protected Boolean processBuildNotification(Integer platform, String title,
			String data) throws Exception {
		if(platform == StateEnum.PUSH_IOS.getValue()){
			sendIOSBroadcast(title,data);
		}else if(platform == StateEnum.PUSH_ANDROID.getValue()){
			//sendAndroidBroadcast();
		}else{
			//sendIOSBroadcast();
			//sendAndroidBroadcast();
		}
		return true;
	}

	@Override
	public Boolean processBuildSingleNotification(String token, Integer platform,
			String title, String data) throws Exception {
		if(platform == StateEnum.PUSH_IOS.getValue()){
			sendIOSUnicast(token,title,data);
		}
		if(platform == StateEnum.PUSH_ANDROID.getValue()){
			//sendAndroidUnicast();
		}
		return true;
	}
	
	/**
	 * ios单个推送
	 * @param token
	 * @param title
	 * @param data
	 * @throws Exception
	 */
	private void sendIOSUnicast(String token,String title,String data) throws Exception {
		IOSUnicast unicast = new IOSUnicast(
				SysConfig.PUSH_UMENG_IOS_APPKEY,SysConfig.PUSH_UMENG_IOS_MASTERSECRET);
		// TODO Set your device token
		unicast.setDeviceToken(token);
		unicast.setAlert(title);
		unicast.setBadge( 1);
		unicast.setSound( "default");
		// TODO set 'production_mode' to 'true' if your app is under production mode
		if(SysConfig.PUSH_SWITCH){
			unicast.setProductionMode();
		}else{  
			unicast.setTestMode();
		}
		// Set customized fields
		unicast.setCustomizedField("data", data);
		client.send(unicast);
	}
		
	/**
	 * ios广播推送
	 * 通知
	 * @throws Exception
	 */
	private void sendIOSBroadcast(String title,String data) throws Exception {
		IOSBroadcast broadcast = new IOSBroadcast(
					SysConfig.PUSH_UMENG_IOS_APPKEY,SysConfig.PUSH_UMENG_IOS_MASTERSECRET);

		broadcast.setAlert(title);
		broadcast.setBadge(1);
		broadcast.setSound("default");
		// TODO set 'production_mode' to 'true' if your app is under production mode
		if(SysConfig.PUSH_SWITCH){
			broadcast.setProductionMode();
		}else{
			broadcast.setTestMode();
		}
		// Set customized fields
		broadcast.setCustomizedField("data", data);
		client.send(broadcast);
	}

	/**
	 * android单个推送
	 * 通知
	 * @throws Exception
	 */
	private void sendAndroidUnicast(String deviceToken) throws Exception {
		AndroidUnicast unicast = new AndroidUnicast(
				SysConfig.PUSH_UMENG_ANDROID_APPKEY,SysConfig.PUSH_UMENG_ANDROID_MASTERSECRET);

		// TODO Set your device token
		unicast.setDeviceToken( "your device token");
		unicast.setTicker( "Android unicast ticker");
		unicast.setTitle(  "中文的title");
		unicast.setText(   "Android unicast text");
		unicast.goAppAfterOpen();
		unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		if(SysConfig.PUSH_SWITCH){
			unicast.setProductionMode();
		}else{
			unicast.setTestMode();
		}
		// Set customized fields
		unicast.setExtraField("test", "helloworld");
		client.send(unicast);
	}
	
	/**
	 * android广播通知
	 * 通知
	 * @throws Exception
	 */
	private void sendAndroidBroadcast() throws Exception {
		AndroidBroadcast broadcast = new AndroidBroadcast(
					SysConfig.PUSH_UMENG_ANDROID_APPKEY,SysConfig.PUSH_UMENG_ANDROID_MASTERSECRET);
		broadcast.setTicker( "Android broadcast ticker");
		broadcast.setTitle(  "中文的title");
		broadcast.setText(   "Android broadcast text");
		broadcast.goAppAfterOpen();
		broadcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		if(SysConfig.PUSH_SWITCH){
			broadcast.setProductionMode();
		}else{
			broadcast.setTestMode();
		}
		// Set customized fields
		broadcast.setExtraField("test", "helloworld");
		client.send(broadcast);
	}
	
	public static void main(String[] args) throws Exception {
		PushService push = new UMengPushServiceImpl();
		
		String jsonData = PushDataHerlper.getNotificationData(null, 101, "我是标题"
				, "测试消息", 1, 2);
		
		//System.out.println(jsonData);
		push.buildSingleNotification("75610e4aec419e412fb3f64c55b3131bcb681256f70a7e3825d475a3202d77bb"
				,2, "通知111", jsonData);
		
		
		//UMengPushServiceImpl u = new UMengPushServiceImpl();
		//String data = PushDataHerlper.getNotificationData(1,1);
		//u.sendIOSUnicast("0076db158c5460326a67b48c466071db2d5ec9983a251c6c6b6bc9b7b57e7eb7","我是测试 ",null);

		//u.sendIOSBroadcast("测试推送", data);
	}
}

