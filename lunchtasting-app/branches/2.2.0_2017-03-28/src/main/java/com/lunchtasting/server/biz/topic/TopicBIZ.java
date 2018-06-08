package com.lunchtasting.server.biz.topic;

import java.util.List;

public interface TopicBIZ {
	/**
	 * 查询话题列表
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List queryTopicList(Integer page,Integer pageSize);
	/**
	 * 查询话题列表+全部
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List queryTopicListAndIndex(Integer page,Integer pageSize);
	/**
	 * 查询话题总数
	 * @return
	 */
	public Integer getTopicCount();
	/**
	 * 新建话题
	 * @param name
	 * @param type
	 * @return
	 */
	public Boolean createTopic(String name , Integer type);
}
