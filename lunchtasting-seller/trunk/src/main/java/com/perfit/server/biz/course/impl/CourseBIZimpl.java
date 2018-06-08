package com.perfit.server.biz.course.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.po.lt.Course;
import com.lunchtasting.server.util.IdWorker;
import com.perfit.server.biz.course.CourseBIZ;
import com.perfit.server.dao.course.CourseDAO;

@Service
public class CourseBIZimpl implements CourseBIZ{
	@Autowired
	private CourseDAO courseDAO;
	@Override
	public List queryCourseList(HashMap map) {
		// TODO Auto-generated method stub
		return courseDAO.queryCourseList(map);
	}

	@Override
	public int queryCourseCount(HashMap map) {
		// TODO Auto-generated method stub
		return courseDAO.queryCourseCount(map);
	}

	@Override
	public Course courseById(Long id, Long sellerId) {
		// TODO Auto-generated method stub
		return courseDAO.courseById(id, sellerId);
	}

	@Override
	public Boolean creat(Course course) {
		// TODO Auto-generated method stub
		course.setId(IdWorker.getId());
		courseDAO.create(course);
		Long id = course.getId();
		if(id!=0 && id!= null ){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Boolean updateCourse(Course course) {
		// TODO Auto-generated method stub
		int pf= courseDAO.updateCourse(course);
		if(pf==1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Boolean deleteCourse(Long id) {
		// TODO Auto-generated method stub
		Course course = new Course();
		course.setId(id);
		course.setFlag(2);
		int pf= courseDAO.updateCourse(course);
		if(pf==1){
			return true;
		}else{
			return false;
		}
	}
}
