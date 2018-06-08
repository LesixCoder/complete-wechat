package com.lunchtasting.server.biz.user.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.user.UserDeviceBIZ;
import com.lunchtasting.server.dao.user.UserDeviceDAO;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.po.lt.UserDevice;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class UserDeviceBIZImpl implements UserDeviceBIZ {
	
	@Autowired
	private UserDeviceDAO userDeviceDAO;
	
	@Override
	public String getAccessTokenByAuthId(String authId) {
		return userDeviceDAO.getAccessTokenByAuthId(authId);
	}

	@Override
	public Boolean updateDevice(Long userId, String accessToken, String authId,HttpServletRequest request) {
		if(ValidatorHelper.isEmpty(userId) || ValidatorHelper.isEmpty(accessToken) 
				|| ValidatorHelper.isEmpty(authId)){
			return false;
		}
		UserDevice userDevice = new UserDevice();
		userDevice.setUserId(userId);
		userDevice.setAccessToken(accessToken);
		userDevice.setAuthId(authId);
		userDevice.setSystemType(request.getHeader("systemType"));
		userDevice.setSystemVersion(request.getHeader("systemVersion"));
		userDevice.setScreenWidth(Integer.parseInt(request.getHeader("screenWidth").toString()));
		userDevice.setScreenHeight(Integer.parseInt(request.getHeader("screenHeight").toString()));
		userDevice.setPlatform(request.getHeader("platform").toString());
		userDevice.setDeviceToken(request.getHeader("devicetoken"));
		userDevice.setChannel(request.getHeader("channel"));
		userDevice.setImei(request.getHeader("imei"));
		userDevice.setAppVersion(request.getHeader("appVersion"));
		
		int result = userDeviceDAO.updateDevice(userDevice);
		if(result > 0){
			return true;
		}
		
		return false;
	}


	
}
