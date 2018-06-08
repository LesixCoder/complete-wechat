package com.lunchtasting.server.biz.temporaryEnroll;

public interface TempooraryInvitationCodeBIZ {
	/**
	 * 验证Code
	 * @param code
	 * @return
	 */
	boolean verdictCode(String code);
	/**
	 * 使用code
	 */
	boolean employCode(String code,Long userId);
	
	/**
	 * 加数据
	 */
	boolean add(int con);
}
