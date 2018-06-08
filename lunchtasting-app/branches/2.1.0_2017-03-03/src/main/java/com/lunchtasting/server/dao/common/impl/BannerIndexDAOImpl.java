package com.lunchtasting.server.dao.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.common.BannerIndexDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.BannerIndex;

@Repository
public class BannerIndexDAOImpl extends GenericDAOSupport<Long, BannerIndex> implements BannerIndexDAO {

	@Override
	protected Class<?> getObjectClass() {
		return BannerIndex.class;
	}

	@Override
	protected String getTableName() {
		return "banner_index";
	}

	@Override
	public List queryBannerIndexList(Integer pageSize) {
		Map map = new HashMap();
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryBannerIndexList", map);	
	}

	
}
