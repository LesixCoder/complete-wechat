package com.lunchtasting.server.dao.course.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.course.CourseMealDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.CourseMeal;

@Repository
public class CourseMealDAOImpl extends GenericDAOSupport<Long,CourseMeal> 
	implements CourseMealDAO {

	@Override
	protected Class<?> getObjectClass() {
		return CourseMeal.class;
	}

	@Override
	protected String getTableName() {
		return "course_meal";
	}

	@Override
	public List queryCourseMealList(Long courseMealId, Integer page,
			Integer pageSize) {
		if(courseMealId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("courseMealId", courseMealId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCourseMealList", map);
	}

	@Override
	public Map getEditCourseMeal(Long courseMealId) {
		if(courseMealId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("courseMealId", courseMealId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getEditCourseMeal", map);	
	}

	@Override
	public Integer updateFlag(Long courseMealId, Integer flag) {
		if(courseMealId == null || flag == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("courseMealId", courseMealId);
		map.put("flag", flag);
		return (Integer)getSqlMapClientTemplate().update(getNamespace() + ".updateFlag", map);
	}

}
