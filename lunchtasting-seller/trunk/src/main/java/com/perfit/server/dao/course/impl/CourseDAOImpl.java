package com.perfit.server.dao.course.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Course;
import com.perfit.server.dao.course.CourseDAO;
@Repository
public class CourseDAOImpl extends GenericDAOSupport<Long,Course> implements CourseDAO{

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return Course.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "course";
	}

	@Override
	public List queryCourseList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCourseList",map);
	}

	@Override
	public int queryCourseCount(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryCourseCount",map);
	}

	@Override
	public Course courseById(Long id,Long sellerId) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("sellerId",sellerId);
		return (Course) getSqlMapClientTemplate().queryForObject(getNamespace()+ ".courseById",map);
	}

	@Override
	public Integer updateCourse(Course course) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update(getNamespace() + ".updateCourse",course);
	}
}
