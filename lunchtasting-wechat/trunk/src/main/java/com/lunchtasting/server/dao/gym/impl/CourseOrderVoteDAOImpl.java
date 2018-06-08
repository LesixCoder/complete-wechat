package com.lunchtasting.server.dao.gym.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.gym.CourseOrderVoteDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.CourseOrderVote;

@Repository
public class CourseOrderVoteDAOImpl extends GenericDAOSupport<Long, CourseOrderVote> 
	implements CourseOrderVoteDAO {

	@Override
	protected Class<?> getObjectClass() {
		return CourseOrderVote.class;
	}

	@Override
	protected String getTableName() {
		return "course_order_vote";
	}

	@Override
	public List queryOrderVoteList(Long courseMealId, Integer page,
			Integer pageSize) {
		if(courseMealId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("courseMealId", courseMealId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryOrderVoteList", map);				
	}

	@Override
	public Integer getOrderVoteCount(Long courseMealId) {
		if(courseMealId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("courseMealId", courseMealId);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOrderVoteCount", map);	
	}

	@Override
	public List queryUserVoteList(Long orderId,Long desUserId, Integer page, Integer pageSize) {
		if(orderId == null || desUserId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("orderId", orderId);
		map.put("desUserId", desUserId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserVoteList", map);					
	}

	@Override
	public Integer getUserVoteCount(Long orderId,Long desUserId) {
		if(orderId == null || desUserId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("orderId", orderId);
		map.put("desUserId", desUserId);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getVoteCount", map);		
	}

	@Override
	public Integer getVote(Long courseMealId, Long userId) {
		if(courseMealId == null || userId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("courseMealId", courseMealId);
		map.put("userId", userId);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getVote", map);		
	}

	@Override
	public Map getCourseOrderVoteDetail(Long orderId) {
		if(orderId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("orderId", orderId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getCourseOrderVoteDetail", map);		
	}

}
