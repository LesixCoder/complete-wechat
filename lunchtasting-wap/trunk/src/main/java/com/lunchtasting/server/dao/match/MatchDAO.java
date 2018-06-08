package com.lunchtasting.server.dao.match;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Match;

public interface MatchDAO extends GenericDAO<Match> {
	
	/**
	 * 查看赛事详情页
	 * @param matchId
	 * @param userId
	 * @return
	 */
	Map getMatchDetail(Long matchId);
	
}
