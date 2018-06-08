package com.lunchtasting.server.biz.gym.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.gym.CourseOrderVoteBIZ;
import com.lunchtasting.server.dao.gym.CourseOrderVoteDAO;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.po.lt.CourseOrderVote;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class CourseOrderVoteBIZImpl implements CourseOrderVoteBIZ  {

	@Autowired
	private CourseOrderVoteDAO courseOrderVoteDAO;

	
	@Override
	public void createCourseOrderVote(Long userId, Long desUserId,
			Long courseOrderId,Long courseMealId) {
		
		CourseOrderVote vote = new CourseOrderVote();
		vote.setId(IdWorker.getId());
		vote.setUserId(userId);
		vote.setDesUserId(desUserId);
		vote.setCourseOrderId(courseOrderId);
		vote.setCourseMealId(courseMealId);
		courseOrderVoteDAO.create(vote);
		
	}


	@Override
	public List queryOrderVoteList(Long courseMealId, Integer page,
			Integer pageSize) {
		List list = courseOrderVoteDAO.queryOrderVoteList(courseMealId, page, pageSize);
		if(list != null){

			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				
				map.put("user_img_url", VariableHelper.IMAGE_VISIT_URL+map.get("user_img_url")
						+ QiNiuStorageHelper.MODEL0+"w/300/h/300");
				
				
				/**
				 * 投票数
				 */
				int voteCount = Integer.parseInt(map.get("vote_count").toString());
				map.put("vote_unit", voteCount%10);
				map.put("vote_ten", (voteCount/10)%10);
				map.put("vote_hundred", (voteCount/100)%10);

				
				/**
				 * 多张用户图片
				 */
				List userList = null;
				if(ValidatorHelper.isMapNotEmpty(map.get("vote_str"))){
					userList = new ArrayList();
					String voteStr =  map.get("vote_str").toString();
					String [] aSpilt = voteStr.split("\\|");
					if(ValidatorHelper.isNotEmpty(aSpilt)){
						Map userMap = null;
						int count = 0;
						for(String s : aSpilt){
							String [] bSpilt = s.split("\\@");
							if(ValidatorHelper.isNotEmpty(bSpilt) && count < 9){
								userMap = new HashMap();
								userMap.put("user_id", Long.parseLong(bSpilt[0]));
								
								userMap.put("user_img_url", VariableHelper.IMAGE_VISIT_URL+bSpilt[1] 
										+ QiNiuStorageHelper.MODEL0+"w/100/h/100");
								
								userList.add(userMap);
								count++;
							}
						}
					}
				}
				map.remove("vote_str");
				map.put("user_list", userList);
				newList.add(map);
			}
			return newList;
		}
		
		return null;
	}


	@Override
	public Integer getOrderVoteCount(Long courseMealId) {
		return courseOrderVoteDAO.getOrderVoteCount(courseMealId);
	}


	@Override
	public List queryUserVoteList(Long orderId,Long desUserId,Integer page,Integer pageSize) {
		List list = courseOrderVoteDAO.queryUserVoteList(orderId,desUserId, page, pageSize);
		if(list != null){

			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				map.put("user_img_url", VariableHelper.IMAGE_VISIT_URL+map.get("user_img_url")
						+ QiNiuStorageHelper.MODEL0+"w/200/h/200");
				
				newList.add(map);
			}
			return newList;
		}
		
		return null;
	}


	@Override
	public Integer getUserVoteCount(Long orderId,Long desUserId) {
		return courseOrderVoteDAO.getUserVoteCount(orderId, desUserId);
	}


	@Override
	public Boolean checkVote(Long courseMealId, Long userId) {
		
		Integer result = courseOrderVoteDAO.getVote(courseMealId, userId);
		if(result == null || result == 0){
			return false;
		}
		
		return true;
	}


	@Override
	public Map getCourseOrderVoteDetail(Long orderId) {
		
		Map map = courseOrderVoteDAO.getCourseOrderVoteDetail(orderId);
		if(map != null){
			
			map.put("user_img_url", VariableHelper.IMAGE_VISIT_URL+map.get("user_img_url")
					+ QiNiuStorageHelper.MODEL0+"w/300/h/300");
			
			
			/**
			 * 投票数
			 */
			int voteCount = Integer.parseInt(map.get("vote_count").toString());
			map.put("vote_unit", voteCount%10);
			map.put("vote_ten", (voteCount/10)%10);
			map.put("vote_hundred", (voteCount/100)%10);
			return map;
			
		}
		
		return null;
	}
	
	
	
}
