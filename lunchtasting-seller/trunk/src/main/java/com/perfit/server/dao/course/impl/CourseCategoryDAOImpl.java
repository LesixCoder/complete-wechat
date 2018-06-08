package com.perfit.server.dao.course.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.CourseCategory;
import com.perfit.server.dao.course.CourseCategoryDAO;
@Repository
public class CourseCategoryDAOImpl extends GenericDAOSupport<Long,CourseCategory> implements CourseCategoryDAO {
	
	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return CourseCategory.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "course_category";
	}

	@Override
	public List<CourseCategory> queryCourseCategoryListByHead(HashMap map) {
		// TODO Auto-generated method stub
		return (List<CourseCategory>)getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCourseCategoryListByHead",map);
	}

	@Override
	public List<CourseCategory> queryCourseCategoryListByBelow(HashMap map) {
		// TODO Auto-generated method stub
		return (List<CourseCategory>)getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCourseCategoryListByBelow",map);
	}
}
