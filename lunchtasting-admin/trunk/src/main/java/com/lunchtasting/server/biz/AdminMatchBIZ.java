package com.lunchtasting.server.biz;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.Match;

public interface AdminMatchBIZ {
	Long saveMatch(Match match);
	Long updateMatch(Match match);
	HashMap queryMatchList(HashMap map);
	Match queryMatchById(String id);
	int queryMatchCount(HashMap map);
	int deleteMatch(HashMap map);
	int topMatch(HashMap map);
	List queryMatchNotLimit();
}
