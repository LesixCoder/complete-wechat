package com.perfit.server.helper;

import com.lunchtasting.server.config.SysConfig;


/**
 * 公共常量存放类
 * @author xuqian
 *
 */
public class VariableHelper {
	
	/**
	 * 默认分页
	 */
	public static final int PAGE_SIZE = 10;
	
	/**
	 * session 和 cookeie 的名字
	 */
	public static final String LOGIN_SESSION_USER = "login_session_seller_user";
	public static final String LOGIN_COOKIE_USER = "login_cookie_seller_user";
	
	
	/**
	 * 环境切换
	 */
	//public static final String IMAGE_VISIT_URL = "http://101.200.173.9:20002/";
	public static final String IMAGE_VISIT_URL = "http://image.jklm.com/";
	
	/**
	 * 图片上传根目录
	 */
	public static final String UPLOAD_IMAGE_ROOT_DIRECTORY = "/home/jklm/images/";
	
	/**
	 * 七牛的首路径
	 */
	public static final String IMG_URL_HEAD = SysConfig.IMG_VISIT_URL;


}
