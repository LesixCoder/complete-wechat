package com.lunchtasting.server.biz.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.common.BannerIndexBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.common.BannerIndexDAO;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class BannerIndexBIZImpl implements BannerIndexBIZ {

	@Autowired
	private BannerIndexDAO indexDAO;

	@Override
	public List queryBannerIndexList(Integer pageSize) {
		List list = indexDAO.queryBannerIndexList(pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL1+"w/1080/h/576");
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	}
}
