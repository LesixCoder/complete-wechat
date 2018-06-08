package com.lunchtasting.server.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lunchtasting.server.biz.gym.CoachBIZ;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 教练
 * @author WenQuan
 *
 */
@Controller
@RequestMapping("/coach")
public class CoachController {
	
	@Autowired
	private CoachBIZ coachBIZ;
	
	
	/**
	 * 教练详情
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{id}")
	public String coachDetail(@PathVariable("id") String id,Model model, HttpServletRequest request){
		if(!ValidatorHelper.isNumber(id)){
			return VariableHelper.ERROR2_JSP;
		}
		long coachId = Long.parseLong(id);
		
		Map coachMap = coachBIZ.getCoachDetail(coachId);
		if(coachMap == null){
			return VariableHelper.ERROR2_JSP;
		}
		model.addAttribute("coach", coachMap);
		
		List coachList = coachBIZ.queryCoachAlbumList(coachId, null, null);
		model.addAttribute("list", coachList);
		
		return "/coach/coach_detail";
	}
}
