package com.lunchtasting.server.biz.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lunchtasting.server.biz.AdminCourseBIZ;
import com.lunchtasting.server.dao.AdminCourseDao;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.po.lt.Course;
@Service
public class AdminCourseBIZImpl implements AdminCourseBIZ{
	@Autowired
	private AdminCourseDao adminCourseDao;
	@Override
	public HashMap queryCourse(HashMap map) {
		HashMap result = new HashMap();
	    List<Course> courseList;
	    int totalCount;
    	 try{
    		 courseList = adminCourseDao.queryCourseList(map);
    		 for(int i = 0;i<courseList.size();i++){
    			 courseList.get(i).setNewId(courseList.get(i).getId().toString());
    		 }
    		 totalCount = adminCourseDao.getCourseCount(map);
    	 }catch(Exception e){
			 e.printStackTrace();
			 result.put("result","1");
		     result.put("descript","查询失败");
		     result.put("totalCount",0);
		     result.put("page",new PageBean(null, Integer.parseInt(map.get("curPage").toString()), 0, Integer.parseInt(map.get("pageSize").toString())));
		     return result;
    	 }
    	 if(courseList != null && courseList.size() > 0){
    		 result.put("result","0");
		     result.put("descript","查询成功");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(courseList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }else{
    		 result.put("result","0");
		     result.put("descript","没有更多");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(courseList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }
		return result;
	}
	@Override
	public int updateCourse(HashMap map) {
		// TODO Auto-generated method stub
		return adminCourseDao.updateCourse(map);
	}
	@Override
	public Long addCourse(Course course) {
		// TODO Auto-generated method stub
		return adminCourseDao.addCourse(course);
	}
	@Override
	public Long updateCourse(Course course) {
		// TODO Auto-generated method stub
		return adminCourseDao.updateCourse(course);
	}
	@Override
	public Course queryCourseById(String id) {
		// TODO Auto-generated method stub
		return adminCourseDao.queryCourseById(id);
	}
	@Override
	public List queryCourseNotLimit() {
		// TODO Auto-generated method stub
		return adminCourseDao.queryCourseNotLimit();
	}

}
