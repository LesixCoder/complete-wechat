package com.lunchtasting.server.dao.match;

import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.MatchGroup;

public interface MatchGroupDAO extends GenericDAO<MatchGroup>{
	
	/**
	 * 创建小组成员
	 * @param id
	 * @param groupId
	 * @param userId
	 * @param matchId
	 */
	void createMatchGroupUser(Long id,Long groupId,Long userId,Long matchId);
	
	/**
	 * 更具user_id和matchId查找MatchGroup
	 * @param MatchGroupId
	 * @param userId
	 * @return
	 */
	Map getMatchGroupUser(Long matchId,Long userId);
	
	/**
	 * 获得参赛用户的队友信息
	 * @param groupId
	 * @param userId
	 * @return
	 */
	Map getGroupUserOther(Long groupId,Long userId);
}
