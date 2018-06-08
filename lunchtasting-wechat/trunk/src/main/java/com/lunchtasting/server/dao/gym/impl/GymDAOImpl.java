package com.lunchtasting.server.dao.gym.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.gym.Gym;
import com.lunchtasting.server.dao.gym.GymDAO;

@Repository
public class GymDAOImpl extends GenericDAOSupport<Long, Gym> implements GymDAO {

	@Override
	protected Class<?> getObjectClass() {
		return Gym.class;
	}

	@Override
	protected String getTableName() {
		return "gym";
	}

	@Override
	public List queryGymList() {
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryGymList");
	}

	@Override
	public Map getGymDetail(Long gymId) {
		if(gymId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("gymId", gymId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getGymDetail", map);
	}

	@Override
	public List queryCourseGymList(Long courseId,Integer page,Integer pageSize) {
		if(courseId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("courseId", courseId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCourseGymList", map);	
	}

}
