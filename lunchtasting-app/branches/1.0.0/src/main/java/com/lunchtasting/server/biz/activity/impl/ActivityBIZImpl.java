package com.lunchtasting.server.biz.activity.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lunchtasting.server.biz.activity.ActivityBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.activity.ActivityDAO;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.po.lt.Activity;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class ActivityBIZImpl implements ActivityBIZ {
	
	@Autowired
	private ActivityDAO activityDAO;
	
	@Override
	public Activity getById(Long id) {
		return activityDAO.getById(id);
	}


	@Override
	public Integer getIndexCount() {
		return activityDAO.getIndexCount();
	}


	@Override
	public List queryIndexList(Integer page, Integer pageSize) throws ParseException {
		List list = activityDAO.queryIndexList(page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				int bizType = Integer.parseInt(map.get("biz_type").toString());
				if(bizType == 1){
					
					/**
					 * 时间
					 */
					map.put("time",CommonHelper.getActivityTime(
								map.get("begin_time").toString(), map.get("end_time").toString(),1));
					map.remove("begin_time");
					map.remove("end_time");
				}
				if(bizType == 2){
					map.remove("begin_time");
					map.remove("end_time");
					map.remove("address");
					map.remove("price");
					map.remove("market_price");
					map.remove("address");

				}
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL0+"w/640/h/460");
				
				/**
				 * 随机数，做颜色标签用
				 */
				map.put("random", CommonHelper.getRandomColor(map.get("biz_id").toString()));
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	}
	
	@Override
	public Integer getActivityCount() {
		return activityDAO.getActivityCount();
	}

	@Override
	public List queryActivityList(Integer page, Integer pageSize) throws ParseException {
		List list = activityDAO.queryActivityList(page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 时间
				 */
				map.put("time",CommonHelper.getActivityTime(
							map.get("begin_time").toString(), map.get("end_time").toString(),1));
				map.remove("begin_time");
				map.remove("end_time");
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL0+"w/640/h/460");
				
				/**
				 * 随机数，做颜色标签用
				 */
				map.put("random", CommonHelper.getRandomColor(map.get("biz_id").toString()));


				newList.add(map);
			}
			return newList;
		}
		return list;
	}

	@Override
	public Map getActivityDetail(Long activityId,Long userId) throws ParseException {
		Map map = activityDAO.getActivityDetail(activityId,userId);
		if(map != null){
			
			/**
			 * 时间
			 */
			map.put("time",CommonHelper.getActivityTime(
					map.get("begin_time").toString(), map.get("end_time").toString(),2));
			
			/**
			 * 图片
			 */
			map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
					+ QiNiuStorageHelper.MODEL0+"w/640/h/540");
			
			/**
			 * 判断是否收藏
			 */
			if(ValidatorHelper.isMapNotEmpty(map.get("is_collect"))){
				map.put("is_collect", 1);
			}else{
				map.put("is_collect", 0);
			}
			
			/**
			 * 判断报名状态
			 * 1 可以报名  2已经报名  3报名结束
			 */
			Date endTime = DateUtil.convertStringTODate(map.get("end_time").toString(),DateUtil.YYYY_MM_DD_HH_MM_SS);
			Date nowTime = new Date();
			if(nowTime.after(endTime)){
				map.put("enroll_status", 3);
			}else{
				if(ValidatorHelper.isMapNotEmpty(map.get("is_enroll"))){
					map.put("enroll_status", 2);
				}else{
					int enrollNum = Integer.parseInt(map.get("enroll_num").toString());
					int nowEnrollNum = Integer.parseInt(map.get("now_enroll_num").toString());
					if(nowEnrollNum >= enrollNum){
						map.put("enroll_status", 3);
					}else{
						map.put("enroll_status", 1);
					}				
				}
			}
			
			map.remove("begin_time");
			map.remove("end_time");
		}
		return map;
	}
	
	@Override
	public Map getEnrollActivity(Long activityId) throws Exception {
		Map map = activityDAO.getEnrollActivity(activityId);
		if(map != null){
			
			/**
			 * 时间
			 */
			map.put("time",CommonHelper.getActivityTime(
					map.get("begin_time").toString(), map.get("end_time").toString(),2));
			map.remove("begin_time");
			map.remove("end_time");
			
		}
		return map;
	}


	@Override
	public List queryVenueActivityList(Long sellerId) throws ParseException {
		List list = activityDAO.queryVenueActivityList(sellerId);
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
						map.get("begin_time").toString(), map.get("end_time").toString(),2));
				map.remove("begin_time");
				map.remove("end_time");
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	}

}
