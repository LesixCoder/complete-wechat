package com.lunchtasting.server.helper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.avro.tool.Main;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;

import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 服务器报文返回结构定义类
 * @author xuqian
 *
 */
public class MapResult {


	public static final String CODE = "code";
	public static final String TYPE = "type";
	public static final String DATA = "data";
	public static final String MESSAGE = "message";
	
	
	/**
	 * type类型解析
	 * 0：无提示，1：tips提示，-1：messagebox提示
	 */
	public static final int TYPE_0 = 0;
	public static final int TYPE_1 = 1;
	public static final int TYPE_2 = 2;
	
	/**
	 * status状态解析
	 * 0：正常，1：内容无变化，-1：异常
	 */
	public static final int STATUS_SUCCESS = 0;
	public static final int STATUS_NOMAL = 1;
	public static final int STATUS_FAIL = -1;

	/**
	 * code码说明
	 * 100 普通错误
	 */
	public static final int CODE_SUCCESS = 100; // 成功
	public static final int CODE_FAILURE = 10001; // 失败
	public static final int CODE_PARAM_ERROR = 10002; // 参数错误
	public static final int CODE_AUTH_ERROR = 10003; //认证失败
	public static final int CODE_HEADERS_ERROR = 10004; //头部参数错误
	public static final int CODE_SYS_ERROR = 10005; // 系统错误
	public static final int CODE_AGAIN_LOGIN = 10006; // 重新登录
	public static final int CODE_IS_BAN = 10007; // 用户被禁用
	public static final int CODE_IS_BLACKLIST = 10008; // 用户被拉黑名单
	
	
	/**
	 * 错误提示文案
	 */
	public static final String MESSAGE_SUCCESS = "成功！";
	public static final String MESSAGE_PARAMETER = "参数错误";
	public static final String MESSAGE_NOTLOGIN = "从新登录";
	public static final String MESSAGE_ERROR = "服务器错误！";
	public static final String MESSAGE_FAILURE = "失败！";
	public static final String MESSAGE_BAN = "账号已被禁用，如有问题请联系客服！";
	
	public static String build(int code,String message){
		Map map = new HashMap();
		map.put(CODE, code);
		map.put(MESSAGE, message);
		map.put(DATA, "");
		return JSONObject.toJSONString(map);
	}
	
	public static Map build(int code,String message,Map data,HttpServletRequest request){
		Map map = new HashMap();
		map.put(CODE, code);
		map.put(MESSAGE, message);
		if(data != null){
			map.put(DATA, data);
		}else{
			map.put(DATA, "");
		}
		
		/**
		 * 打印日志返回参数信息
		 */
		request.setAttribute(VariableConfig.RETURN_LOGGER_MSG, JSONObject.toJSONString(map));
		
		return map;
	}
	
	public static Map build(int code,String message,HttpServletRequest request){
		Map map = new HashMap();
		map.put(CODE, code);
		map.put(MESSAGE, message);
		map.put(DATA, "");

		/**
		 * 打印日志返回参数信息
		 */
		request.setAttribute(VariableConfig.RETURN_LOGGER_MSG, JSONObject.toJSONString(map));
		
		return map;
	}

}
