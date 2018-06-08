package com.lunchtasting.server.biz;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.CourseCategory;

public interface AdminCourseCategoryBIZ {
	HashMap queryCourseCategory(HashMap map);
	int updateCourseCategory(HashMap map);
	Long addCourseCategory(CourseCategory courseCategory);
	Long updateCourseCategory(CourseCategory courseCategory);
	CourseCategory queryCourseCategoryById(String id);
	List queryCourseCategoryByLevel();
	int getCourseCategoryByName(HashMap map);
	int deleteCategoryByParentId(HashMap map);
	CourseCategory getCategoryByName(String name);
	List getlevel2();
}
