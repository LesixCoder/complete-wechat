package com.lunchtasting.server.dao.course.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.course.CourseDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Course;

@Repository
public class CourseDAOImpl extends GenericDAOSupport<Long,Course> 
	implements CourseDAO {

	@Override
	protected Class<?> getObjectClass() {
		return Course.class;
	}

	@Override
	protected String getTableName() {
		return "course";
	}

	@Override
	public List queryCourseList(Integer page, Integer pageSize) {
		Map map = new HashMap();
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCourseList", map);	
	}

	@Override
	public Map getEditCourse(Long courseId) {
		if(courseId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("courseId", courseId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getEditCourse", map);	
	}

	@Override
	public List queryCourseGymList(Long courseId) {
		if(courseId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("courseId", courseId);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCourseGymList", map);
	}

	@Override
	public void addCourseGym(Long id,Long courseId, Long gymId) {
		if(id == null || courseId == null || gymId == null){
			return;
		}
		
		Map map = new HashMap();
		map.put("id", id);
		map.put("courseId", courseId);
		map.put("gymId", gymId);
		getSqlMapClientTemplate().insert(getNamespace() + ".addCourseGym", map);

	}

	@Override
	public void removeCourseGym(Long id) {
		if(id == null){
			return;
		}
		Map map = new HashMap();
		map.put("id", id);
		getSqlMapClientTemplate().delete(getNamespace() + ".removeCourseGym", map);
	}

	@Override
	public Integer getCourseGym(Long courseId, Long gymId) {
		if(courseId == null || gymId == null){
			return 0;
		}
		
		Map map = new HashMap();
		map.put("courseId", courseId);
		map.put("gymId", gymId);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getCourseGym", map);
	}

	@Override
	public Integer updateCourseFlag(Long courseId, Integer flag) {
		if(courseId == null || flag == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("courseId", courseId);
		map.put("flag", flag);
		return (Integer)getSqlMapClientTemplate().update(getNamespace() + ".updateCourseFlag", map);
	}
}
