package com.lunchtasting.server.dao.course;

import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Course;

public interface CourseDAO extends GenericDAO<Course> {
	
	Map  getCourseDetail(Long id);
}
