package com.lunchtasting.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.AdminCourseTemporaryDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.CourseTemporary;
@Repository
public class AdminCourseTemporaryDaoImpl extends GenericDAOSupport<Long,CourseTemporary> implements AdminCourseTemporaryDao{

	@Override
	public List queryCourseTemporaryList() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCourseTemporary");
	}

	@Override
	public Long addCourse(CourseTemporary courseTemporary) {
		// TODO Auto-generated method stub
		return  (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create",courseTemporary);
	}

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return CourseTemporary.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "course_temporary";
	}

}
