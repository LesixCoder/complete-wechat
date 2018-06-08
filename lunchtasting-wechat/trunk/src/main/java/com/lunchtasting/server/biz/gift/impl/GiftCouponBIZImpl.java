package com.lunchtasting.server.biz.gift.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.gift.GiftCouponBIZ;
import com.lunchtasting.server.dao.gift.GiftCouponDAO;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class GiftCouponBIZImpl implements GiftCouponBIZ {

	@Autowired
	private GiftCouponDAO giftCouponDAO;

	@Override
	public List queryGiftCouponList(Integer page, Integer pageSize) {
		List list = giftCouponDAO.queryGiftCouponList(page, pageSize);
		
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				
				map.put("seller_logo", VariableHelper.IMAGE_VISIT_URL+map.get("seller_logo")
						+ QiNiuStorageHelper.MODEL1+"w/200/h/200");
				
				/**
				 * 判断是否已失效
				 * status 1 正常  2已失效
				 */
				int status = 1;
				int codeCount = Integer.parseInt(map.get("code_count").toString());
				int num = Integer.parseInt(map.get("num").toString());
				if(codeCount >= num){
					status = 2;
				}
				try {
					Date endTime = DateUtil.convertStringTODate(
							map.get("end_time").toString(), "yyyy-MM-dd HH:mm");
					Date nowTime = new Date();
					if(nowTime.after(endTime)){
						status = 2;
					}
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				map.put("status", status);
				
				newList.add(map);
			}
			return newList;
		}
		
		return null;
	}

	@Override
	public Map getGiftCouponDetail(Long id) {
		Map map = giftCouponDAO.getGiftCouponDetail(id);
		if(map != null){
			map.put("seller_logo", VariableHelper.IMAGE_VISIT_URL+map.get("seller_logo")
					+ QiNiuStorageHelper.MODEL1+"w/200/h/200");
			
			/**
			 * 判断是否已失效
			 * status 1 正常  2已失效
			 */
			int status = 1;
			int codeCount = Integer.parseInt(map.get("code_count").toString());
			int num = Integer.parseInt(map.get("num").toString());
			if(codeCount >= num){
				status = 2;
			}
			try {
				Date endTime = DateUtil.convertStringTODate(
						map.get("end_time").toString(), "yyyy-MM-dd HH:mm");
				Date nowTime = new Date();
				if(nowTime.after(endTime)){
					status = 2;
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			map.put("status", status);
			
		}
		return map; 
	}

	@Override
	public List queryUserGiftCouponCodeList(Long userId, Integer page,
			Integer pageSize) {
		List list = giftCouponDAO.queryUserGiftCouponCodeList(userId, page, pageSize);
		
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				map.put("seller_logo", VariableHelper.IMAGE_VISIT_URL+map.get("seller_logo")
						+ QiNiuStorageHelper.MODEL1+"w/200/h/200");
				
				newList.add(map);
			}
			return newList;
		}
		
		return null;
	}

	@Override
	public Boolean exchangeGiftCoupon(Long giftCouponId, Long userId) {
		Integer result = giftCouponDAO.exchangeGiftCoupon(giftCouponId, userId);
		if(result == null || result == 0){
			return false;
		}
		return true;
	}

	@Override
	public Map getUserCode(Long giftCouponId, Long userId) {
		return giftCouponDAO.getUserCode(giftCouponId, userId);
	}

	@Override
	public void createGiftCouponCode(Long giftCouponId, String code) {
		Map map = new HashMap();
		IdWorker idWorker = new IdWorker(0, 0);
		map.put("id", idWorker.nextId());
		map.put("giftCouponId", giftCouponId);
		map.put("code", code);
		giftCouponDAO.createGiftCouponCode(map);
		
	}
}
