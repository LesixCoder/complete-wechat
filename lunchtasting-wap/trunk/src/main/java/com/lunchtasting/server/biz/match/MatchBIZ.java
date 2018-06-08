package com.lunchtasting.server.biz.match;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.po.lt.Match;

public interface MatchBIZ {
	
	/**
	 * 查看赛事详情页
	 * @param matchId
	 * @param userId
	 * @return
	 */
	Map getMatchDetail(Long matchId) throws Exception;
	
}
