package com.perfit.server.biz.course;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.Course;

public interface CourseBIZ {
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
	 * @param id
	 * @return
	 */
	Course courseById(Long id,Long sellerId);
	/**
	 * 新建课程
	 * @param course
	 * @return
	 */
	Boolean creat(Course course);
	/**
	 * 更新课程
	 * @param course
	 * @return
	 */
	Boolean updateCourse(Course course); 
	/**
	 * 根据ID删除课程
	 * @param id
	 * @return
	 */
	Boolean deleteCourse(Long id);
}
