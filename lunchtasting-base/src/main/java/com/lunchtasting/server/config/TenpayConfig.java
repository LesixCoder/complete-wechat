package com.lunchtasting.server.config;

/**
 * 微信支付参数
 * Created on 2016-11-3
 * @author xuqian
 *
 */
public class TenpayConfig {

	
	//初始化
	public static String APP_ID = "wx11ef503bfa72cb61";//微信开发平台应用id
	public static String MCH_ID = "1398739502"; //微信支付分配的商户号
	public static String APP_KEY = "92dfa194391a59dc65b88b704599dbd6";//财付通密匙
	public static String SPBILL_CREATE_IP = "123.59.37.218"; //用户端实际ip
	public static String ZHENGSHU = "/home/service/resources/apiclient_cert.p12"; //证书地址
	
	//统一下单，获得预支付订单请求地址
	public static String PREPAY_ID_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	//查询订单
	public static String ORDER_QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	//订单退款
	public static String ORDER_REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	//退款订单查询
	public static String ORDER_REFUND_QUERY_URL = "https://api.mch.weixin.qq.com/pay/refundquery";

	
	//课程支付回调地址
	public static String COURSE_NOFIFY_URL = "http://app.mperfit.com/wxpay/course/notify";
	//public static String COURSE_NOFIFY_URL = "http://test-app.mperfit.com/wxpay/course/notify";

	//字符集
	public static String CHARSET = "utf-8";
	
}
