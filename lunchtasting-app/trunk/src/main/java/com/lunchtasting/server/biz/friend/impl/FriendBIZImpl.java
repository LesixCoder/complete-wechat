package com.lunchtasting.server.biz.friend.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.friend.FriendBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.friend.FriendDAO;
import com.lunchtasting.server.dao.user.UserHotDAO;
import com.lunchtasting.server.enumeration.SysEnum;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.po.lt.Friend;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class FriendBIZImpl implements FriendBIZ {

	@Autowired
	private FriendDAO friendDAO;
	@Autowired
	private UserHotDAO userHotDAO;

	@Override
	public Boolean addFriend(Long srcUserId, Long desUserId) {
		if(srcUserId == null || desUserId == null){
			return false;
		}
		
		Friend friend = new Friend();
		friend.setId(IdWorker.getId());
		friend.setSrcUserId(srcUserId);
		friend.setDesUserId(desUserId);
		friendDAO.create(friend);
		if(userHotDAO.getTypeCount(srcUserId,SysEnum.UserHotType.FOLLOW_FRIEND.getType())<20){
			userHotDAO.createUserHot(srcUserId, SysEnum.UserHotType.FOLLOW_FRIEND.getNumber(), SysEnum.UserHotType.FOLLOW_FRIEND.getType());
		}
		return true;
	}

	@Override
	public Boolean removeFriend(Long srcUserId, Long desUserId) {
		Integer result = friendDAO.removeFriend(srcUserId, desUserId);
		if(result != null && result > 0){
			return true;
		}
		return false;
	}

	@Override
	public Integer getFollowCount(Long userId) {
		return friendDAO.getFollowCount(userId);
	}

	@Override
	public List queryFollowList(Long userId,Long desUserId,Integer isLoginUser,Integer page, Integer pageSize) {
		List list = friendDAO.queryFollowList(userId,desUserId,isLoginUser, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				map.put("user_img_url", SysConfig.IMG_VISIT_URL+map.get("user_img_url")
						+ QiNiuStorageHelper.MODEL1+"w/80/h/80");
				
				/**
				 * 1. 看自己的关注列表，那肯定都关注过，直接设为1（已 ）
				 * 2. 看别人的关注的列表
				 */
				if(isLoginUser != null){
					map.put("is_follow", 1);
				}else{
					if(ValidatorHelper.isMapNotEmpty(map.get("is_follow"))){
						map.put("is_follow", 1);
					}else{
						map.put("is_follow", 0);
					}
				}
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	}

	@Override
	public Integer getFansCount(Long userId) {
		return friendDAO.getFansCount(userId);
	}

	@Override
	public List queryFansList(Long userId,Long desUserId,Integer isLoginUser, Integer page, Integer pageSize) {
		List list = friendDAO.queryFansList(userId,desUserId,isLoginUser, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				map.put("user_img_url", SysConfig.IMG_VISIT_URL+map.get("user_img_url")
						+ QiNiuStorageHelper.MODEL1+"w/80/h/80");
				
				/**
				 * 是否关注
				 */
				if(ValidatorHelper.isMapNotEmpty(map.get("is_follow"))){
					map.put("is_follow", 1);
				}else{
					map.put("is_follow", 0);
				}
				
				/**
				 * 是否新好友
				 */
				if(isLoginUser != null){
					if(ValidatorHelper.isMapNotEmpty(map.get("is_new"))){
						map.put("is_new", 1);
					}else{
						map.put("is_new", 0);
					}
				}
				newList.add(map);
			}
			return newList;
		}
		return list;
	}

	@Override
	public Boolean checkFollow(Long srcUserId, Long desUserId) {
		if(srcUserId == null){
			return true;
		}
		String id = friendDAO.getFollow(srcUserId, desUserId);
		if(ValidatorHelper.isEmpty(id)){
			return false;
		}
		return true;
	} 
	
}
