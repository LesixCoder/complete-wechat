package com.lunchtasting.server.biz.match.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunchtasting.server.biz.match.MatchGroupBIZ;
import com.lunchtasting.server.dao.match.MatchGroupDAO;
import com.lunchtasting.server.po.lt.MatchGroup;
import com.lunchtasting.server.util.IdWorker;

@Transactional(rollbackFor = Throwable.class)
@Service
public class MatchGroupBIZImpl implements MatchGroupBIZ{
	
	@Autowired
	private MatchGroupDAO matchGroupDAO;

	@Override
	public void createMatchGroup(String name, Long matchId, Long userId) {
		MatchGroup matchGroup = new MatchGroup();
		Long id = IdWorker.getId();
		matchGroup.setId(id);
		matchGroup.setMatchId(matchId);
		matchGroup.setName(name);
		matchGroup.setUserId(userId);
		matchGroupDAO.create(matchGroup);
	}

	@Override
	public Map getMatchGroupUser(Long matchId, Long userId) {
		return matchGroupDAO.getMatchGroupUser(matchId, userId);
	}

	@Override
	public Boolean addTeam(Long matchId, Long userId, Long inviteUserId,String inviteOpenId)
			throws Exception {
		if(matchId == null || userId == null || inviteUserId == null){
			return false;
		}
		
		IdWorker idWorker = new IdWorker(0, 0);
		/**
		 * 小组表
		 */
		MatchGroup group = new MatchGroup();
		Long groupId = idWorker.nextId();
		group.setId(groupId);
		group.setMatchId(matchId);
		group.setUserId(inviteUserId);
		matchGroupDAO.create(group);
		
		/**
		 * 小组成员表
		 */
		matchGroupDAO.createMatchGroupUser(idWorker.nextId(),groupId, inviteUserId,matchId);
		matchGroupDAO.createMatchGroupUser(idWorker.nextId(),groupId,userId,matchId);
		
		/**
		 * 通知邀请方对方已经接受邀请，开启虐狗模式吧（消息模板）
		 */
		
		return true;
	}

	@Override
	public Map getGroupUserOther(Long groupId, Long userId) {
		return matchGroupDAO.getGroupUserOther(groupId, userId);
	}
}
