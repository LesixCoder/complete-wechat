package com.lunchtasting.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.lunchtasting.server.dao.AdminCourseDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Course;
@Repository
public class AdminCourseDaoImpl extends GenericDAOSupport<Long,Course> implements AdminCourseDao{

	@Override
	public List queryCourseList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCourseList",map);
	}

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
	public int getCourseCount(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryCourseCount",map);
	}

	@Override
	public int updateCourse(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update(getNamespace() + ".updateCourse",map);
	}

	@Override
	public Long addCourse(Course course) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create",course);
	}

	@Override
	public Long updateCourse(Course course) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".update",course);
	}

	@Override
	public Course queryCourseById(String id) {
		Map map = new HashMap();
		map.put("id", id);
		return (Course) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryCourseById",map);
	}

	@Override
	public List queryCourseNotLimit() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCourseNotLimit");
	}

}
