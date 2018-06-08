package com.lunchtasting.server.helper;

/**
 * 微信配置相关文件
 * @author Administrator
 *
 */

public class WeChatConfig {
	
	/**
	 * 咆哮狗公众号相关信息
	 */
	public static final String APP_ID = "wx9209004c5710de36";
	public static final String APP_SECRET = "4f016fa29c8f7a6e7284eff1163dbd02";
	//财付通密匙
	public static final String APP_KEY = "ZywudishuaiqitiancaiF18B2lihaile";
    //微信支付商户号
	public static String MCH_ID = "1419852602";
	public static String MCH_NAME = "CrazyDog咆哮狗";
	//public static String MCH_NAME = "perfit";
	//域名
	public static final String rootUrl = "http://wchat.mperfit.com";
	//授权证书（退款使用）
	public static final String APICLIENT_CERT = "/home/service/resources/wechat/pxg/apiclient_cert.p12";
	
	
//	/**
//	 * 赛事云公众号相关信息
//	 */
//	public static final String APP_ID = "wxbda4bea6a00a2a45";
//	public static final String APP_SECRET = "d156f2094c4ae44b763958ae59c5bedc";
////	//财付通密匙
//	public static final String APP_KEY = "WMwudishuaiqitiancaiF18B2lihaile";
////  //微信支付商户号
//	public static String MCH_ID = "1486911782";
//	public static String MCH_NAME = "完美赛事云";
//	//域名
//	//public static final String rootUrl = "test-wechat.mperfit.com";
//	public static final String rootUrl = "wchat.mperfit.com";
//    //授权证书（退款使用）
//	public static final String APICLIENT_CERT = "/home/service/resources/wechat/ssy/apiclient_cert.p12";

	
	
	/**
	 * 微信支付回调路径
	 */
	public static String NOFIFY_MATCH_URL = "http://"+rootUrl+"/wxpay/match/notify";
	public static String NOFIFY_GOODS_URL = "http://"+rootUrl+"/wxpay/goods/notify";
	public static String NOFIFY_ALBUM_IMAGE_URL = "http://"+rootUrl+"/wxpay/album_image/notify";
	public static String NOFIFY_COURSE_URL = "http://"+rootUrl+"/wxpay/course/notify";



	
}

