package com.lunchtasting.server.push;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

/**
 * 消息数据组装
 * Created on 2016-9-3
 * @author xuqian
 *
 */
public class PushDataHerlper {
	
	/**
	 * 通知数据体
	 * @param bizType
	 * @param title
	 * @param content
	 * @return
	 */
	public static String getNotificationData(Long bizId,Integer bizType,String title
				,String content,Integer num,Integer type){
		Map map = new HashMap();
		map.put("biz_id", bizId);
		map.put("biz_type", bizType);
		map.put("title", title);
		map.put("content", content);
		map.put("num", num);
		map.put("type", type);
		return JSONObject.toJSONString(map);
	}
	
	/**
	 * 消息数据体
	 * @return
	 */
	public static String getMessageData(){
		return null;
	}
}
