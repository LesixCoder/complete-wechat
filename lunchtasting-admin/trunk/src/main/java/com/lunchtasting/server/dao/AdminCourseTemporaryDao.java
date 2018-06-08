package com.lunchtasting.server.dao;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.CourseTemporary;

public interface AdminCourseTemporaryDao extends GenericDAO<CourseTemporary>{
	List queryCourseTemporaryList();
	Long addCourse(CourseTemporary courseTemporary);
}
