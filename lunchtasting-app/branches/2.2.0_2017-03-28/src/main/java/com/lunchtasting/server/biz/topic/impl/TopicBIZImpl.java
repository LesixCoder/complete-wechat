package com.lunchtasting.server.biz.topic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.topic.TopicBIZ;
import com.lunchtasting.server.dao.topic.TopicDAO;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.po.lt.Topic;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class TopicBIZImpl implements TopicBIZ{
	@Autowired
	private TopicDAO topicDAO;
	
	@Override
	public List queryTopicList(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("page",page);
		map.put("pageSize",pageSize);
		List list = topicDAO.queryTopicList(page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				Map map3 = (Map) list.get(i);
				map3.put("random", map3.get("color"));
				map3.remove("color");
				newList.add(map3);
			}
			return newList;
		}
		return list;
	}
	@Override
	public List queryTopicListAndIndex(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		List list = queryTopicList(page, pageSize);
		Map  map2 =  new HashMap();
		map2.put("id",0);
		map2.put("name","全部");
		map2.put("random",1);
		list.add(0,map2);
		return list;
	}

	@Override
	public Integer getTopicCount() {
		// TODO Auto-generated method stub
		return topicDAO.getTopicCount();
	}

	@Override
	public Boolean createTopic(String name, Integer type) {
		// TODO Auto-generated method stub
		Topic topic = new Topic();
		topic.setFlag(0);
		topic.setId(IdWorker.getId());
		topic.setName(name);
		topic.setSort(99);
		topic.setType(type);
		topicDAO.create(topic);
		return true;
	}

}
