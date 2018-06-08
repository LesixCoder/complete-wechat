package com.lunchtasting.server.dao.community.impl;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.community.CommunityCommentDAO;
import com.lunchtasting.server.dao.community.CommunityDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.CommunityComment;

@Repository
public class CommunityCommentDAOImpl extends GenericDAOSupport<Long, CommunityComment> implements CommunityCommentDAO {

	@Override
	protected Class<?> getObjectClass() {
		return CommunityComment.class;
	}

	@Override
	protected String getTableName() {
		return "community_comment";
	}

}
