package com.lunchtasting.server.biz;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.Topic;

public interface AdminTopicBIZ {
	HashMap queryTopicList(HashMap map);
	int updateTopic(HashMap map);
	Long addTopic(Topic topic);
	Long updateTopic(Topic topic);
	Topic queryTopicById(String id);
	int queryNameCount(String name);
	List queryTopicNotLimit();
	Long saveTNMiddle(Long topicId,Long noteId);
	Long updateTNMiddle(Long topicId,Long noteId);
}
