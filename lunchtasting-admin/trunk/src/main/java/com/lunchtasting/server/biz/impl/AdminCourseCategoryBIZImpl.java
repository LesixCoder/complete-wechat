package com.lunchtasting.server.biz.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunchtasting.server.biz.AdminCourseCategoryBIZ;
import com.lunchtasting.server.dao.AdminCourseCategoryDao;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.po.lt.CourseCategory;
@Transactional(rollbackFor = Throwable.class)
@Service
public class AdminCourseCategoryBIZImpl implements AdminCourseCategoryBIZ{
	
	@Autowired
	private AdminCourseCategoryDao adminCourseCategoryDao;
	
	@Override
	public HashMap queryCourseCategory(HashMap map) {
		HashMap result = new HashMap();
	    List<CourseCategory> courseCategoryList;
	    int totalCount;
    	 try{
    		 courseCategoryList = adminCourseCategoryDao.queryCourseCategoryList(map);
//    		 for(int i = 0;i<courseCategoryList.size();i++){
//    			 courseCategoryList.get(i).setNewId(courseCategoryList.get(i).getId().toString());
//    		 }
    		 totalCount = adminCourseCategoryDao.getCourseCategoryCount(map);
    	 }catch(Exception e){
			 e.printStackTrace();
			 result.put("result","1");
		     result.put("descript","查询失败");
		     result.put("totalCount",0);
		     result.put("page",new PageBean(null, Integer.parseInt(map.get("curPage").toString()), 0, Integer.parseInt(map.get("pageSize").toString())));
		     return result;
    	 }
    	 if(courseCategoryList != null && courseCategoryList.size() > 0){
    		 result.put("result","0");
		     result.put("descript","查询成功");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(courseCategoryList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }else{
    		 result.put("result","0");
		     result.put("descript","没有更多");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(courseCategoryList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }
		return result;
	}

	@Override
	public int updateCourseCategory(HashMap map) {
		// TODO Auto-generated method stub
		return adminCourseCategoryDao.updateCourseCategory(map);
	}

	@Override
	public Long addCourseCategory(CourseCategory courseCategory) {
		// TODO Auto-generated method stub
		return adminCourseCategoryDao.addCourseCategory(courseCategory);
	}

	@Override
	@Transactional
	public Long updateCourseCategory(CourseCategory courseCategory) {
		// TODO Auto-generated method stub
		return adminCourseCategoryDao.updateCourseCategory(courseCategory);
	}

	@Override
	public CourseCategory queryCourseCategoryById(String id) {
		// TODO Auto-generated method stub
		return adminCourseCategoryDao.queryCourseCategoryById(id);
	}

	@Override
	public List queryCourseCategoryByLevel() {
		// TODO Auto-generated method stub
		return adminCourseCategoryDao.queryCourseCategoryByLevel();
	}

	@Override
	public int getCourseCategoryByName(HashMap map) {
		// TODO Auto-generated method stub
		return adminCourseCategoryDao.getCourseCategoryByName(map);
	}

	@Override
	public int deleteCategoryByParentId(HashMap map) {
		return adminCourseCategoryDao.deleteCategoryByParentId(map);
	}

	@Override
	public CourseCategory getCategoryByName(String name) {
		// TODO Auto-generated method stub
		return adminCourseCategoryDao.getCategoryByName(name);
	}

	@Override
	public List getlevel2() {
		// TODO Auto-generated method stub
		return adminCourseCategoryDao.getlevel2();
	}

}
