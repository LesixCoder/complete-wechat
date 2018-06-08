package com.lunchtasting.server.dao.community.impl;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.community.CommunityPraiseDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.CommunityPraise;

@Repository
public class CommunityPraiseDAOImpl extends GenericDAOSupport<Long, CommunityPraise> 
			implements CommunityPraiseDAO {

	@Override
	protected Class<?> getObjectClass() {
		return CommunityPraise.class;
	}

	@Override
	protected String getTableName() {
		return "community_praise";
	}

}
