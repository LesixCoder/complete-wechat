package com.lunchtasting.server.dao.course.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.course.CourseDAO;
import com.lunchtasting.server.dao.orders.OrdersDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Course;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class CourseDAOImpl extends GenericDAOSupport<Long, Course> implements CourseDAO {

	@Override
	protected Class<?> getObjectClass() {
		return Course.class;
	}

	@Override
	protected String getTableName() {
		return "course";
	}
	
	@Override
	public Integer getCourseCount(Long areaId, Long categoryId, Long sortId) {
		Map map = new HashMap();
		map.put("areaId", areaId);
		map.put("categoryId", categoryId);
		map.put("sortId", sortId);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getCourseCount", map);
	}

	@Override
	public List queryCourseList(Long areaId, Long categoryId, Long sortId,
			Double longitude, Double latitude, Integer page, Integer pageSize) {
		Map map = new HashMap();
		map.put("areaId", areaId);
		map.put("categoryId", categoryId);
		map.put("sortId", sortId);		
		map.put("longitude", longitude);
		map.put("latitude", latitude);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCourseList", map);
	}

	@Override
	public Map getCourseDtail(Long courseId) {
		if(ValidatorHelper.isEmpty(courseId)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("courseId", courseId);
		return (Map) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getCourseDtail",map);	
	}

	@Override
	public List queryCourseCategoryList(Integer level) {
		Map map = new HashMap();
		map.put("level", level);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCourseCategoryList",map);
	}
	
	@Override
	public List queryAllCourseCategoryList() {
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryAllCourseCategoryList");
	}

	@Override
	public List querySellerCourseList(Long sellerId, Integer page,
			Integer pageSize) {
		if(sellerId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("sellerId", sellerId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".querySellerCourseList", map);
	}

	@Override
	public Map getConfirmCourse(Long courseId) {
		if(courseId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("courseId", courseId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getConfirmCourse", map);
	}


}
