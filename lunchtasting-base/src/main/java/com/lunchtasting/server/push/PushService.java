package com.lunchtasting.server.push;

public interface PushService {
	
	/**
	 * 广播群推送(通知)
	 * @param platform 0 全平台推送  1 android  2 ios
	 * @param title
	 * @param data
	 * @return
	 */
	Boolean buildNotification(Integer platform,String title,String data) throws Exception;
	
	/**
	 * 单个推送（通知）
	 * @param target 推送对象
	 * @param platform 0 全平台推送  1 android  2 ios
	 * @param title
	 * @param data
	 * @return
	 */
	
	Boolean buildSingleNotification(String target,Integer platform,String title,String data) throws Exception;
}
