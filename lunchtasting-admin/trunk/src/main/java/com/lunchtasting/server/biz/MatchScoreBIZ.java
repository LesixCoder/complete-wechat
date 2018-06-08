package com.lunchtasting.server.biz;

public interface MatchScoreBIZ {
	
	boolean create(String tel,Long matchId,Integer rank,Integer score);
	
	boolean activityScore(String tel,Long matchId,Integer score);
	
}
