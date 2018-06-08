package com.lunchtasting.server.dao.course;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Course;

public interface CourseDAO extends GenericDAO<Course> {

	/**
	 * 查询课程列表
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryCourseList(Integer page,Integer pageSize);
	
	/**
	 * 获得一个编辑的课程信息
	 * @param courseId
	 * @return
	 */
	Map getEditCourse(Long courseId);
	
	/**
	 * 查询课程下的健身房列表
	 * @param courseId
	 * @return
	 */
	List queryCourseGymList(Long courseId);
	
	/**
	 * 添加课程下的健身房
	 * @param id
	 * @param courseId
	 * @param gymId
	 */
	void addCourseGym(Long id,Long courseId,Long gymId);
	
	/**
	 * 删除一个课程健身房关联
	 * @param courseGymId
	 * @return
	 */
	void removeCourseGym(Long id);
	
	/**
	 * 获得课程健身房关联
	 * @param courseId
	 * @param gymId
	 * @return
	 */
	Integer getCourseGym(Long courseId,Long gymId);
	
	/**
	 * 修改状态
	 * @param courseId
	 * @param flag
	 * @return
	 */
	Integer updateCourseFlag(Long courseId,Integer flag);
}
