package com.lunchtasting.server.biz.gym;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.po.lt.Course;

public interface CourseBIZ {

	Course getById(Long id);
	
	/**
	 * 获得课程详情
	 * @param courseId
	 * @return
	 */
	Map getCourseDtail(Long courseId);
	
	
	/**
	 * 查询课程套餐列表
	 * @param gymId
	 * @return
	 */
	List queryCourseMealList(Long courseId);
	
	/**
	 * 获得课程套餐详情
	 * @param courseMealId
	 * @return
	 */
	Map getCourseMealDetail(Long courseMealId);
	
	
//	/**
//	 * 获得课程总数
//	 * @param areaId
//	 * @param categoryId
//	 * @param sortId
//	 * @return
//	 */
//	Integer getCourseCount(Long areaId,Long categoryId);
//	
//	/**
//	 * 查询课程列表
//	 * @param areaId
//	 * @param categoryId
//	 * @param sortId
//	 * @param longitude
//	 * @param latitude
//	 * @param page
//	 * @param pageSize
//	 * @return
//	 */
//	List queryCourseList(Long areaId,Long categoryId,Integer page,Integer pageSize);
//	
//	/**
//	 * 获得课程类目列表
//	 * @param level
//	 * @return
//	 */
//	List queryCourseCategoryList(Integer level);
//	
//	/**
//	 * 获得整个课程类目，包括一级二级列表
//	 * @return
//	 */
//	List queryAllCourseCategoryList();
//	
//	/**
//	 * 获得课程智能排序列表
//	 * @return
//	 */
//	List queryCourseSortList();
//	
//	/**
//	 * 查询商家课程列表
//	 * @param sellerId
//	 * @param page
//	 * @param pageSize
//	 * @return
//	 */
//	List querySellerCourseList(Long sellerId,Integer page,Integer pageSize);
//	
//	/**
//	 * 获得订单支付前的确认课程
//	 * @param courseId
//	 * @return
//	 */
//	Map getConfirmCourse(Long courseId);
}
