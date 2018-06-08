package com.lunchtasting.server.biz.course.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.course.CourseBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.course.CourseDAO;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.po.lt.Course;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
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
	public Integer getCourseCount(Long areaId, Long categoryId, Long sortId) {
		return courseDAO.getCourseCount(areaId, categoryId, sortId);
	}

	@Override
	public List queryCourseList(Long areaId, Long categoryId, Long sortId,
			Double longitude, Double latitude, Integer page, Integer pageSize) {
		List list = courseDAO.queryCourseList(areaId, categoryId, sortId, longitude, latitude, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL1+"w/340/h/288");
				
				/**
				 * 地理位置
				 */
				if(ValidatorHelper.isMapNotEmpty(map.get("distance"))){
					map.put("distance", CommonHelper.getDistance(Double.parseDouble(map.get("distance").toString())));
				}else{
					map.put("distance", "");
				}
				
				newList.add(map);
			}
			return newList;
		}
		
		return list;
	}

	@Override
	public Map getCourseDtail(Long courseId) {
		Map map = courseDAO.getCourseDtail(courseId);
		if(map != null){
			
			/**
			 * 课程图片
			 */
			map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
					+ QiNiuStorageHelper.MODEL0+"w/750/h/540");
			
			List imgList = null;
			if(ValidatorHelper.isMapNotEmpty(map.get("img_array"))){
				imgList = new ArrayList();
				String imgArray = map.get("img_array").toString();
				String [] spilt = imgArray.split(",");
				for(String img : spilt){
					imgList.add(SysConfig.IMG_VISIT_URL+img
							+QiNiuStorageHelper.MODEL0+"w/750/h/540");
				}
			}
			map.put("img_list", imgList);
		}
		return map;
	}

	@Override
	public List queryCourseCategoryList(Integer level) {
		return courseDAO.queryCourseCategoryList(level);
	}

	@Override
	public List queryCourseSortList() {
		
		List list = new ArrayList();
		
		Map map0 = new HashMap();
		map0.put("id", 0);
		map0.put("name", "全部");
		list.add(map0);
		
		Map map1 = new HashMap();
		map1.put("id", 1);
		map1.put("name", "人气最靓");
		list.add(map1);
		
		Map map2 = new HashMap();
		map2.put("id", 2);
		map2.put("name", "评价最帅");
		list.add(map2);
		
		return list;
	}

	@Override
	public List querySellerCourseList(Long sellerId, Integer page,
			Integer pageSize) {
		List list = courseDAO.querySellerCourseList(sellerId, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL1+"w/340/h/288");
				
				newList.add(map);
			}
			return newList;
		}
		return list;	
	}

	@Override
	public Map getConfirmCourse(Long courseId) {
		return courseDAO.getConfirmCourse(courseId);
	}

	@Override
	public List queryAllCourseCategoryList() {
		List list = courseDAO.queryAllCourseCategoryList();
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			
			/**
			 * 默认添加个全部
			 */
			Map allMap = new HashMap();
			allMap.put("category_id", 0);
			allMap.put("name", "全部");
			allMap.put("second_list", null);
			newList.add(allMap);
			
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				List secondList = null;
				if(ValidatorHelper.isMapNotEmpty(map.get("str"))){
					secondList = new ArrayList();
					String str = map.get("str").toString();
					String [] spiltA = str.split("\\|");
					if(ValidatorHelper.isNotEmpty(spiltA)){
						for(String a : spiltA){
							String [] spiltB = a.split("\\-");
							if(ValidatorHelper.isNotEmpty(spiltB)){
								Map secondMap = new HashMap();
								secondMap.put("category_id", Integer.parseInt(spiltB[0]));
								secondMap.put("name", spiltB[1]);
								secondList.add(secondMap);
							}
						}
					}
				}
				map.remove("str");
				map.put("second_list", secondList);
				newList.add(map);
			}
			return newList;
		}
		return list;	
	}
}
