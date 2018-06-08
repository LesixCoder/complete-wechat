package com.lunchtasting.server.biz;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.Course;

public interface AdminCourseBIZ {
	HashMap queryCourse(HashMap map);
	int updateCourse(HashMap map);
	Long addCourse(Course course);
	Long updateCourse(Course course);
	Course queryCourseById(String id);
	List queryCourseNotLimit();
}
