package com.lunchtasting.server.util;

public class BaseObject {
	public static final int RESULT_SUCCESS = 100; // 成功
	public static final int RESULT_FAILURE = 101; // 失败
	public static final int RESULT_PARAM_ERROR = 102; // 参数错误
	public static final int RESULT_AGAIN = 103; //重复记录
	public static final int RESULT_SYS_ERROR = 104; // 服务器异常
	public static final int RESULT_ERROR_STRING = 105; //非法字符（比如:关键字过滤）
	
	
			
	public BaseObject(int code){
		this.code = code;
	}
	
	private int code;


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	
	
}
