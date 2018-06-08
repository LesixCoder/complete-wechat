package com.lunchtasting.server.biz.course.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.course.CourseBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.course.CourseDAO;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.po.lt.Course;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class CourseBIZImpl implements CourseBIZ {
	
	@Autowired
	private CourseDAO courseDAO;
	
	@Override
	public Course getById(Long id) {
		return courseDAO.getById(id);
	}
	
	@Override
	public List queryCourseList(Integer page, Integer pageSize) {
		List list = courseDAO.queryCourseList(page, pageSize);
		if(list != null){
			
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				if(ValidatorHelper.isMapNotEmpty(map.get("img_url"))){
					
					map.put("img_url_big",SysConfig.IMG_VISIT_URL+
							map.get("img_url").toString());
					
					map.put("img_url",SysConfig.IMG_VISIT_URL+map.get("img_url").toString()
							+QiNiuStorageHelper.MODEL0+"w/100/h/100");
				}
				
				/**
				 * 多图
				 */
				if(ValidatorHelper.isMapNotEmpty(map.get("img_array"))){
					
					List imgList = imgList = new ArrayList();
					Map imgMap = null;
					String imgArray = map.get("img_array").toString();
					String [] spilt = imgArray.split(",");
					for(String img : spilt){
						imgMap = new HashMap();
						imgMap.put("img_url_big",SysConfig.IMG_VISIT_URL+
								img);
						
						imgMap.put("img_url",SysConfig.IMG_VISIT_URL+img
								+QiNiuStorageHelper.MODEL0+"w/100/h/100");
						
						imgList.add(imgMap);
					}
					map.put("img_list", imgList);
				}
				newList.add(map);
			}
			return newList;
		}
		return list;
	}

	@Override
	public Boolean addCourse(String name, String mold,
			String characteristic, String tag, String highlight,
			String process, String imgUrl, String imgArray) {
		
		Course course = new Course();
		course.setId(IdWorker.getId());
		course.setName(name);
		course.setMold(mold);
		course.setCharacteristic(characteristic);
		course.setTag(tag);
		course.setHighlight(highlight);
		course.setProcess(process);
		course.setImgUrl(imgUrl);
		course.setImgArray(imgArray);
		courseDAO.create(course);
		return true;
	}

	@Override
	public Map getEditCourse(Long courseId) {
		return courseDAO.getEditCourse(courseId);
	}

	@Override
	public Boolean editCourse(Long courseId,String name, String mold, String characteristic,
			String tag, String highlight, String process, String imgUrl,
			String imgArray) {
		
		Course course = new Course();
		course.setId(courseId);
		course.setName(name);
		course.setMold(mold);
		course.setCharacteristic(characteristic);
		course.setTag(tag);
		course.setHighlight(highlight);
		course.setProcess(process);
		course.setImgUrl(imgUrl);
		course.setImgArray(imgArray);
		courseDAO.update(course);
		return true;
	}

	@Override
	public List queryCourseGymList(Long courseId) {
		return courseDAO.queryCourseGymList(courseId);
	}

	@Override
	public Boolean addCourseGym(Long courseId, Long gymId) {
		courseDAO.addCourseGym(IdWorker.getId(),courseId, gymId);
		return true;
	}

	@Override
	public Boolean removeCourseGym(Long courseGymId) {
		courseDAO.removeCourseGym(courseGymId);
		return true;
	}

	@Override
	public Boolean checkCourseGym(Long courseId, Long gymId) {
		Integer result = courseDAO.getCourseGym(courseId, gymId);
		if(result != null & result > 0){
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateCourseFlag(Long courseId, Integer flag) {
		Integer result = courseDAO.updateCourseFlag(courseId, flag);
		if(result == null || result == 0){
			return false;
		}
		return true;
	}
}
