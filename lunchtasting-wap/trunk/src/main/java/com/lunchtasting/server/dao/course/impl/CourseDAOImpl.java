package com.lunchtasting.server.dao.course.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.course.CourseDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Course;
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
	public Map getCourseDetail(Long id) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("id", id);
		return (Map) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getCourseDetail", map);
	}
}
