package com.lunchtasting.server.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunchtasting.server.biz.AdminCourseTemporaryBIZ;
import com.lunchtasting.server.dao.AdminCourseTemporaryDao;
import com.lunchtasting.server.po.lt.CourseTemporary;
@Transactional(rollbackFor = Throwable.class)
@Service
public class AdminCourseTemporaryBIZImpl implements AdminCourseTemporaryBIZ{
	
	@Autowired
	private AdminCourseTemporaryDao adminCourseTemporaryDao;
	
	@Override
	public Long addCourseTemporary(CourseTemporary courseTemporary) {
		// TODO Auto-generated method stub
		return adminCourseTemporaryDao.addCourse(courseTemporary);
	}

	@Override
	public List queryCourseTemporary() {
		// TODO Auto-generated method stub
		return adminCourseTemporaryDao.queryCourseTemporaryList();
	}

}
