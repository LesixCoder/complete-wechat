package com.lunchtasting.server.dao.course.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.course.CourseOrderDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.CourseOrder;

@Repository
public class CourseOrderDAOImpl extends GenericDAOSupport<Long,CourseOrder> 
	implements CourseOrderDAO {

	@Override
	protected Class<?> getObjectClass() {
		return CourseOrder.class;
	}

	@Override
	protected String getTableName() {
		return "course_order";
	}

	@Override
	public List queryCourseOrderList() {
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCourseOrderList");
	}

}
