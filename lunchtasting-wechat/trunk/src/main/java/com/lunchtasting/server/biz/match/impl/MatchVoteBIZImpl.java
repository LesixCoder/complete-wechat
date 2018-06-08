package com.lunchtasting.server.biz.match.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.match.MatchVoteBIZ;
import com.lunchtasting.server.dao.match.MatchVoteDAO;
import com.lunchtasting.server.po.lt.MatchVote;
import com.lunchtasting.server.util.IdWorker;

@Service
public class MatchVoteBIZImpl implements MatchVoteBIZ {

	@Autowired
	private MatchVoteDAO matchVoteDAO;
	
	@Override
	public void createMatchVote(Long orderId, Long userId) {
		MatchVote vote = new MatchVote();
		vote.setId(IdWorker.getId());
		vote.setOrderId(orderId);
		vote.setUserId(userId);
		matchVoteDAO.create(vote);
	}
	
	@Override
	public boolean checkIsVote(Long orderId, Long userId) {
		Integer result = matchVoteDAO.getIsVote(orderId, userId);
		if(result != null & result > 0){
			return true;
		}
		return false;
	}

	@Override
	public Map getMatchVoteDetail(Long orderId, Long userId) {
		return matchVoteDAO.getMatchVoteDetail(orderId, userId);
	}
	
}
