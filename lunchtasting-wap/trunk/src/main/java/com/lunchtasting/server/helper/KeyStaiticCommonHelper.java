package com.lunchtasting.server.helper;
/**
 * 静态常量的Key。唯一重要的。
 * @author Administrator
 *
 */
public class KeyStaiticCommonHelper {
	/**
	 * 微信APPID公众号 唯一标识
	 */
	public static final String APP_ID = "wx9209004c5710de36";
	/**
	 * 财付通密匙
	 */
	public static final String appSecret = "4f016fa29c8f7a6e7284eff1163dbd02";
	public static final String APP_KEY = "ZywudishuaiqitiancaiF18B2lihaile";
	/**
	 * 域名
	 * 
	 */
	
	public static final String rootUrl = "wap.mperfit.com";
	
	/**
	 * 微信支付商户号
	 */
	public static String MCH_ID = "1419852602";
   //=======================================临时活动===============================================
	/*public static final String rootUrl = "test-wap.mperfit.com";
	public static String WEBCHAT_LOGIN_URL = "http://test-wap.mperfit.com/wxpay/temporaryEnroll/sign";
	public static String COURSE_NOFIFY_URL = "http://test-wap.mperfit.com/wxpay/temporaryEnroll/notify";*/
	/**
	 * 微信支付回调路径
	 */
	
	public static String COURSE_NOFIFY_URL = "http://wap.mperfit.com/wxpay/temporaryEnroll/notify";
	/**
	 * 价格
	 */
	public static double  PRICE =128;
	/**
	 * 不授权     微信调用回调地址
	 */
	
	public static String WEBCHAT_LOGIN_URL = "http://wap.mperfit.com/wxpay/temporaryEnroll/sign";
	
	/**
	 * 临时用户表session
	 */
	public static String TEMPORARY_LOGIN_SESSION_USER = "temporary_login_session_user";
	/**
	 * boby微信支付
	 */
	public static String TEMPORARY_BODY = "活动";
	
}
