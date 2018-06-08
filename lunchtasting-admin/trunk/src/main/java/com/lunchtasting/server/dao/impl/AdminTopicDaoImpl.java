package com.lunchtasting.server.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.AdminTopicDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Topic;
@Repository
public class AdminTopicDaoImpl extends GenericDAOSupport<Long,Topic> implements AdminTopicDao{

	@Override
	public List queryTopicList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryTopicList",map);
	}

	@Override
	public int getTopicCount(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryTopicCount",map);
	}

	@Override
	public int updateTopic(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update(getNamespace() + ".updateTopic",map);
	}

	@Override
	public Long addTopic(Topic topic) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create",topic);
	}

	@Override
	public Long updateTopic(Topic topic) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".update",topic);
	}

	@Override
	public Topic queryTopicById(String id) {
		HashMap map = new HashMap();
		map.put("id", id);
		return (Topic) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryTopicById",map);
	}

	@Override
	public int queryNameCount(String name) {
		HashMap map = new HashMap();
		map.put("name", name);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryNameCount",map);
	}

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return Topic.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "topic";
	}

	@Override
	public List queryTopicNotLimit() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryTopicNotLimit");
	}

	@Override
	public Long saveTNMiddle(Long topicId, Long noteId) {
		HashMap map = new HashMap();
		map.put("topicId", topicId);
		map.put("noteId", noteId);
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".insertTNMiddle",map);
	}

	@Override
	public Long updateTNMiddle(Long topicId, Long noteId) {
		HashMap map = new HashMap();
		map.put("topicId", topicId);
		map.put("noteId", noteId);
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".updateTNMiddle",map);
	}

}
