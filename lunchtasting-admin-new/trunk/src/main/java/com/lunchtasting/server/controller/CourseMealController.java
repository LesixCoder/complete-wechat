package com.lunchtasting.server.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.coach.CoachBIZ;
import com.lunchtasting.server.biz.course.CourseBIZ;
import com.lunchtasting.server.biz.course.CourseMealBIZ;
import com.lunchtasting.server.biz.gym.GymBIZ;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.po.lt.Course;
import com.lunchtasting.server.po.lt.CourseMeal;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 课程套餐
 * @author WenQuan
 *
 */
@Controller
@RequestMapping("/course/meal")
public class CourseMealController {

	@Autowired
	private CourseBIZ courseBIZ;
	@Autowired
	private CourseMealBIZ courseMealBIZ;
	@Autowired
	private GymBIZ gymBIZ;
	@Autowired
	private CoachBIZ coachBIZ;
	
	/**
	 * 课程套餐列表
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/list")
	public String courseMealList(Model model, HttpServletRequest request) throws IOException{
		String cId = request.getParameter("course_id");
		if(!ValidatorHelper.isNumber(cId)){
			return VariableHelper.ERROR_JSP;
		}
		long courseId = Long.parseLong(cId);
		Course course = courseBIZ.getById(courseId);
		if(course == null){
			return VariableHelper.ERROR_JSP;
		}
		model.addAttribute("course", course);
		
		List list = courseMealBIZ.queryCourseMealList(courseId, null, null);
		model.addAttribute("list", list);
		
		return "/course/course_meal_list";
	}
	
	/**
	 * 课程套餐添加
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/add")
	public String courseMealAdd(Model model, HttpServletRequest request) throws IOException{
		String cId = request.getParameter("course_id");
		if(!ValidatorHelper.isNumber(cId)){
			return VariableHelper.ERROR_JSP;
		}
		long courseId = Long.parseLong(cId);
		
		Course course = courseBIZ.getById(courseId);
		if(course == null){
			return VariableHelper.ERROR_JSP;
		}
		model.addAttribute("course", course);
		
		/**
		 * 课程下的健身房
		 */
		List gymList = courseBIZ.queryCourseGymList(courseId);
		model.addAttribute("gymList", gymList);
		
		
		/**
		 * 教练列表
		 */
		List coachList = coachBIZ.querySimpleCoachList();
		model.addAttribute("coachList", coachList);
		
