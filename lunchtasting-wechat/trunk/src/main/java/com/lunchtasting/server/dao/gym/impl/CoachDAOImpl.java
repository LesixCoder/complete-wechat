package com.lunchtasting.server.dao.gym.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.gym.CoachDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.gym.Coach;

@Repository
public class CoachDAOImpl extends GenericDAOSupport<Long, Coach> implements CoachDAO{

	@Override
	protected Class<?> getObjectClass() {
		return Coach.class;
	}

	@Override
	protected String getTableName() {
		return "coach";
	}

	@Override
	public Map getCoachDetail(Long coachId) {
		if(coachId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("coachId", coachId);
		return (Map) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getCoachDetail",map);		
	}

	@Override
	public List queryCoachAlbumList(Long coachId, Integer page, Integer pageSize) {
		if(coachId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("coachId", coachId);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCoachAlbumList",map);			}

}
