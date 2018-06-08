package com.perfit.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.po.lt.Course;
import com.lunchtasting.server.po.lt.CourseCategory;
import com.lunchtasting.server.po.lt.Seller;
import com.perfit.server.biz.course.CourseCategoryBIZ;
import com.perfit.server.helper.MapResult;
import com.perfit.server.helper.VariableHelper;

@Controller
public class CourseCategoryController {
	@Autowired 
	private CourseCategoryBIZ courseCategoryBIZ;
	
	@RequestMapping(value = "/findCourseCategory")
	@ResponseBody
	public Object findCourseCategory(HttpServletRequest request){
		try{
			String  parentId =  request.getParameter("parentId");
			List<CourseCategory> list =  courseCategoryBIZ.queryCourseCategoryListByBelow(Integer.parseInt(parentId));
			Map map = new HashMap();
			map.put("list", list);
			return  MapResult.build(0,"成功",map,request);
		}catch (Exception e) {
			return MapResult.build(1,"服务器存在问题",null);
		}
	}
}
