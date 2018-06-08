package com.lunchtasting.server.dao.message.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.message.MessageNotificationDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.MessageNotification;

@Repository
public class MessageNotificationDAOImpl extends
		GenericDAOSupport<Long, MessageNotification> implements
		MessageNotificationDAO {

	@Override
	protected Class<?> getObjectClass() {
		return MessageNotification.class;
	}

	@Override
	protected String getTableName() {
		return "message_notification";
	}

	@Override
	public Integer getNotificationCount(Long userId) {
		if (userId == null) {
			return 0;
		}

		Map map = new HashMap();
		map.put("userId", userId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				getNamespace() + ".getNotificationCount", map);
	}

	@Override
	public List queryNotificationList(Long userId, Integer page,
			Integer pageSize) {
		if (userId == null) {
			return null;
		}

		Map map = new HashMap();
		map.put("userId", userId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(
				getNamespace() + ".queryNotificationList", map);
	}

	@Override
	public Integer getCommentNotificationCount(Long userId) {
		if (userId == null) {
			return 0;
		}

		Map map = new HashMap();
		map.put("userId", userId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				getNamespace() + ".getCommentNotificationCount", map);

	}

	@Override
	public List queryCommentNotificationList(Long userId, Integer page,
			Integer pageSize) {
		if (userId == null) {
			return null;
		}

		Map map = new HashMap();
		map.put("userId", userId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(
				getNamespace() + ".queryCommentNotificationList", map);
	}

	@Override
	public Integer getLikeNotificationCount(Long userId) {
		if (userId == null) {
			return 0;
		}

		Map map = new HashMap();
		map.put("userId", userId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				getNamespace() + ".getLikeNotificationCount", map);
	}

	@Override
	public List queryLikeNotificationList(Long userId, Integer page,
			Integer pageSize) {
		if (userId == null) {
			return null;
		}

		Map map = new HashMap();
		map.put("userId", userId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(
				getNamespace() + ".queryLikeNotificationList", map);
	}

	@Override
	public Integer getUnreadNotificationCount(Long userId) {
		if (userId == null) {
			return 0;
		}

		Map map = new HashMap();
		map.put("userId", userId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				getNamespace() + ".getUnreadNotificationCount", map);
	}

	@Override
	public Map getUnreadMap(Long userId) {
		if(userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return (Map) getSqlMapClientTemplate().queryForObject(
				getNamespace() + ".getUnreadMap", map);	
	}

	@Override
	public void updateIsRead(Long userId, Integer bizType) {
		if(userId == null || bizType == null){
			return;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("bizType", bizType);
		getSqlMapClientTemplate().update(getNamespace() + ".updateIsRead", map);
	}

	@Override
	public void updateNotifyIsRead(Long userId) {
		if(userId == null){
			return;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		getSqlMapClientTemplate().update(getNamespace() + ".updateNotifyIsRead", map);
	}

	@Override
	public Integer getSameFriendCount(Long srcUserId, Long desUserId) {
		Map map = new HashMap();
		map.put("srcUserId", srcUserId);
		map.put("desUserId", desUserId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				getNamespace() + ".getSameFriendCount", map);		
	}

	@Override
	public Integer getSameNoteLikeCount(Long srcUserId, Long desUserId,
			Long bizId) {
		Map map = new HashMap();
		map.put("srcUserId", srcUserId);
		map.put("desUserId", desUserId);
		map.put("bizId", bizId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				getNamespace() + ".getSameNoteLikeCount", map);			
	}

}
