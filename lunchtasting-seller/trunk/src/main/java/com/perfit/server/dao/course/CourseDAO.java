package com.perfit.server.dao.course;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Course;

public interface CourseDAO extends GenericDAO<Course>{
	/**
	 * 查询课程列表
	 * @param map
	 * @return
	 */
	List queryCourseList(HashMap map);
	/**
	 * 查询课程总个数
	 * @param map
	 * @return
	 */
	int queryCourseCount(HashMap map);
	/**
	 * 根据ID查询课程
	 * @param map
	 * @return
	 */
	Course courseById(Long id,Long sellerId);
	/**
	 * 更新课程
	 * @param course
	 * @return
	 */
	Integer updateCourse(Course course);
	
}
