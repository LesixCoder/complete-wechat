package com.lunchtasting.server.dao.course;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.CourseOrder;

public interface CourseOrderDAO extends GenericDAO<CourseOrder> {

	List queryCourseOrderList();
}
