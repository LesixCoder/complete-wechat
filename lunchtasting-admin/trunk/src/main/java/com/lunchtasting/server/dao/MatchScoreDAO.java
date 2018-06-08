package com.lunchtasting.server.dao;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.MatchScore;

public interface MatchScoreDAO extends GenericDAO<MatchScore>{
	
	Integer addUserScore(Long userId,String tel,Long matchId,Integer score,Integer bizType);
}
