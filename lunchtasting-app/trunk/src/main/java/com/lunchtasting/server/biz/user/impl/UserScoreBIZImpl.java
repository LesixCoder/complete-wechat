package com.lunchtasting.server.biz.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.user.UserScoreBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.user.UserScoreDAO;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class UserScoreBIZImpl implements UserScoreBIZ {

	@Autowired
	private UserScoreDAO userScoreDAO;

	@Override
	public Integer getUserScoreCount(Long userId) {
		return userScoreDAO.getUserScoreCount(userId);
	}

	@Override
	public List queryUserScoreList(Long userId, Integer page,Integer pageSize) {
		return userScoreDAO.queryUserScoreList(userId, page, pageSize);
	}

	@Override
	public Integer getUserScoreTotal(Long userId) {
		Integer result = userScoreDAO.getUserScoreTotal(userId);
		if(result == null){
			return 0;
		}
		return result;
	}

	@Override
	public List queryUserScoreRank(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		List list = userScoreDAO.queryUserScoreRank(page, pageSize);
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
	public Integer getUserScoreRank(Long userId) {
		// TODO Auto-generated method stub
		return userScoreDAO.getUserScoreRank(userId);
	}

	@Override
	public Integer getScoreRankCount() {
		// TODO Auto-generated method stub
		return userScoreDAO.getScoreRankCount();
	}

	@Override
	public boolean completion(String phone,Long userId) {
		// TODO Auto-generated method stub
		try {
			Integer con = userScoreDAO.getUserScoreByPhone(phone);
			if(con!=null && con>0){
				userScoreDAO.completion(phone, userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
