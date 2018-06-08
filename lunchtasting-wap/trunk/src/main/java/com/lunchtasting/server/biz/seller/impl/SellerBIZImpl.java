package com.lunchtasting.server.biz.seller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.seller.SellerBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.seller.SellerDAO;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class SellerBIZImpl implements SellerBIZ {

	@Autowired
	private SellerDAO sellerDAO;

	@Override
	public Map getShareDetail(Long sellerId) {
		Map map = sellerDAO.getShareDetail(sellerId);
		if(map != null){
			
			/**
			 * 图片
			 */
			map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
					+ QiNiuStorageHelper.MODEL0+"w/640/h/500");
//			if(ValidatorHelper.isMapNotEmpty(map.get("img_array"))){
//				List imgList = new ArrayList();
//				String imgArray = map.get("img_array").toString();
//				for(String img : imgArray.split(",")){
//					imgList.add(SysConfig.IMG_VISIT_URL+img
//							+ QiNiuStorageHelper.MODEL0+"w/640/h/500");
//				}
//				map.put("img_list", imgList);
//				map.remove("img_array");
//			}
		}
		return map;
	}
	
}
