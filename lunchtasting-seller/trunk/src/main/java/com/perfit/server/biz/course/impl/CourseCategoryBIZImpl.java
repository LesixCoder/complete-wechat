package com.perfit.server.biz.course.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.po.lt.CourseCategory;
import com.perfit.server.biz.course.CourseCategoryBIZ;
import com.perfit.server.dao.course.CourseCategoryDAO;
@Service
public class CourseCategoryBIZImpl implements CourseCategoryBIZ{
	@Autowired
	private CourseCategoryDAO courseCategoryDAO;
	@Override
	public List<CourseCategory> queryCourseCategoryListByHead() {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		return courseCategoryDAO.queryCourseCategoryListByHead(map);
	}

	@Override
	public List<CourseCategory> queryCourseCategoryListByBelow(Integer parentId) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("parentId",parentId);
		return courseCategoryDAO.queryCourseCategoryListByBelow(map);
	}
}
