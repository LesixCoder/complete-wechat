package com.lunchtasting.server.biz.course;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lunchtasting.server.po.lt.CourseMeal;

public interface CourseMealBIZ {
	
	CourseMeal getById(Long id);
	
	/**
	 * 查询课程套餐列表
	 * @param courseId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryCourseMealList(Long courseId,Integer page,Integer pageSize);
	
	/**
	 * 添加一个课程套餐
	 * @param courseId
	 * @param gymId
	 * @param coachId
	 * @param name
	 * @param price
	 * @param marketPrice
	 * @param peopleNumber
	 * @param courseNumber
	 * @param type
	 * @param strTime
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	Boolean addCourseMeal(Long courseId,Long gymId,Long coachId,String name
			,Double price,Double marketPrice,Integer peopleNumber,Integer courseNumber
			,Integer type,String strTime,Date beginTime,Date endTime);
	
	/**
	 * 获得一个编辑的课程套餐信息
	 * @param courseMealId
	 * @return
	 */
	Map getEditCourseMeal(Long courseMealId);
	
	/**
	 * 编辑课程套餐信息
	 * @param courseMealId
	 * @param courseId
	 * @param gymId
	 * @param coachId
	 * @param name
	 * @param price
	 * @param marketPrice
	 * @param peopleNumber
	 * @param courseNumber
	 * @param type
	 * @param strTime
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	Boolean editCourseMeal(Long courseMealId,Long courseId,Long gymId,Long coachId,String name
			,Double price,Double marketPrice,Integer peopleNumber,Integer courseNumber
			,Integer type,String strTime,Date beginTime,Date endTime);
	
	/**
	 * 修改状态
	 * @param courseMealId
	 * @param flag
	 * @return
	 */
	Boolean updateFlag(Long courseMealId,Integer flag);
}
