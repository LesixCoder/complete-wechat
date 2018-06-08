package com.lunchtasting.server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;


import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.config.AlipayConfig;
import com.lunchtasting.server.config.TenpayConfig;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.payment.AlipayHelper;
import com.lunchtasting.server.payment.TenpayHelper;
import com.lunchtasting.server.po.lt.Orders;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.DateUtils;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.SmsUtil;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

public class Test {

	public static void main(String[] args) throws Exception {
//		TenpayHelper tenpayHelper = new TenpayHelper();
//		String xml = "<xml><appid><![CDATA[wx11ef503bfa72cb61]]></appid><bank_type><![CDATA[CFT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[N]]></is_subscribe><mch_id><![CDATA[1398739502]]></mch_id><nonce_str><![CDATA[24ec8468b67314c2013d215b77034476]]></nonce_str><openid><![CDATA[o-UZCwHhQTcZfaIki-it84310SMs]]></openid><out_trade_no><![CDATA[201611272332053283367452]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[E4BC773E4507D1C91AA46B159214954A]]></sign><time_end><![CDATA[20161127233218]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[APP]]></trade_type><transaction_id><![CDATA[4006792001201611271033387655]]></transaction_id></xml>";
//		Map map = tenpayHelper.doXMLParse(xml);
//		SortedMap parameters = new TreeMap();
//		parameters.putAll(map);
//		System.out.println(parameters);
//		System.out.println(parameters.size());
//		System.out.println(CommonHelper.getParameterJson(parameters));
//		System.out.println(parameters.get("sign"));
//		tenpayHelper.isTenpaySign(parameters);
		
		//System.out.println(CommonHelper.getOutNo().length());
//		IdWorker idWorker = new IdWorker(0, 0);
//		
//		for(int i = 0 ; i < 100; i ++){
//			System.out.println(idWorker.nextId());		
//		}
		
		
//		System.out.println(idWorker.nextId());
//		System.out.println(idWorker.nextId());
//		System.out.println(idWorker.nextId());
//		System.out.println(idWorker.nextId());
//		
//		
//		System.out.println(convertStringToString("2017-12-07","yyyy.M.d"));
		
		long current = (new Date()).getTime() / 1000;
		
		long a = Math.round(Math.random() * Math.pow(2, 32));
		
		System.out.println(current);
		System.out.println(current +86400l);
		System.out.println(a);
	}
	
	
	public static String convertStringToString(String date, String pattern){
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		sf.setLenient(false);
		return sf.format(date);
	}
	
	
	
	public static Map getSignMap(String orderCode,double price,String name){
		Map map = new HashMap();
		
//		map.put("app_id", AlipayConfig.APP_ID);
//		map.put("method", "alipay.trade.app.pay");
//		map.put("charset", AlipayConfig.CHARSET);
//		map.put("sign_type", "RSA");
//		map.put("timestamp", DateUtil.convertDateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
//		map.put("version", "1.0");
//		map.put("notify_url", AlipayConfig.COURSE_NOFIFY_URL);
//		
//		Map contentMap = new HashMap();
//		contentMap.put("out_trade_no", orderCode);
//		//contentMap.put("seller_id", AlipayConfig.SELLER_ID);
//		contentMap.put("product_code", "QUICK_MSECURITY_PAY");
//		contentMap.put("subject", name);
//		contentMap.put("total_amount", price+"");
//		
//		String bizContent = JSONObject.toJSONString(contentMap);
//		map.put("biz_content", bizContent);
		return map;
	}
	
	
	
	public static void testMap(){
		TreeMap<Integer, Integer> map1 = new TreeMap<Integer, Integer>(); // 默认的TreeMap升序排列
		TreeMap<Integer, Integer> map2 = new TreeMap<Integer, Integer>(
				new Comparator<Integer>() {
					/*
					 * int compare(Object o1, Object o2) 返回一个基本类型的整型， 返回负数表示：o1
					 * 小于o2， 返回0 表示：o1和o2相等， 返回正数表示：o1大于o2。
					 */
					public int compare(Integer a, Integer b) {
						return b - a;
					}
				});
		map2.put(1, 2);
		map2.put(2, 4);
		map2.put(7, 1);
		map2.put(5, 2);
		System.out.println("Map2=" + map2);

		map1.put(1, 2);
		map1.put(2, 4);
		map1.put(7, 1);
		map1.put(5, 2);
		System.out.println("map1=" + map1);
	}

	/**
	 * 获取uuid
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static void getTime() throws ParseException {
		String strDate = "2016-08-26 16:59:00";
		System.out.println(DateUtil.convertDateToString(new Date(),
				"M.dd HH:mm"));
		System.out.println(CommonHelper.getActivityDifferDay(strDate));

	}

	public static Map getSignMap() {
		Map map = new HashMap();

		map.put("app_id", AlipayConfig.APP_ID);
		map.put("method", "alipay.trade.app.pay");
		map.put("charset", AlipayConfig.CHARSET);
		map.put("sign_type", "RSA");
		map.put("timestamp",
				DateUtil.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		map.put("version", "1.0");
		map.put("notify_url", "www.baidu.com");

		Map contentMap = new HashMap();
		contentMap.put("out_trade_no", "1213123123123");
		contentMap.put("seller_id", AlipayConfig.SELLER_ID);
		contentMap.put("product_code", "QUICK_MSECURITY_PAY");
		contentMap.put("subject", "动感单车");
		contentMap.put("total_amount", "5.00");

//		String bizContent = JSONObject.toJSONString(contentMap);
//
//		map.put("biz_content", bizContent);

		return map;
	}

}