		return "/course/course_meal_add";
	}
	
	/**
	 * 添加一个课程套餐
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doAdd")
	@ResponseBody
	public Object courseMealDoAdd(HttpServletRequest request,HttpServletResponse response){
		String cId = request.getParameter("course_id");
		String gId = request.getParameter("gym_id");
		String coId = request.getParameter("coach_id");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String marketPrice = request.getParameter("market_price");
		String peopleNumber = request.getParameter("people_number");
		String courseNumber = request.getParameter("course_number");
		String strTime = request.getParameter("str_time");
		String beginTime = request.getParameter("begin_time");
		String endTime = request.getParameter("end_time");
		String type = request.getParameter("type");
		
		if(!ValidatorHelper.isNumber(cId) || !ValidatorHelper.isEmpty(gId) 
				|| !ValidatorHelper.isNumber(coId) || ValidatorHelper.isEmpty(price)
				|| ValidatorHelper.isEmpty(marketPrice) || ValidatorHelper.isEmpty(peopleNumber)
				|| ValidatorHelper.isEmpty(courseNumber) || ValidatorHelper.isEmpty(strTime)
				|| ValidatorHelper.isEmpty(beginTime) || ValidatorHelper.isEmpty(endTime)
				|| !ValidatorHelper.isNumber(type)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		long courseId = Long.parseLong(cId);
		long gymId = Long.parseLong(gId);
		long coachId = Long.parseLong(coId);
		
		try {
			
			Course course = courseBIZ.getById(courseId);
			if(course == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,"课程不存在！");
			}
			
			/**
			 * 时间格式   2017-01-01
			 */
			Date beginDate = null;
			if(ValidatorHelper.isNotEmpty(beginTime)){
				beginDate = DateUtil.convertStringTODate(beginTime, "yyyy-MM-dd");
			}
			Date endDate = null;
			if(ValidatorHelper.isNotEmpty(endTime)){
				endDate = DateUtil.convertStringTODate(endTime, "yyyy-MM-dd");
			}
			
			Boolean result = courseMealBIZ.addCourseMeal(courseId, gymId, coachId, name, Double.parseDouble(price)
					, Double.parseDouble(marketPrice), Integer.parseInt(peopleNumber)
					, Integer.parseInt(courseNumber), Integer.parseInt(type), strTime, beginDate, endDate);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
		}
		
		return MapResult.build(MapResult.CODE_FAILURE,MapResult.MESSAGE_FAILURE);	
	}
	
	/**
	 * 课程套餐编辑
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/edit")
	public String courseMealEdit(Model model, HttpServletRequest request) throws IOException{
		String cmId = request.getParameter("cm_id");
		String cId = request.getParameter("course_id");
		if(!ValidatorHelper.isNumber(cmId) || !ValidatorHelper.isNumber(cId)){
			return VariableHelper.ERROR_JSP;
		}
		long courseMealId = Long.parseLong(cmId);
		long courseId = Long.parseLong(cId);
		
		Map courseMealMap = courseMealBIZ.getEditCourseMeal(courseMealId);
		if(courseMealMap == null){
			return VariableHelper.ERROR_JSP;
		}
		model.addAttribute("meal", courseMealMap);

		Course course = courseBIZ.getById(courseId);
		if(course == null){
			return VariableHelper.ERROR_JSP;
		}
		model.addAttribute("course", course);
		
		/**
		 * 课程下的健身房
		 */
		List gymList = courseBIZ.queryCourseGymList(courseId);
		model.addAttribute("gymList", gymList);
		
		
		/**
		 * 教练列表
		 */
		List coachList = coachBIZ.querySimpleCoachList();
		model.addAttribute("coachList", coachList);
		
		return "/course/course_meal_add";
	}
	
	/**
	 * 编辑一个课程套餐
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doEdit")
	@ResponseBody
	public Object courseMealDoEdit(HttpServletRequest request,HttpServletResponse response){
		String cmId = request.getParameter("cm_id");
		String cId = request.getParameter("course_id");
		String gId = request.getParameter("gym_id");
		String coId = request.getParameter("coach_id");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String marketPrice = request.getParameter("market_price");
		String peopleNumber = request.getParameter("people_number");
		String courseNumber = request.getParameter("course_number");
		String strTime = request.getParameter("str_time");
		String beginTime = request.getParameter("begin_time");
		String endTime = request.getParameter("end_time");
		String type = request.getParameter("type");
		if(!ValidatorHelper.isNumber(cmId) || !ValidatorHelper.isNumber(cId) || !ValidatorHelper.isEmpty(gId) 
				|| !ValidatorHelper.isNumber(coId) || ValidatorHelper.isEmpty(price)
				|| ValidatorHelper.isEmpty(marketPrice) || ValidatorHelper.isEmpty(peopleNumber)
				|| ValidatorHelper.isEmpty(courseNumber) || ValidatorHelper.isEmpty(strTime)
				|| ValidatorHelper.isEmpty(beginTime) || ValidatorHelper.isEmpty(endTime)
				|| !ValidatorHelper.isNumber(type)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		long courseMealId = Long.parseLong(cmId);
		long courseId = Long.parseLong(cId);
		long gymId = Long.parseLong(gId);
		long coachId = Long.parseLong(coId);
		
		try {
			
			Course course = courseBIZ.getById(courseId);
			if(course == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,"课程不存在！");
			}
			
			
			/**
			 * 时间格式   2017-01-01
			 */
			Date beginDate = null;
			if(ValidatorHelper.isNotEmpty(beginTime)){
				beginDate = DateUtil.convertStringTODate(beginTime, "yyyy-MM-dd");
			}
			Date endDate = null;
			if(ValidatorHelper.isNotEmpty(endTime)){
				endDate = DateUtil.convertStringTODate(endTime, "yyyy-MM-dd");
			}
			
			Boolean result = courseMealBIZ.editCourseMeal(courseMealId,courseId, gymId, coachId, name, Double.parseDouble(price)
					, Double.parseDouble(marketPrice), Integer.parseInt(peopleNumber)
					, Integer.parseInt(courseNumber), Integer.parseInt(type), strTime, beginDate, endDate);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
		}
		
		return MapResult.build(MapResult.CODE_FAILURE,MapResult.MESSAGE_FAILURE);	
	}
	
	/**
	 * 修改上线下线状态
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doFlag")
	@ResponseBody
	public Object doFlag(HttpServletRequest request,HttpServletResponse response){
		String cmId = request.getParameter("cm_id");
		String f = request.getParameter("flag");
		if(!ValidatorHelper.isNumber(cmId) || !ValidatorHelper.isNumber(f)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		if(!f.equals("0") && !f.equals("1")){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		long courseMealId = Long.parseLong(cmId);
		int flag = Integer.parseInt(f);
		
		
		try {
			boolean result = courseMealBIZ.updateFlag(courseMealId, flag);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
		}
		return MapResult.build(MapResult.CODE_FAILURE,MapResult.MESSAGE_FAILURE);
	}
	
}
