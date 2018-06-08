
package com.lunchtasting.server.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lunchtasting.server.biz.gym.CourseBIZ;
import com.lunchtasting.server.biz.gym.GymBIZ;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.po.lt.gym.Gym;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 健身房
 * @author xq
 *
 */
@Controller
@RequestMapping("/gym")
public class GymController {

	@Autowired
	private GymBIZ gymBIZ;
	@Autowired
	private CourseBIZ courseBIZ;
	
	/**
	 * 健身房地图
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/map")
	public String gymMap(Model model, HttpServletRequest request){
		String gId = request.getParameter("gym_id");
		if(!ValidatorHelper.isNumber(gId)){
			return VariableHelper.ERROR2_JSP;
		}
		long gymId = Long.parseLong(gId);
		
		Map gym = gymBIZ.getGymDetail(gymId);
		if(gym == null){
			return VariableHelper.ERROR2_JSP;
		}
		model.addAttribute("gym", gym);
		
		return "/gym/gym_map";
	}
	
//	@RequestMapping(value = "/{id}")
//	public String gymDetail(String id,Model model, HttpServletRequest request){
//		if(!ValidatorHelper.isNumber(id)){
//			return VariableHelper.ERROR_JSP;
//		}
//		long gymId = Long.parseLong(id);
//		
//		Map gymMap = gymBIZ.getGymDetail(gymId);
//		if(gymMap == null){
//			return VariableHelper.ERROR_JSP;
//		}
//		model.addAttribute("gym", gymMap);
//		
//		return "/gym/gym_detail";
//	}
	

//	
//	/**
//	 * 健身房训练营
//	 * @param model
//	 * @param request
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/xly")
//	public String courseDetail(Model model, HttpServletRequest request){
//		String gId = request.getParameter("gym_id");
//		if(!ValidatorHelper.isNumber(gId)){
//			return VariableHelper.ERROR_JSP;
//		}
//		long gymId = Long.parseLong(gId);
//		
//		Map gymMap = gymBIZ.getGymDetail(gymId);
//		if(gymMap == null){
//			return VariableHelper.ERROR_JSP;
//		}
//		
//		Map courseMap = courseBIZ.getGymXlyFirstCourse(gymId);
//		if(courseMap == null){
//			return VariableHelper.ERROR_JSP;
//		}
//		long courseId = Long.parseLong(courseMap.get("id").toString());
//		
//		/**
//		 * 课程套餐列表
//		 */
//		List courseMealList = courseBIZ.queryCourseMealList(courseId);
//		model.addAttribute("courseMealList", courseMealList);
//		model.addAttribute("gym", gymMap);
//
//		return "/gym/gym_list";
//	}
}
