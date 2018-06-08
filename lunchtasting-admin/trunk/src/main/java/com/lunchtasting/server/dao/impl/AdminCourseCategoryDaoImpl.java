package com.lunchtasting.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.AdminCourseCategoryDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.CourseCategory;
@Repository
public class AdminCourseCategoryDaoImpl extends GenericDAOSupport<Long,CourseCategory> implements AdminCourseCategoryDao{

	@Override
	public List queryCourseCategoryList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCourseCategoryList",map);
	}

	@Override
	public int getCourseCategoryCount(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryCourseCategoryListCount",map);
	}

	@Override
	public int updateCourseCategory(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update(getNamespace() + ".deleteCourseCategory",map);
	}

	@Override
	public Long addCourseCategory(CourseCategory courseCategory) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create",courseCategory);
	}

	@Override
	public Long updateCourseCategory(CourseCategory courseCategory) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".update",courseCategory);
	}

	@Override
	public CourseCategory queryCourseCategoryById(String id) {
		Map map = new HashMap();
		map.put("id", id);
		return (CourseCategory) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getById",map);
	}

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return CourseCategory.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "course_category";
	}

	@Override
	public List queryCourseCategoryByLevel() {
		// TODO Auto-generated method stub
		return (List) getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCourseCategoryByLevel");
	}

	@Override
	public int getCourseCategoryByName(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryCourseCategoryByName", map);
	}

	@Override
	public int deleteCategoryByParentId(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update(getNamespace() + ".deleteCategoryByParentId",map);
	}

	@Override
	public CourseCategory getCategoryByName(String name) {
		HashMap map = new HashMap();
		map.put("name", name);
		return (CourseCategory) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getCategoryByName",map);
	}

	@Override
	public List getlevel2() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".getlevel2");
	}

}
