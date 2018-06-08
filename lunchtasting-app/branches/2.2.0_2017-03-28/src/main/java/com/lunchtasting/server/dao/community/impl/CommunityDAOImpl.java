package com.lunchtasting.server.dao.community.impl;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.community.CommunityDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Community;

@Repository
public class CommunityDAOImpl extends GenericDAOSupport<Long, Community> implements CommunityDAO {

	@Override
	protected Class<?> getObjectClass() {
		return Community.class;
	}

	@Override
	protected String getTableName() {
		return "community";
	}

}
