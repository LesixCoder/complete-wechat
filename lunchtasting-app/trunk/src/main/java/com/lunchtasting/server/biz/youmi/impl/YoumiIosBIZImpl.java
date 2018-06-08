package com.lunchtasting.server.biz.youmi.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.user.UserDeviceBIZ;
import com.lunchtasting.server.biz.youmi.YoumiIosBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.user.UserDeviceDAO;
import com.lunchtasting.server.dao.youmi.YoumiIosDAO;
import com.lunchtasting.server.util.HttpUtil;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class YoumiIosBIZImpl implements YoumiIosBIZ{
	@Autowired
	private YoumiIosDAO youmiIosDAO;
	@Autowired
	private UserDeviceDAO userDeviceDAO;
	
	@Override
	public boolean create(String idfa, String url) {
		// TODO Auto-generated method stub
		if(youmiIosDAO.getYoumiByIdfa(idfa)!=null){
			youmiIosDAO.updateYoumi(url,null,idfa);
			return true;
		}
		youmiIosDAO.createYoumiIos(idfa, url);
		return true;
	}

	@Override
	public void succeed(String idfa) {
		// TODO Auto-generated method stub
		try {
			/**
			 * 判断渠道是否是有米
			 */
			Map map = youmiIosDAO.getYoumiByIdfa(idfa);
			if(map==null){
				return;
			}
			/**
			 * 筛选重复用户
			 */
			String appid = SysConfig.YOUMI_APP_ID;
			Integer con =userDeviceDAO.getDeviceCount(appid, idfa);
			if(con>1){
				return;
			}
			String result = HttpUtil.queryStringForGet(map.get("url").toString());
			youmiIosDAO.updateYoumi(null,disposeString(result),idfa);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Integer disposeString(String re){
		try {
			String [] ssss = re.split(":");
			String res =ssss[1].substring(0,ssss[1].length()-1);
			if(ValidatorHelper.isNumber(res)){
				return Integer.parseInt(res);
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
