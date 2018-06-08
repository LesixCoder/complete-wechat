package com.lunchtasting.server.dao.topic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.topic.TopicDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Topic;

@Repository
public class TopicDAOImpl extends GenericDAOSupport<Long,Topic>implements TopicDAO{

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
	public List queryTopicList(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryTopicList", map);
	}

	@Override
	public Integer getTopicCount() {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getTopicCount", map);
	}

	@Override
	public List queryTopicListByNote(Long noteId) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("noteId", noteId);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryTopicListByNote", map);
	}

	@Override
	public void createConnect(Long noteId, Long topicId) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("noteId", noteId);
		map.put("topicId", topicId);
		getSqlMapClientTemplate().insert(getNamespace() + ".createConnect", map);
	}
}
