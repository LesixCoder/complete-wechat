package com.lunchtasting.server.dao.match;

import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.MatchVote;

public interface MatchVoteDAO extends GenericDAO<MatchVote> {
	
	/**
	 * 判断用户是否已经投票
	 * @param orderId
	 * @param userId
	 * @return
	 */
	Integer getIsVote(Long orderId, Long userId);
	
	/**
	 * 获得参赛付款用户投票详情
	 * @param orderId
	 * @param userId
	 * @return
	 */
	Map getMatchVoteDetail(Long orderId,Long userId);
}
