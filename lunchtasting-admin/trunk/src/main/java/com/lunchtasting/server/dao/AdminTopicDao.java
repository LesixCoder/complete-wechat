package com.lunchtasting.server.dao;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Topic;

public interface AdminTopicDao extends GenericDAO<Topic>{
	List queryTopicList(HashMap map);
	int getTopicCount(HashMap map);
	int updateTopic(HashMap map);
	Long addTopic(Topic topic);
	Long updateTopic(Topic topic);
	Topic queryTopicById(String id);
	int queryNameCount(String name);
	List queryTopicNotLimit();
	Long saveTNMiddle(Long topicId,Long noteId);
	Long updateTNMiddle(Long topicId,Long noteId);
}
