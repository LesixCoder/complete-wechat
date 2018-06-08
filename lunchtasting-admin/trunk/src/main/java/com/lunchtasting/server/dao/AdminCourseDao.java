package com.lunchtasting.server.dao;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Course;

public interface AdminCourseDao extends GenericDAO<Course>{
	List queryCourseList(HashMap map);
	int getCourseCount(HashMap map);
	int updateCourse(HashMap map);
	Long addCourse(Course course);
	Long updateCourse(Course course);
	Course queryCourseById(String id);
	List queryCourseNotLimit();
}
