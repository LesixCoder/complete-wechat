package com.lunchtasting.server.dao.friend.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.friend.FriendDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Friend;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class FriendDAOImpl extends GenericDAOSupport<Long, Friend> implements FriendDAO {

	@Override
	protected Class<?> getObjectClass() {
		return Friend.class;
	}

	@Override
	protected String getTableName() {
		return "friend";
	}

	@Override
	public Integer removeFriend(Long srcUserId, Long desUserId) {
		if(srcUserId == null || desUserId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("srcUserId", srcUserId);
		map.put("desUserId", desUserId);
		return getSqlMapClientTemplate().delete(getNamespace() + ".removeFriend", map);
	}

	@Override
	public Integer getFollowCount(Long userId) {
		if(userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getFollowCount", map);
	}

	@Override
	public List queryFollowList(Long userId,Long desUserId, Integer page, Integer pageSize) {
		if(desUserId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("desUserId", desUserId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryFollowList", map);
	}

	@Override
	public Integer getFansCount(Long userId) {
		if(userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getFansCount", map);
	}

	@Override
	public List queryFansList(Long userId,Long desUserId, Integer page, Integer pageSize) {
		if(desUserId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("desUserId", desUserId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryFansList", map);
	}

	@Override
	public String getFollow(Long srcUserId, Long desUserId) {
		if(srcUserId == null || desUserId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("srcUserId", srcUserId);
		map.put("desUserId", desUserId);
		return (String)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getFollow", map);
	}

}
