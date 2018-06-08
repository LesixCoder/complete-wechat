	package com.lunchtasting.server.controller;

import java.io.IOException;
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
import com.lunchtasting.server.biz.gym.GymBIZ;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.po.lt.Course;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseBIZ courseBIZ;
	@Autowired
	private GymBIZ gymBIZ;
	@Autowired
	private CoachBIZ coachBIZ;
	
	
	/**
	 * 大课程列表
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/list")
	public String courseList(Model model, HttpServletRequest request) throws IOException{
		String page = request.getParameter("page");
		
		List list = courseBIZ.queryCourseList(0, 30);
		model.addAttribute("list", list);
		
		return "/course/course_list";
	}
	
	/**
	 * 添加课程
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/add")
	public String courseAdd(Model model, HttpServletRequest request) throws IOException{
		return "/course/course_add";
	}
	
	/**
	 * 添加课程信息
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doAdd")
	@ResponseBody
	public Object doAdd(HttpServletRequest request,HttpServletResponse response){
		String name = request.getParameter("name");
		String mold = request.getParameter("mold");
		String characteristic = request.getParameter("characteristic");
		String tag = request.getParameter("tag");
		String highlight = request.getParameter("highlight");
		String process = request.getParameter("process");
		String imgUrl = request.getParameter("img_url");
		String imgArray = request.getParameter("img_array");
		
		if(ValidatorHelper.isEmpty(name) || ValidatorHelper.isEmpty(mold) 
				|| ValidatorHelper.isEmpty(characteristic) || ValidatorHelper.isEmpty(tag)
				|| ValidatorHelper.isEmpty(highlight) || ValidatorHelper.isEmpty(process)
				|| ValidatorHelper.isEmpty(imgUrl) || ValidatorHelper.isEmpty(imgArray)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		
		try {
			Boolean result = courseBIZ.addCourse(name, mold, characteristic, tag
					, highlight, process, imgUrl, imgArray);
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
	 * 编辑课程
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/edit")
	public String courseEdit(Model model, HttpServletRequest request) throws IOException{
		String cId = request.getParameter("course_id");
		if(!ValidatorHelper.isNumber(cId)){
			return VariableHelper.ERROR_JSP;
		}
		long courseId = Long.parseLong(cId);
		
		Map courseMap = courseBIZ.getEditCourse(courseId);
		if(courseMap == null){
			return VariableHelper.ERROR_JSP;
		}
		model.addAttribute("course", courseMap);
		
//		
//		/**
//		 *健身房列表
//		 */
//		List gymList = gymBIZ.querySimpleGymList();
//		model.addAttribute("gymList", gymList);
		
		return "/course/course_edit";
	}
	
	
	/**
	 * 编辑课程信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doEdit")
	@ResponseBody
	public Object doEdit(HttpServletRequest request,HttpServletResponse response){
		String cId = request.getParameter("course_id");
		String name = request.getParameter("name");
		String mold = request.getParameter("mold");
		String characteristic = request.getParameter("characteristic");
		String tag = request.getParameter("tag");
		String highlight = request.getParameter("highlight");
		String process = request.getParameter("process");
		String imgUrl = request.getParameter("img_url");
		String imgArray = request.getParameter("img_array");
		
		if(!ValidatorHelper.isNumber(cId) ||ValidatorHelper.isEmpty(name) || ValidatorHelper.isEmpty(mold) 
				|| ValidatorHelper.isEmpty(characteristic) || ValidatorHelper.isEmpty(tag)
				|| ValidatorHelper.isEmpty(highlight) || ValidatorHelper.isEmpty(process)
				|| ValidatorHelper.isEmpty(imgUrl) || ValidatorHelper.isEmpty(imgArray)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		long courseId = Long.parseLong(cId);
		
		try {
			Boolean result = courseBIZ.editCourse(courseId, name, mold, characteristic
					, tag, highlight, process, imgUrl, imgArray);
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
		String cId = request.getParameter("course_id");
		String f = request.getParameter("flag");
		if(!ValidatorHelper.isNumber(cId) || !ValidatorHelper.isNumber(f)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		if(!f.equals("0") && !f.equals("1")){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		long courseId = Long.parseLong(cId);
		int flag = Integer.parseInt(f);
		
		
		try {
			boolean result = courseBIZ.updateCourseFlag(courseId, flag);
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
	 * 课程关联的健身房列表
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/gym/list")
	public String courseGymList(Model model, HttpServletRequest request) throws IOException{
		String cId = request.getParameter("course_id");
		if(!ValidatorHelper.isNumber(cId)){
			return VariableHelper.ERROR_JSP;
		}
		long courseId = Long.parseLong(cId);
		Course course = courseBIZ.getById(courseId);
		model.addAttribute("course", course);
		
		List list = courseBIZ.queryCourseGymList(courseId);
		model.addAttribute("list", list);
		
		return "/course/course_gym_list";
	}
	
	/**
	 * 添加课程和健身房关联
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/gym/add")
	public String courseGymAdd(Model model, HttpServletRequest request) throws IOException{
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
		
		List gymList = gymBIZ.querySimpleGymList();
		model.addAttribute("gymList", gymList);
		
		return "/course/course_gym_add";
		
	}
	
	/**
	 * 添加一个课程健身房关联
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/gym/doAdd")
	@ResponseBody
	public Object courseGymDoAdd(HttpServletRequest request,HttpServletResponse response){
		String cId = request.getParameter("course_id");
		String gId = request.getParameter("gym_id");
		if(!ValidatorHelper.isNumber(cId) || !ValidatorHelper.isNumber(gId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		long courseId = Long.parseLong(cId);
		long gymId = Long.parseLong(gId);
		
		try {
			
			/**
			 * 判断是否已经关联过了
			 */
			Boolean checkAdd = courseBIZ.checkCourseGym(courseId, gymId);
			if(checkAdd){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,"课程和当前健身房已经关联过了！请误重复关联！");
			}
			
			Boolean result = courseBIZ.addCourseGym(courseId, gymId);
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
	 * 删除一个课程和健身房的关联
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/gym/doRemove")
	@ResponseBody
	public Object courseGymDoRemove(HttpServletRequest request,HttpServletResponse response){
		String cgId = request.getParameter("cg_id");
		if(!ValidatorHelper.isNumber(cgId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		long courseGymId = Long.parseLong(cgId);
		
		try {
			Boolean result = courseBIZ.removeCourseGym(courseGymId);
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
