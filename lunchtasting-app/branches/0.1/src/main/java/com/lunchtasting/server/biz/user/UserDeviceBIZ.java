package com.lunchtasting.server.biz.user;

import javax.servlet.http.HttpServletRequest;

public interface UserDeviceBIZ {

	String getAccessTokenByAuthId(String authId);
	
	/**
	 * 修改当前用户的最新设备信息
	 * @param userId
	 * @param accessToken
	 * @param authId
	 * @param request
	 * @return
	 */
	Boolean updateDevice(Long userId,String accessToken,String authId,HttpServletRequest request);
}
