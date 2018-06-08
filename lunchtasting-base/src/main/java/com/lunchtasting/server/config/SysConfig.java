package com.lunchtasting.server.config;

/**
 * 公用帮组类
 * @author xq
 *
 */
public class SysConfig {
	
	/**
	 * 测试环境
	 */
	//友盟推送
	public final static String PUSH_UMENG_ANDROID_APPKEY = "";
	public final static String PUSH_UMENG_ANDROID_MASTERSECRET = "";
	public final static String PUSH_UMENG_IOS_APPKEY = "57c943aa67e58e1f43000c43";
	public final static String PUSH_UMENG_IOS_MASTERSECRET = "yxnhnzpr2mvyxafmlxjcffvfpwxbb6hp";
//	public final static boolean PUSH_SWITCH = false;
//	
	//七牛
//	public final static String QINIU_ACCESSKKEY = "aTYGSSdVHYdZDSbQHQP_b5B8d01VNcfGgX-LXPBG";
//	public final static String QINIU_SECRETKEY = "ECDizQrvpaOY7I3Z35H3nk8nzB07XC4jmfwHgEsm";
//	public final static String IMG_VISIT_URL = "http://ocjp9x6x9.bkt.clouddn.com/";
//	public final static String QINIU_IMAGE_BUCKETNAME = "perfit-image"; 
//	
	//微信登录
	public final static String WEChAT_APPID = "wx11ef503bfa72cb61";
	public final static String WEChAT_SECRET = "19f755e7a9d1605da44f81a32f74de5f";

	
	
	
	/**
	 * 正式环境
	 */
	//友盟
	
	//七牛
	public final static String QINIU_ACCESSKKEY = "7orzceKY7Hd17Rk2m0fuj_32hJuhzOZ0PZ86pwwh";
	public final static String QINIU_SECRETKEY = "NEHjBO_3ZXjAzWKq-DlFHa6oXXt5uLYD1rNUbPFX";
	public final static String IMG_VISIT_URL = "http://image.mperfit.com/";
	//public final static String IMG_VISIT_URL = "http://ocjov3qs6.bkt.clouddn.com/";
	public final static String QINIU_IMAGE_BUCKETNAME = "perfit-image"; 
	public final static boolean PUSH_SWITCH = true;


	
	/**
	 * 日志文件 
	 */
	public static final String LOGGRR_CONTROLLER = "controllerLogger";
	public static final String LOGGER_TOOL = "toolLogger";
	public static final String LOGGER_ALIPAY = "alipayLogger";
	public static final String LOGGER_TENPAY = "tenpayLogger";
	
	/**
	 * 有米APPID
	 */
	public static final String YOUMI_APP_ID = "1155981225";
	
}
