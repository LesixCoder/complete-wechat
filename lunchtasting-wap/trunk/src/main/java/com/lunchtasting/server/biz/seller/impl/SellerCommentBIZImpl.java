package com.lunchtasting.server.biz.seller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.seller.SellerCommentBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.seller.SellerCommentDAO;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.ValidatorHelper;
@Service
public class SellerCommentBIZImpl implements SellerCommentBIZ{
	@Autowired
	private SellerCommentDAO sellerCommentDAO;
	@Override
	public List getShareCommentByCourseTop2(HashMap map) {
		// TODO Auto-generated method stub
		List list = sellerCommentDAO.getShareCommentByCourseTop2(map);
		for (int i = 0; i < list.size(); i++) {
			Map map2 = (Map) list.get(i);
			map2.put("img_url", SysConfig.IMG_VISIT_URL+map2.get("img_url")
					+ QiNiuStorageHelper.MODEL0+"w/640/h/500");
			if(ValidatorHelper.isMapNotEmpty(map2.get("img_array"))){
				List imgList = new ArrayList();
				String imgArray = map2.get("img_array").toString();
				for(String img : imgArray.split(",")){
				imgList.add(SysConfig.IMG_VISIT_URL+img
					+ QiNiuStorageHelper.MODEL0+"w/640/h/500");
				}
				map2.put("img_list", imgList);
				map2.remove("img_array");
				list.set(i, map2);
			}
		}
		return list;
	}
}
