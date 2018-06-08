package com.lunchtasting.server.push.impl;

import com.lunchtasting.server.push.PushService;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 
 * @author xq
 *
 */
public abstract class AbstractPushServiceImpl implements PushService {
	
	
	@Override
	public Boolean buildNotification(Integer platform,String title,String data) throws Exception{
		if(ValidatorHelper.isEmpty(platform) || ValidatorHelper.isEmpty(data)){
			return false;
		}
		return processBuildNotification(platform, title,data);
	}
	
    protected abstract Boolean processBuildNotification(Integer platform,String title,String data) throws Exception;
	

	@Override
	public Boolean buildSingleNotification(String target,Integer platform,String title,String data) throws Exception{
		if(ValidatorHelper.isEmpty(target) || ValidatorHelper.isEmpty(platform)
				||ValidatorHelper.isEmpty(data)){
			return false;
		}
		return processBuildSingleNotification(target, platform,title,data);
	}
	
    protected abstract Boolean processBuildSingleNotification(String target,Integer platform,String title,String data) throws Exception;

}
