package com.lunchtasting.server.biz.course.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.course.CourseBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.course.CourseDAO;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class CourseBIZImpl implements CourseBIZ{
	@Autowired
	private CourseDAO courseDAO;
	@Override
	public Map getCourseDetail(Long id) {
		// TODO Auto-generated method stub
		Map map = courseDAO.getCourseDetail(id);
		if(map!=null){
			map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
					+ QiNiuStorageHelper.MODEL0+"w/640/h/500");	
			if(ValidatorHelper.isMapNotEmpty(map.get("img_array"))){
				List imgList = new ArrayList();
				String imgArray = map.get("img_array").toString();
				for(String img : imgArray.split(",")){
				imgList.add(SysConfig.IMG_VISIT_URL+img
					+ QiNiuStorageHelper.MODEL0+"w/640/h/500");
				}
				map.put("img_list", imgList);
				map.remove("img_array");
			}
		}
		return map;
	}
}
