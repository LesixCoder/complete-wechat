package com.lunchtasting.server.biz.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.user.UserHotBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.user.UserHotDAO;
import com.lunchtasting.server.po.lt.UserHot;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class UserHotBIZImpl implements UserHotBIZ{
	@Autowired
	private UserHotDAO userDao;

	@Override
	public List queryUserHotList(Integer page, Integer pageSize,Integer time) {
		// TODO Auto-generated method stub
		List list =  userDao.queryUserHotList(page, pageSize,time);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				HashMap map = (HashMap) list.get(i);
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL1+"w/80/h/80");
				newList.add(map);	
			}
			return newList;
		}
		return list;
	}

	@Override
	public Boolean create() {
		// TODO Auto-generated method stub
		UserHot userHot = new UserHot();
		userDao.create(userHot);
		return true;
	}

	@Override
	public Integer getUserHotCount(Integer time) {
		// TODO Auto-generated method stub
		return userDao.getUserHotCount(time);
	}

	@Override
	public Integer getUserHotByUser(Long userId, Integer time) {
		// TODO Auto-generated method stub
		return userDao.getUserHotByUser(userId,time);
	}

	@Override
	public Integer getUserHotRank(Long userId, Integer time) {
		// TODO Auto-generated method stub
		return userDao.getUserHotRank(userId, time);
	}
}
