package com.lunchtasting.server.biz.match;

import java.util.Map;

public interface MatchVoteBIZ {
	
	
	void createMatchVote(Long orderId,Long userId);
	
	/**
	 * 判断用户是否已经投过票
	 * @param orderId
	 * @param userId
	 * @return
	 */
	boolean checkIsVote(Long orderId, Long userId);
	
	/**
	 * 获得参赛付款用户投票详情
	 * @param orderId
	 * @param userId
	 * @return
	 */
	Map getMatchVoteDetail(Long orderId,Long userId);
}
