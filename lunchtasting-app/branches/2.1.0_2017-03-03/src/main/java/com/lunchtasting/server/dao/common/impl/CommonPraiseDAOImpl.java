package com.lunchtasting.server.dao.common.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.common.CommonCollectDAO;
import com.lunchtasting.server.dao.common.CommonPraiseDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.CommonPraise;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class CommonPraiseDAOImpl extends GenericDAOSupport<Long, CommonPraise> implements CommonPraiseDAO {

	@Override
	protected Class<?> getObjectClass() {
		return CommonPraise.class;
	}

	@Override
	protected String getTableName() {
		return "common_praise";
	}

	@Override
	public Long getPraiseId(Long userId, Long bizId, Integer bizType) {
		if(ValidatorHelper.isEmpty(userId) || ValidatorHelper.isEmpty(bizId)
				|| ValidatorHelper.isEmpty(bizType)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("bizId", bizId);
		map.put("bizType", bizType);
		return (Long) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getPraiseId", map);
	}

}
