package com.lunchtasting.server.biz.course.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.course.CourseOrderBIZ;
import com.lunchtasting.server.dao.course.CourseOrderDAO;
import com.lunchtasting.server.util.DateUtil;

@Service
public class CourseOrderBIZImpl implements CourseOrderBIZ {

	@Autowired
	private CourseOrderDAO courseOrderDAO;

	@Override
	public List queryCourseOrderList() {
		List list = courseOrderDAO.queryCourseOrderList();
		if(list != null){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 判断时间显示
				 * 1 普通课  2团课
				 */
				int type = Integer.parseInt(map.get("meal_type").toString());
				if(type == 1){
					map.put("meal_str_time", map.get("meal_begin_time") + " " +map.get("meal_str_time"));
				}else{
					map.put("meal_str_time", map.get("meal_begin_time") + " - " 
							+ map.get("meal_end_time") + " " +map.get("meal_str_time"));
				}
				
				
				try {
					map.put("create_time", DateUtil.convertDateToString(
							DateUtil.convertStringTODate(map.get("create_time").toString(), "yyyy-MM-dd HH:mm")
								,"yyyy-MM-dd HH:mm"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				newList.add(map);
			}
			return newList;
		}
		return null;
	}
}
