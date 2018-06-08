package com.lunchtasting.server.biz.friend;

import java.util.List;

public interface FriendBIZ {

	/**
	 * 添加好友
	 * @param srcUserId
	 * @param desUserId
	 * @return
	 */
	Boolean addFriend(Long srcUserId,Long desUserId);
	
	/**
	 * 删除好友
	 * @param srcUserId
	 * @param desUserId
	 * @return
	 */
	Boolean removeFriend(Long srcUserId,Long desUserId);
	
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
	 * @param loginUserId = userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryFollowList(Long userId,Long desUserId,Long loginUserId,Integer page,Integer pageSize);
	
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
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryFansList(Long userId,Long desUserId,Integer page,Integer pageSize);
	
	/**
	 * 判断是否关注过
	 * @param srcUserId
	 * @param desUserId
	 * @return
	 */
	Boolean checkFollow(Long srcUserId,Long desUserId);
}
