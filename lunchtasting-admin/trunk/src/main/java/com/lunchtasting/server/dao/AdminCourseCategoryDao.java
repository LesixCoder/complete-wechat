package com.lunchtasting.server.dao;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.CourseCategory;

public interface AdminCourseCategoryDao extends GenericDAO<CourseCategory>{
	List queryCourseCategoryList(HashMap map);
	int getCourseCategoryCount(HashMap map);
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
