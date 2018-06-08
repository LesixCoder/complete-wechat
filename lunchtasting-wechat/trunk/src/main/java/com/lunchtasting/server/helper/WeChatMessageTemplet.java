package com.lunchtasting.server.helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.lunchtasting.server.config.TenpayConfig;
import com.lunchtasting.server.util.ValidatorHelper;
import com.sun.org.apache.regexp.internal.recompile;

/**
 * 微信消息模板帮助类
 * Created on 2017-5-21
 * @author xuqian
 *
 */
public class WeChatMessageTemplet {
	
//	//赛事组队邀请通知模板
//	public static final String MATCH_GROUP_INVITE_TEMPLET = "YWZ_OGGJQ6LQh3KnHt5VJbe0Y12h8peFX5_M0A";
//	//赛事组队邀请通知同意模板
//	public static final String MATCH_GROUP_INVITE_AGREE_TEMPLET = ""; 
//	//赛事报名成功
//	public static final String MATCH_APPLY_SUCCESS_TEMPLET = "";
	
	//商品付款模板
	public static final String ORDER_PAY_TEMPLET = "MgXpAW3jZ92GRmZh9tOTDqGjSADiuBMyPH8AF0W-25A";
	//商品退款模板
	public static final String ORDER_REFUND_TEMPLET = "lMy8ES-7SeZNJ4NyPXJoH4_Nj7gjQIZQF8E4chrAIec";
	//中奖模板
	public static final String WIN_TEMPLET = "8jxo2qnwjr9nAB4cBjza5byt0dHW03iGbvysm1_NJso";
	
	
	/**
	 * 课程订单付款成功
	 * @param openId
	 * @param goUrl
	 * @param title
	 * @param reason
	 * @param price
	 * @param remark
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static boolean courseOrderPay(String openId,String goUrl,String title,String price,String info,String remark) throws ClientProtocolException, IOException{
		if(ValidatorHelper.isEmpty(openId) || ValidatorHelper.isEmpty(title) || ValidatorHelper.isEmpty(info) 
				|| ValidatorHelper.isEmpty(price) || ValidatorHelper.isEmpty(remark)){
				return false;
		}
		
		String accessToken = WeChatHelper.getAccessToken();
		if(ValidatorHelper.isEmpty(accessToken)){
			return false;
		}
		
		/**
		 * 组装数据
		 */
		Map dataMap = getDataMap(openId, ORDER_PAY_TEMPLET, goUrl);
		if(dataMap == null){
			return false;
		}
		Map contentMap = new HashMap();
		contentMap.put("first", getValueMap(title, "#173177"));
		contentMap.put("orderMoneySum", getValueMap(price, "#173177"));
		contentMap.put("orderProductName", getValueMap(info, "#173177"));
		contentMap.put("Remark", getValueMap(remark, "#173177"));
		dataMap.put("data",contentMap);
		String json = JSON.toJSON(dataMap).toString();
		
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;
		HttpPost request = new HttpPost(url);
		StringEntity entity = new StringEntity(json,"utf-8");
		request.setEntity(entity);
		HttpResponse response = new DefaultHttpClient().execute(request);
		if(response.getStatusLine().getStatusCode()==200){
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
			if (ValidatorHelper.isNotEmpty(result)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 课程退款消息模板
	 * @param openId
	 * @param goUrl(模板跳转链接)
	 * @param title
	 * @param reason
	 * @param price
	 * @param remark
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static boolean courseOrderRefund(String openId,String goUrl,String title,String reason,String price,String remark) throws ClientProtocolException, IOException{
		if(ValidatorHelper.isEmpty(openId) || ValidatorHelper.isEmpty(title) || ValidatorHelper.isEmpty(reason) 
				|| ValidatorHelper.isEmpty(price) || ValidatorHelper.isEmpty(remark)){
				return false;
		}
		
		String accessToken = WeChatHelper.getAccessToken();
		if(ValidatorHelper.isEmpty(accessToken)){
			return false;
		}
		
		/**
		 * 组装数据
		 */
		Map dataMap = getDataMap(openId, ORDER_REFUND_TEMPLET, goUrl);
		if(dataMap == null){
			return false;
		}
		Map contentMap = new HashMap();
		contentMap.put("first", getValueMap(title, "#173177"));
		contentMap.put("reason", getValueMap(reason, "#173177"));
		contentMap.put("refund", getValueMap(price, "#173177"));
		contentMap.put("remark", getValueMap(remark, "#173177"));
		dataMap.put("data",contentMap);
		String json = JSON.toJSON(dataMap).toString();
		
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;
		HttpPost request = new HttpPost(url);
		StringEntity entity = new StringEntity(json,"utf-8");
		request.setEntity(entity);
		HttpResponse response = new DefaultHttpClient().execute(request);
		if(response.getStatusLine().getStatusCode()==200){
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
			if (ValidatorHelper.isNotEmpty(result)) {
				return true;
			}
		}
		return false;
		
	}
	
	
	public static boolean goodsWin(String openId,String goUrl,String title,String reason,String price,String remark) throws ClientProtocolException, IOException{
		if(ValidatorHelper.isEmpty(openId) || ValidatorHelper.isEmpty(title) || ValidatorHelper.isEmpty(reason) 
				|| ValidatorHelper.isEmpty(price) || ValidatorHelper.isEmpty(remark)){
				return false;
		}
		
		String accessToken = WeChatHelper.getAccessToken();
		if(ValidatorHelper.isEmpty(accessToken)){
			return false;
		}
		
		/**
		 * 组装数据
		 */
		Map dataMap = getDataMap(openId, ORDER_REFUND_TEMPLET, goUrl);
		if(dataMap == null){
			return false;
		}
		Map contentMap = new HashMap();
		contentMap.put("first", getValueMap(title, "#173177"));
		contentMap.put("reason", getValueMap(reason, "#173177"));
		contentMap.put("refund", getValueMap(price, "#173177"));
		contentMap.put("remark", getValueMap(remark, "#173177"));
		dataMap.put("data",contentMap);
		String json = JSON.toJSON(dataMap).toString();
		
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;
		HttpPost request = new HttpPost(url);
		StringEntity entity = new StringEntity(json,"utf-8");
		request.setEntity(entity);
		HttpResponse response = new DefaultHttpClient().execute(request);
		if(response.getStatusLine().getStatusCode()==200){
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
			if (ValidatorHelper.isNotEmpty(result)) {
				return true;
			}
		}
		return false;
		
	}
	
	
	/**
	 * 获得Data模板
	 * @param openId
	 * @param templateId
	 * @param goUrl
	 * @return
	 */
	private static Map getDataMap(String openId,String templateId,String goUrl){
		Map map = new HashMap();
		map.put("touser",openId);
		map.put("template_id",templateId);
		map.put("url",goUrl);
		return map;
	}
	
	/**
	 * 获得值和颜色map
	 * @param value
	 * @param color
	 * @return
	 */
	private static Map getValueMap(String value,String color){
		Map map = new HashMap();
		map.put("value", value);
		map.put("color", color);
		return map;
	}
	
//	
//	/**
//	 * 发送赛事报名成功通知
//	 * @param openId
//	 * @param goUrl
//	 * @return
//	 * @throws ClientProtocolException
//	 * @throws IOException
//	 */
//	public static boolean sendMatchSignUpSuccess(String openId,String goUrl) throws ClientProtocolException, IOException{
//		if(ValidatorHelper.isEmpty(openId)){
//			return false;
//		}
//		
//		String accessToken = WeChatHelper.getAccessToken();
//		if(ValidatorHelper.isEmpty(accessToken)){
//			return false;
//		}
//		
//		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;
//		HttpPost request = new HttpPost(url);
//		StringEntity entity = new StringEntity(getGroupInvateDate(openId,goUrl),"utf-8");
//		request.setEntity(entity);
//		HttpResponse response = new DefaultHttpClient().execute(request);
//		if(response.getStatusLine().getStatusCode()==200){
//			String result = EntityUtils.toString(response.getEntity());
//			System.out.println(result);
//			if (ValidatorHelper.isNotEmpty(result)) {
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	
//	private static String getGroupInvateDate(String openId,String url){
//		
//		Map cMap = new HashMap();
//		cMap.put("touser",openId);
//		cMap.put("template_id",MATCH_GROUP_INVITE_TEMPLET);
//		cMap.put("url",url);
//		
//		Map dataMap = new HashMap();
//		dataMap.put("first", getMap("测试一条数据","#173177"));
//		dataMap.put("keyword1", getMap("测试一条数据","#173177"));
//		dataMap.put("keyword2", getMap("测试一条数据","#173177"));
//		dataMap.put("keyword3", getMap("测试一条数据","#173177"));
//		dataMap.put("remark", getMap("测试一条数据","#173177"));
//		cMap.put("data",dataMap);
//		
//		return JSON.toJSON(cMap).toString();
//	}
//	
//	private static Map getMap(String value,String color){
//		Map map = new HashMap();
//		map.put("value", value);
//		map.put("color", color);
//		return map;
//	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		System.out.println(3333);
		String title = "您好，您购买的Crazy Dog肌械训练营-体验课已排课成功";
		String info = "Crazy Dog肌械训练营-体验课";
		String remark = "备注：因需录取您的信息安排课前体测，请及时联系客服18518481875，可获得10元现金奖励哦！";
//		WeChatMessageTemplet.courseOrderRefund("oBYZV1Zcidc0m--oSXC653vEurpU", "www.baidu.com", 
//				title, reason, 10+"", remark);
		courseOrderPay("oBYZV1ccaT4jJ30UD446w9MUKT18", "http://wchat.mperfit.com/course/921633867612291072", 
				title, 80+"",info, remark);
		System.out.println(222);
		//oBYZV1ccaT4jJ30UD446w9MUKT18
	}
}
