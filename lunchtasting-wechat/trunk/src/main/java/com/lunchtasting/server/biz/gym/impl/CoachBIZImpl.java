package com.lunchtasting.server.biz.gym.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.gym.CoachBIZ;
import com.lunchtasting.server.dao.gym.CoachDAO;

@Service
public class CoachBIZImpl implements CoachBIZ {
	
	@Autowired
	private CoachDAO coachDAO;

	@Override
	public Map getCoachDetail(Long coachId) {
		return coachDAO.getCoachDetail(coachId);
	}

	@Override
	public List queryCoachAlbumList(Long coachId, Integer page, Integer pageSize) {
		return coachDAO.queryCoachAlbumList(coachId, page, pageSize);
	}
	
}
