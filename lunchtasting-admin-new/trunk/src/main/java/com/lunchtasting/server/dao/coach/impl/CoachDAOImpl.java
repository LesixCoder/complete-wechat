package com.lunchtasting.server.dao.coach.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.coach.CoachDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.UserAdmin;
import com.lunchtasting.server.po.lt.gym.Coach;

@Repository
public class CoachDAOImpl extends GenericDAOSupport<Long,Coach> 
	implements CoachDAO {

	@Override
	protected Class<?> getObjectClass() {
		return Coach.class;
	}

	@Override
	protected String getTableName() {
		return "coach";
	}

	@Override
	public List queryCoachList(Integer page, Integer pageSize) {
		Map map = new HashMap();
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCoachList", map);
	}
	
	@Override
	public List querySimpleCoachList() {
		 return getSqlMapClientTemplate().queryForList(getNamespace() + ".querySimpleCoachList");
	}

	@Override
	public Map getEditCoach(Long coachId) {
		if(coachId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("coachId", coachId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getEditCoach", map);	
	}

	@Override
	public Integer updateFlag(Long coachId, Integer flag) {
		if(coachId == null || flag == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("coachId", coachId);
		map.put("flag", flag);
		return (Integer)getSqlMapClientTemplate().update(getNamespace() + ".updateFlag", map);
	}

	@Override
	public List queryCoachAlbumList(Long coachId, Integer page, Integer pageSize) {
		if(coachId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("coachId", coachId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCoachAlbumList",map);
	}

	@Override
	public void addCoachAlbum(Map map) {
		if(map == null){
			return;
		}
		getSqlMapClientTemplate().insert(getNamespace() + ".addCoachAlbum",map);
	}

	@Override
	public void removeCoachAlbum(Long coachAlbumId) {
		if(coachAlbumId == null){
			return;
		}
		Map map = new HashMap();
		map.put("coachAlbumId", coachAlbumId);
		getSqlMapClientTemplate().delete(getNamespace() + ".removeCoachAlbum",map);
	}
}
