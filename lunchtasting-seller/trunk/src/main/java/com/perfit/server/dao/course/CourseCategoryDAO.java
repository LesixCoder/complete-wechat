package com.perfit.server.dao.course;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.CourseCategory;

public interface CourseCategoryDAO {
	/**
	 * 查询课程分类一级
	 * @param map
	 * @return
	 */
	List<CourseCategory> queryCourseCategoryListByHead(HashMap map);
	
	/**
	 * 查询课程分类二级
	 * @param map
	 * @return
	 */
	List<CourseCategory> queryCourseCategoryListByBelow(HashMap map);
}
