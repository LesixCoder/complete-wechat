package com.lunchtasting.server.biz.match.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.match.MatchBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.match.MatchDAO;
import com.lunchtasting.server.dao.match.MatchOrderDAO;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.helper.WeChatMessageTemplet;
import com.lunchtasting.server.po.lt.Match;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;
@Service
public class MatchBIZImpl implements MatchBIZ{
	
	@Autowired
	private MatchDAO matchDAO;
	@Autowired
	private MatchOrderDAO matchOrderDAO;

	@Override
	public Match getById(Long id) {
		return matchDAO.getById(id);
	}

	@Override
	public Map getMatchDetail(Long matchId, Long userId) throws ParseException {
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

//	@Override
//	public List getRecommendUser(Long userId, Long matchId, int sex,
//			int size) {
//		
//		/**
//		 * 先查看当天的推荐列表中是否已经有记录
//		 * 有，直接显示
//		 * 否，随机推荐异性用户并插入到推荐表中
//		 */
//		List userList = matchDAO.queryMatchRecommendUserList(userId, matchId);
//		if(ValidatorHelper.isEmpty(userList)){
//			
//			if(sex == 1){
//				sex = 2;
//			}else{
//				sex = 1;
//			}
//			
//			userList = matchOrderDAO.queryMatchOrderUserList(matchId, sex, size);
//			if(ValidatorHelper.isNotEmpty(userList)){
//				IdWorker idWorker = new IdWorker(0, 0);
//				for(int i =0 ;i<userList.size();i++){
//					HashMap map = (HashMap) userList.get(i);
//					matchDAO.createMatchRecommendUser(idWorker.nextId(), userId
//							, Long.parseLong(map.get("user_id").toString()), matchId);
//				}
//
//			}
//		}
//		
//		/**
//		 * 处理拼装业务
//		 */
//		if(ValidatorHelper.isNotEmpty(userList)){
//			List newList = new ArrayList();
//			for(int i =0 ;i<userList.size();i++){
//				HashMap map = (HashMap) userList.get(i);
//				
//				/**
//				 * 用户标签
//				 */
//				if(ValidatorHelper.isMapNotEmpty(map.get("tags"))){
//					String tags = map.get("tags").toString();
//					String [] spilt = tags.split(",");
//					List tagList = new ArrayList();
//					Map tagMap = null;
//					for(String tag : spilt){
//						tagMap = new HashMap();
//						tagMap.put("tag", tag);
//						tagMap.put("random", Utils.getRandomNumber(1,6));
//						tagList.add(tagMap);
//					}
//					map.put("tag_list", tagList);
//				}
//				
//				/**
//				 * 星座 年龄
//				 */
//				if(ValidatorHelper.isMapNotEmpty(map.get("birth"))){
//					String birth = map.get("birth").toString();
//					try {
//						Date date = DateUtil.convertStringTODate(birth,DateUtil.DATE_PATTERN_YYYY_MM_DD);
//						String constellation = CommonHelper.getConstellation(date.getMonth()+1, date.getDate());
//						map.put("constellation", constellation);
//						int age = CommonHelper.getAge(date);
//						map.put("age", age);
//						
//					} catch (ParseException e) {
//						e.printStackTrace();
//					}
//					
//				}
//				
//				newList.add(map);
//			}
//			return newList;
//		}
//		
//		return null;
//	}

	@Override
	public Boolean inviteGroupUser(Long srcUserId, Long desUserId,
			Long matchId, String content) {
		matchDAO.createMatchInvite(IdWorker.getId(), srcUserId, desUserId, matchId, content);
		return true;
	}

	@Override
	public List queryMatchInviteList(Long userId, Integer page, Integer pageSize) {
		return matchDAO.queryMatchInviteList(userId, page, pageSize);
	}

	@Override
	public Boolean checkIsInvite(Long srcUserId, Long desUserId, Long matchId) {
		int result = matchDAO.getIsInvite(srcUserId, desUserId, matchId);
		if(result > 0){
			return true;
		}
		return false;
	}

	@Override
	public Map getMatchCodeByCode(String code) {
		return matchDAO.getMatchCodeByCode(code);
	}

	@Override
	public List queryPxgCjList(String sex) {
		return matchDAO.queryPxgCjList(sex);
	}
	
}
