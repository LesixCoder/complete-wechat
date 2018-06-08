package com.lunchtasting.server.dao.activity.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.activity.ActivityCommentDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.ActivityComment;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class ActivityCommentDAOImpl extends GenericDAOSupport<Long, ActivityComment> implements ActivityCommentDAO  {

	@Override
	protected Class<?> getObjectClass() {
		return ActivityComment.class;
	}

	@Override
	protected String getTableName() {
		return "activity_comment";
	}

	@Override
	public Integer getCommentCount(Long activityId) {
		if(ValidatorHelper.isEmpty(activityId)){
			return null;
		}
		Map map = new HashMap();
		map.put("activityId", activityId);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getCommentCount",map);	
	}

	@Override
	public List queryCommentList(Long activityId, Integer page, Integer pageSize) {
		if(ValidatorHelper.isEmpty(activityId)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("activityId", activityId);
		map.put("page", page);
		map.put("pageSize", pageSize);
        return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCommentList", map);
	}

}
