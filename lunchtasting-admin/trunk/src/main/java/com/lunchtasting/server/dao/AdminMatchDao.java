package com.lunchtasting.server.dao;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Match;

public interface AdminMatchDao extends GenericDAO<Match>{
	Long saveMatch(Match match);
	Long updateMatch(Match match);
	List queryMatchList(HashMap map);
	Match queryMatchById(String id);
	int queryMatchCount(HashMap map);
	int deleteMatch(HashMap map);
	int topMatch(HashMap map);
	List queryMatchNotLimit();
}
