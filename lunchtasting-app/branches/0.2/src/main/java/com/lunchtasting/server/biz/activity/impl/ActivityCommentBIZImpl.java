package com.lunchtasting.server.biz.activity.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.activity.ActivityCommentBIZ;
import com.lunchtasting.server.dao.activity.ActivityCommentDAO;
import com.lunchtasting.server.po.lt.ActivityComment;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class ActivityCommentBIZImpl implements ActivityCommentBIZ{
	
	@Autowired
	private ActivityCommentDAO commentDAO;

	@Override
	public Boolean createComment(Long activityId,Long userId, String content, String imgArray,
			String parentId,String parentUserId) {
		
		ActivityComment comment = new ActivityComment();
		comment.setActivityId(activityId);
		comment.setUserId(userId);
		comment.setContent(content);
		comment.setImgUrl(imgArray);
		if(ValidatorHelper.isNotEmpty(parentId)){
			comment.setParentId(Long.parseLong(parentId));
		}
		if(ValidatorHelper.isNotEmpty(parentUserId)){
			comment.setParentUserId(Long.parseLong(parentId));
		}

		commentDAO.create(comment);
		if(ValidatorHelper.isNotEmpty(comment.getId())){
			return true;
		}
		return false;
	}

	@Override
	public Integer getCommentCount(Long activityId) {
		return commentDAO.getCommentCount(activityId);
	}

	@Override
	public List queryCommentList(Long activityId, Long activityUserId,Integer page, Integer pageSize) {
		List list = commentDAO.queryCommentList(activityId, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);				
//				if(ValidatorHelper.isMapNotEmpty(map.get("img_url"))){
//					String imgUrl = map.get("img_url").toString();
//					StringBuffer sb = new StringBuffer();
//					for(String img : imgUrl.split(",")){
//						sb.append(img).append(",");
//					}
//					if(sb.substring(sb.length()-1, sb.length()).equals(",")){
//						sb.delete(sb.length()-1, sb.length());
//					}		
//					map.put("img_url", sb.toString());
//				}
				
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	}

}
