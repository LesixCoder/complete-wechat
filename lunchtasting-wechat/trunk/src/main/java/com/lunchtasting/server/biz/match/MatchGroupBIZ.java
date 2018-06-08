package com.lunchtasting.server.biz.match;

import java.util.Map;

import com.lunchtasting.server.po.lt.MatchGroup;

public interface MatchGroupBIZ {
	
	void createMatchGroup(String name,Long matchId,Long userId);
	
	/**
	 * 判断用户是否参加某次赛事组队
	 * @param matchId
	 * @param userId
	 * @return
	 */
	Map getMatchGroupUser(Long matchId,Long userId);
	
	/**
	 * 创建组队(创建小组表 和小组成员表)
	 * @param matchId
	 * @param userId
	 * @param inviteUserId
	 * @param invateOpenId
	 * @return
	 * @throws Exception
	 */
	Boolean addTeam(Long matchId,Long userId,Long inviteUserId,String inviteOpenId) throws Exception;
	
	/**
	 * 获得参赛用户的队友信息
	 * @param groupId
	 * @param userId
	 * @return
	 */
	Map getGroupUserOther(Long groupId,Long userId);
}
