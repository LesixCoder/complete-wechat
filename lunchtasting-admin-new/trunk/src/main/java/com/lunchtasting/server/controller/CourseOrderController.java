package com.lunchtasting.server.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lunchtasting.server.biz.course.CourseOrderBIZ;

/**
 * 课程订单
 * @author WenQuan
 *
 */
@Controller
@RequestMapping("/course/order")
public class CourseOrderController {
	
	@Autowired
	private CourseOrderBIZ courseOrderBIZ;
	
	/**
	 * 课程订单列表
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/list")
	public String orderList(Model model, HttpServletRequest request) throws IOException{
		
		List list = courseOrderBIZ.queryCourseOrderList(); 
		model.addAttribute("list", list);
		
		return "/course/course_order_list";
	}
}
