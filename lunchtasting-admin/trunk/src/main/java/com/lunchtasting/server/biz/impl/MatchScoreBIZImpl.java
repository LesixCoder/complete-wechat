package com.lunchtasting.server.biz.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.MatchScoreBIZ;
import com.lunchtasting.server.dao.AdminUserForNoteDao;
import com.lunchtasting.server.dao.MatchScoreDAO;
import com.lunchtasting.server.po.lt.MatchScore;
import com.lunchtasting.server.util.IdWorker;
@Service
public class MatchScoreBIZImpl implements MatchScoreBIZ{
	@Autowired
	private MatchScoreDAO matchScoreDAO;
	@Autowired
	private AdminUserForNoteDao userDao;
	@Override
	public boolean create(String tel,Long matchId,Integer rank,Integer score) {
		//根据手机号查找用户ID
		Map user = userDao.getUserByPhone(tel);
		
		if (user!=null) {
			Long userId=Long.parseLong(user.get("id").toString());
			MatchScore matchScore = new MatchScore();
			matchScore.setId(IdWorker.getId());
			matchScore.setMatchId(matchId);
			matchScore.setUserId(userId);
			matchScore.setRank(rank);
    		matchScoreDAO.create(matchScore);
    		matchScoreDAO.addUserScore(userId, null, matchId, score,1);
    		return true;
		}
		MatchScore matchScore = new MatchScore();
		matchScore.setId(IdWorker.getId());
		matchScore.setMatchId(matchId);
		matchScore.setRank(rank);
		matchScore.setTel(tel);
		matchScoreDAO.create(matchScore);
		matchScoreDAO.addUserScore(null, tel, matchId, score,1);
		return true;
	}
	@Override
	public boolean activityScore(String tel, Long matchId, Integer score) {
		// TODO Auto-generated method stub
		Map user = userDao.getUserByPhone(tel);
		if (user!=null) {
			Long userId=Long.parseLong(user.get("id").toString());
			matchScoreDAO.addUserScore(userId, null, matchId, score,2);
			return true;
		}
		matchScoreDAO.addUserScore(null, tel, matchId, score,2);
		return true;
	}

}
