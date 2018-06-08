package com.lunchtasting.server.dao.friend;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Friend;

public interface FriendDAO extends GenericDAO<Friend>  {

	/**
	 * 删除好友
	 * @param srcUserId
	 * @param desUserId
	 * @return
	 */
	Integer removeFriend(Long srcUserId,Long desUserId);
	
	/**
	 * 关注好友总数
	 * @param userId
	 * @return
	 */
	Integer getFollowCount(Long userId);
	
	/**
	 * 关注的好友列表
	 * @param userId 登录用户id
	 * @param desUserId 查看的用户id
	 * @param isLoginUser
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryFollowList(Long userId,Long desUserId,Integer isLoginUser,Integer page,Integer pageSize);
	
	/**
	 * 粉丝好友总数
	 * @param userId
	 * @return
	 */
	Integer getFansCount(Long userId);
	
	/**
	 * 粉丝好友列表
	 * @param userId 登录用户id
	 * @param desUserId 查看的用户id
	 * @param isLoginUser
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryFansList(Long userId,Long desUserId,Integer isLoginUser,Integer page,Integer pageSize);
	
	/**
	 * 判断是否关注过
	 * @param srcUserId
	 * @param desUserId
	 * @return
	 */
	String getFollow(Long srcUserId,Long desUserId);
}
