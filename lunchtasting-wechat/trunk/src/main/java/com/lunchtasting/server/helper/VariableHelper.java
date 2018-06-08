package com.lunchtasting.server.helper;


/**
 * 公共常量存放类
 * @author xuqian
 *
 */
public class VariableHelper {
	
	/**
	 * 默认分页
	 */
	public static final int PAGE_SIZE = 20;
	
	/**
	 * session 和 cookeie 的名字
	 */
	public static final String LOGIN_SESSION_USER = "login_session_user";
	public static final String LOGIN_COOKIE_USER = "login_cookie_user";
	/**
	 * 邀请对象和渠道
	 */
	public static final String INVITE_SESSION_USER = "invite_session_user";
	//public static final String INVITE_CHANNEL1 = "invite_channel";
	
	
	/**
	 * 环境切换
	 */
	//public static final String IMAGE_VISIT_URL = "http://101.200.173.9:20002/";
	public static final String IMAGE_VISIT_URL = "http://image.mperfit.com/";
	
	
	/**
	 * 图片上传根目录
	 */
	public static final String UPLOAD_IMAGE_ROOT_DIRECTORY = "/home/jklm/images/";
	
	/**
	 * 错误跳转页面
	 */
	public static final String ERROR_JSP = "/error";
	public static final String ERROR2_JSP = "/error2";
}
