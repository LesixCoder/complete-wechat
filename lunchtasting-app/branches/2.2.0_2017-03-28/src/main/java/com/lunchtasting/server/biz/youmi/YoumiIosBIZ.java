package com.lunchtasting.server.biz.youmi;

public interface YoumiIosBIZ {
	/**
	 * 新建或者更新
	 * @param idfa
	 * @param url
	 * @return
	 */
	boolean create(String idfa,String url); 
	/**
	 * 成功注册
	 * @param idfa
	 * @return
	 */
	void succeed(String idfa);
}
