package com.lunchtasting.server.dao.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.common.CommonCollectDAO;
import com.lunchtasting.server.dao.user.UserDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.CommonCollect;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class CommonCollectDAOImpl extends GenericDAOSupport<Long, CommonCollect> implements CommonCollectDAO {

	@Override
	protected Class<?> getObjectClass() {
		return CommonCollect.class;
	}

	@Override
	protected String getTableName() {
		return "common_collect";
	}

	@Override
	public Long getCollectId(Long userId, Long bizId, Integer bizType) {
		if(ValidatorHelper.isEmpty(userId) || ValidatorHelper.isEmpty(bizId)
				|| ValidatorHelper.isEmpty(bizType)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("bizId", bizId);
		map.put("bizType", bizType);
		return (Long) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getCollectId", map);
	}

	@Override
	public Integer getUserCollectCount(Long userId,Integer bizType) {
		if(null == userId){
			return 0;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("bizType", bizType);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserCollectCount", map);
	}

	@Override
	public List queryActivtyCollectList(Long userId, Integer page, Integer pageSize) {
		if(null == userId){
			return null;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("page",page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryActivtyCollectList", map);	
	}

	@Override
	public List queryArticleCollectList(Long userId, Integer page,
			Integer pageSize) {
		if(null == userId){
			return null;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("page",page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryArticleCollectList", map);	
	}

	@Override
	public Integer deleteCollect(Long userId, Integer bizType, String ids) {
		if(userId == null || bizType == null || ValidatorHelper.isEmpty(ids)){
			return 0;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("bizType", bizType);
		map.put("ids", ids);
		return getSqlMapClientTemplate().delete(getNamespace() + ".deleteCollect", map);	
	}

}
