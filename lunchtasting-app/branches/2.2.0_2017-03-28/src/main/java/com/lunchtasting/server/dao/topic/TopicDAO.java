package com.lunchtasting.server.dao.topic;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Topic;

public interface TopicDAO extends GenericDAO<Topic>{
	/**
	 * 查询所有topic列表
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryTopicList(Integer page,Integer pageSize);
	/**
	 * 查询话题总数
	 * @return
	 */
	Integer getTopicCount();
	/**
	 * 根据noteId得到所对应标签
	 * @param noteId
	 * @return
	 */
	List queryTopicListByNote(Long noteId);
	/**
	 * 新增中间表
	 * @param noteId
	 * @param topicId
	 */
	void createConnect(Long noteId,Long topicId);
}
