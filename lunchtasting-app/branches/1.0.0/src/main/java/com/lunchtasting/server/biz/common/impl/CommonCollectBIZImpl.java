package com.lunchtasting.server.biz.common.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.common.CommonCollectBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.common.CommonCollectDAO;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.po.lt.CommonCollect;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class CommonCollectBIZImpl implements CommonCollectBIZ{
	
	@Autowired
	private CommonCollectDAO collectDAO;

	@Override
	public Boolean createCollect(Long userId, Long bizId, Integer bizType) {
		
		CommonCollect collect = new CommonCollect();
		collect.setId(IdWorker.getId());
		collect.setUserId(userId);
		collect.setBizId(bizId);
		collect.setBizType(bizType);
		collectDAO.create(collect);
		if(ValidatorHelper.isEmpty(collect.getId())){
			return false;
		}
		return true;
	}

	@Override
	public Boolean checkUserCollect(Long userId, Long bizId, Integer bizType) {
		Long collectId = collectDAO.getCollectId(userId, bizId, bizType);
		if(collectId == null){
			return false;
		}
		return true;
	}

	@Override
	public Integer getUserCollectCount(Long userId,Integer bizType) {
		return collectDAO.getUserCollectCount(userId,bizType);
	}

	@Override
	public List queryActivtyCollectList(Long userId,Integer page, Integer pageSize) throws ParseException {

		List list = collectDAO.queryActivtyCollectList(userId, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL0+"w/330/h/216");
				
				/**
				 * 时间
				 */
				map.put("time",CommonHelper.getActivityTime(
							map.get("begin_time").toString(), map.get("end_time").toString(),1));
	
				
				/**
				 * 状态
				 */
				int status = CommonHelper.getActivityStatus(
						map.get("begin_time").toString(), map.get("end_time").toString());
				if(status == 1){
					
					/**
					 * 几天开始
					 */
					String differDay = CommonHelper.getActivityDifferDay(map.get("begin_time").toString());
					map.put("differ_day", differDay);
				}
				map.put("status", status);
				
				
				map.remove("begin_time");
				map.remove("end_time");
				newList.add(map);
			}
			return newList;
		}
		return list;
	}

	@Override
	public List queryArticleCollectList(Long userId, Integer page,
			Integer pageSize) {
		List list = collectDAO.queryArticleCollectList(userId, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url"));
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	}
	
	@Override
	public List querySellerCollectList(Long userId, Integer page,
			Integer pageSize) {
		List list = collectDAO.querySellerCollectList(userId,page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL0+"w/640/h/460");
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	}

	@Override
	public Boolean deleteCollect(Long userId, Integer bizType, String ids) {
		int result = collectDAO.deleteCollect(userId, bizType, ids);
		if(result > 0){
			return true;
		}
		return false;
	}

}
