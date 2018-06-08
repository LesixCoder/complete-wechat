package com.lunchtasting.server.biz.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.AdminTopicBIZ;
import com.lunchtasting.server.dao.AdminTopicDao;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.po.lt.Topic;
@Service
public class AdminTopicBIZImpl implements AdminTopicBIZ{
	
	@Autowired
	private AdminTopicDao adminTopicDao;
	
	@Override
	public HashMap queryTopicList(HashMap map) {
		HashMap result = new HashMap();
	    List<Topic> topicList;
	    int totalCount;
    	 try{
    		 topicList = adminTopicDao.queryTopicList(map);
    		 for(int i = 0;i<topicList.size();i++){
    			 topicList.get(i).setNewId(topicList.get(i).getId().toString());
    		 }
    		 totalCount = adminTopicDao.getTopicCount(map);
    	 }catch(Exception e){
			 e.printStackTrace();
			 result.put("result","1");
		     result.put("descript","查询失败");
		     result.put("totalCount",0);
		     result.put("page",new PageBean(null, Integer.parseInt(map.get("curPage").toString()), 0, Integer.parseInt(map.get("pageSize").toString())));
		     return result;
    	 }
    	 if(topicList != null && topicList.size() > 0){
    		 result.put("result","0");
		     result.put("descript","查询成功");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(topicList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }else{
    		 result.put("result","0");
		     result.put("descript","没有更多");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(topicList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }
    	 return result;
	}

	@Override
	public int updateTopic(HashMap map) {
		// TODO Auto-generated method stub
		return adminTopicDao.updateTopic(map);
	}

	@Override
	public Long addTopic(Topic topic) {
		// TODO Auto-generated method stub
		return adminTopicDao.addTopic(topic);
	}

	@Override
	public Long updateTopic(Topic topic) {
		// TODO Auto-generated method stub
		return adminTopicDao.updateTopic(topic);
	}

	@Override
	public Topic queryTopicById(String id) {
		// TODO Auto-generated method stub
		return adminTopicDao.queryTopicById(id);
	}

	@Override
	public int queryNameCount(String name) {
		// TODO Auto-generated method stub
		return adminTopicDao.queryNameCount(name);
	}

	@Override
	public List queryTopicNotLimit() {
		// TODO Auto-generated method stub
		return adminTopicDao.queryTopicNotLimit();
	}

	@Override
	public Long saveTNMiddle(Long topicId, Long noteId) {
		// TODO Auto-generated method stub
		return adminTopicDao.saveTNMiddle(topicId, noteId);
	}

	@Override
	public Long updateTNMiddle(Long topicId, Long noteId) {
		// TODO Auto-generated method stub
		return adminTopicDao.updateTNMiddle(topicId, noteId);
	}

}
