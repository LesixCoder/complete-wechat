package com.lunchtasting.server.biz.course;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.po.lt.Course;

public interface CourseBIZ {

	Course getById(Long id);
	
	/**
	 * 查询课程列表
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryCourseList(Integer page,Integer pageSize);
	
	/**
	 * 添加课程
	 * @param name
	 * @param mold
	 * @param characteristic
	 * @param tag
	 * @param highlight
	 * @param process
	 * @param imgUrl
	 * @param imgArray
	 * @return
	 */
	Boolean addCourse(String name,String mold,String characteristic
			,String tag,String highlight,String process,String imgUrl,String imgArray);
	
	/**
	 * 获得一个编辑的课程信息
	 * @param courseId
	 * @return
	 */
	Map getEditCourse(Long courseId);
	
	/**
	 * 编辑课程信息
	 * @param courseId
	 * @param name
	 * @param mold
	 * @param characteristic
	 * @param tag
	 * @param highlight
	 * @param process
	 * @param imgUrl
	 * @param imgArray
	 * @return
	 */
	Boolean editCourse(Long courseId,String name,String mold,String characteristic
			,String tag,String highlight,String process,String imgUrl,String imgArray);
	
	/**
	 * 查询课程下的健身房列表
	 * @param courseId
	 * @return
	 */
	List queryCourseGymList(Long courseId);
	
	/**
	 * 添加课程下的健身房关联
	 * @param courseId
	 * @param gymId
	 * @return
	 */
	Boolean addCourseGym(Long courseId,Long gymId);
	
	/**
	 * 删除一个课程健身房关联
	 * @param courseGymId
	 * @return
	 */
	Boolean removeCourseGym(Long courseGymId);
	
	/**
	 * 获得课程和健身房是否关联
	 * @param courseId
	 * @param gymId
	 * @return
	 */
	Boolean checkCourseGym(Long courseId,Long gymId);
	
	/**
	 * 修改状态
	 * @param courseId
	 * @param flag
	 * @return
	 */
	Boolean updateCourseFlag(Long courseId,Integer flag);
}
