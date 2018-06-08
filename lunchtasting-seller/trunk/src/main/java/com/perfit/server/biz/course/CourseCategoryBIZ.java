package com.perfit.server.biz.course;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.CourseCategory;

public interface CourseCategoryBIZ {
	/**
	 * 查询一级分类
	 * @param map
	 * @return
	 */
	List<CourseCategory> queryCourseCategoryListByHead();
	
	/**
	 * 查询二级分类
	 * @param map
	 * @return
	 */
	List<CourseCategory> queryCourseCategoryListByBelow(Integer parentId);
}
