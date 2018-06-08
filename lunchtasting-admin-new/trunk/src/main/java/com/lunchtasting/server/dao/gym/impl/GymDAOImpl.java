package com.lunchtasting.server.dao.gym.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.gym.GymDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.gym.Gym;

@Repository
public class GymDAOImpl extends GenericDAOSupport<Long,Gym> 
	implements GymDAO {

	@Override
	protected Class<?> getObjectClass() {
		return Gym.class;
	}

	@Override
	protected String getTableName() {
		return "gym";
	}

	@Override
	public List queryGymList(Integer flag,Integer page, Integer pageSize) {
		Map map = new HashMap();
		map.put("flag", flag);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryGymList", map);
	}

	@Override
	public List querySimpleGymList() {
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".querySimpleGymList");
	}

	@Override
	public Map getEditGym(Long gymId) {
		if(gymId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("gymId", gymId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getEditGym", map);
	}

	@Override
	public Integer updateFlag(Long gymId, Integer flag) {
		if(gymId == null || flag == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("gymId", gymId);
		map.put("flag", flag);
		return (Integer)getSqlMapClientTemplate().update(getNamespace() + ".updateFlag", map);
	}

}
