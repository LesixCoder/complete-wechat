package com.lunchtasting.server.biz.match.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.match.MatchBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.match.MatchDAO;
import com.lunchtasting.server.dao.user.UserDAO;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.po.lt.Match;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.ValidatorHelper;
@Service
public class MatchBIZImpl implements MatchBIZ{
	
	@Autowired
	private MatchDAO  matchDAO;
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public Match getById(Long id) {
		return matchDAO.getById(id);
	}
	
	@Override
	public List queryMatchCategoryList(Long userId) {
		
		/**
		 * 获得当前用户赛事情况
		 */
		Map userMap = null;
		int userScore = 0;
		String userMedalIds = "";
		if(ValidatorHelper.isNotEmpty(userId)){
			userMap = userDAO.getUserMatch(userId);
			if(userMap != null){
				userScore = Integer.parseInt(userMap.get("total_score").toString());
				userMedalIds = userMap.get("medal_str").toString();
			}
		}
		
		List list = matchDAO.queryMatchCategoryList();
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				if(ValidatorHelper.isNotNull(map.get("img_url"))){
					map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
							+ QiNiuStorageHelper.MODEL0+"w/750/h/650");
				}
				
				/**
				 * 判断用户解锁条件  1.积分到达  2.获得相应勋章
				 * status 1.赛事发布用户未达到要求(上锁)  2.赛事解锁用户达到要求  
				 */
				int status = 1;
				if(userMap == null){
					//用于青铜默认打开
					if(map.get("is_lock").toString().equals("0")){
						status = 2;
					}else{
						status = 1; 
					}
				}else{
					
					/**
					 * 判断用户积分是否到达要求
					 */
					int matchScore = Integer.parseInt(map.get("score").toString());
					if(userScore >= matchScore){
						
						/**
						 * 判断用户是否获得对应勋章
						 */
						if(ValidatorHelper.isMapNotEmpty(map.get("medal_id"))){
							String matchMedalId = map.get("medal_id").toString();
							if(ValidatorHelper.isNotEmpty(userMedalIds)){
								String[] ids = userMedalIds.split(",");
								if(ValidatorHelper.isNotEmpty(ids)){
									for(String id : ids){
										if(matchMedalId.equals(id)){
											status = 2; //用户登录，获得对应积分和勋章，达到要求，赛事解锁
										}
									}
								}
							}else{ //用户登录，积分达到，但没获得对应勋章,没达到要求
								status = 1;
							}
						}else{ //用户登录，积分达到，且赛事不需要勋章,赛事解锁
							status = 2;
						}
					}else{
						status = 1; //用户登录，积分未达到，没达到要求
					}
				}
				map.put("status", status);
				map.remove("medal_id");
				map.remove("is_lock");
				newList.add(map);
			}
			return newList;
		}
		return list;
	}

	@Override
	public List queryMatchList(Long categoryId,Integer page, Integer pageSize) throws Exception {
		List list = matchDAO.queryMatchList(categoryId,page,pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				if(ValidatorHelper.isNotNull(map.get("img_url"))){
					map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
							+ QiNiuStorageHelper.MODEL0+"w/750/h/650");
				} 
				
				/**
				 * 时间
				 */
				map.put("time", DateUtil.convertDateToString(
						DateUtil.convertStringTODate(
								map.get("begin_time").toString(),DateUtil.YYYY_MM_DD_HH_MM_SS),"yyyy.M.d"));
				
				/**
				 * 判断赛事是否解锁
				 */
				if(map.get("is_lock").toString().equals("0")){
					/**
					 * 状态
					 */
					int status = CommonHelper.getActivityStatus(
							map.get("begin_time").toString(), map.get("end_time").toString());
					map.put("status", status);
				}
				
				map.remove("begin_time");
				map.remove("end_time");
				newList.add(map);
			}
			return newList;
		}
		return list;                                                                                                                                                                                                                                                                                                                                                                                                                                              
	}

	@Override
	public Integer getMatchCount(Long categoryId) {
		return matchDAO.getMatchCount(categoryId);
	}

	@Override
	public Map getMatchDetail(Long matchId,Long userId) throws Exception {
		Map map = matchDAO.getMatchDetail(matchId, userId);
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
					+ QiNiuStorageHelper.MODEL1+"w/750/h/540");
			
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

}
