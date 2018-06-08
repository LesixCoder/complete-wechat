package com.lunchtasting.server.dao.course;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.CourseMeal;

public interface CourseMealDAO extends GenericDAO<CourseMeal> {

	/**
	 * 查询课程套餐列表
	 * @param courseId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryCourseMealList(Long courseId,Integer page,Integer pageSize);
	
	/**
	 * 获得一个编辑的课程套餐信息
	 * @param courseMealId
	 * @return
	 */
	Map getEditCourseMeal(Long courseMealId);
	
	/**
	 * 修改状态
	 * @param courseMealId
	 * @param flag
	 * @return
	 */
	Integer updateFlag(Long courseMealId,Integer flag);
}
