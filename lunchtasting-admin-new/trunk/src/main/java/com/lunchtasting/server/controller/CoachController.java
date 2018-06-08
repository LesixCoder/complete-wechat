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
import com.lunchtasting.server.biz.gym.GymBIZ;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.po.lt.gym.Coach;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/coach")
public class CoachController {

	@Autowired
	private CoachBIZ coachBIZ;
	@Autowired
	private GymBIZ gymBIZ;
	
	/**
	 * 教练列表
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/list")
	public String coachList(Model model, HttpServletRequest request) throws IOException{
//		String page = request.getParameter("page");
//		String name = request.getParameter("name");
//		String flag = request.getParameter("flag");
	
		List list = coachBIZ.queryCoachList(0, 30);
		model.addAttribute("list", list);
		
		return "/coach/coach_list";
	}
	
	/**
	 * 添加教练
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/add")
	public String coachAdd(Model model, HttpServletRequest request) throws IOException{
		
		/**
		 * 健身房列表
		 */
		List gymList = gymBIZ.querySimpleGymList();
		model.addAttribute("gymList", gymList);
		
		return "/coach/coach_add";
	}
	
	/**
	 * 添加教练
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doAdd")
	@ResponseBody
	public Object doAdd(HttpServletRequest request,HttpServletResponse response){
		String name = request.getParameter("name");
		String gymId = request.getParameter("gym_id");
		String sex = request.getParameter("sex");
		String phone = request.getParameter("phone");
		String age = request.getParameter("age");
		String birth = request.getParameter("birth");
		String coachYear = request.getParameter("coach_year");
		String certificate = request.getParameter("certificate");
		String imgUrl = request.getParameter("img_url");
		if(ValidatorHelper.isEmpty(name) || !ValidatorHelper.isNumber(gymId)
				|| !ValidatorHelper.isNumber(sex) || ValidatorHelper.isEmpty(phone)
				|| !ValidatorHelper.isNumber(age) || ValidatorHelper.isEmpty(birth)
				|| !ValidatorHelper.isNumber(coachYear) || ValidatorHelper.isEmpty(certificate)
				|| ValidatorHelper.isEmpty(imgUrl)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		
		
		try {
			Boolean result = coachBIZ.addCoach(name, Long.parseLong(gymId), Integer.parseInt(sex), phone
					, Integer.parseInt(age), birth, Integer.parseInt(coachYear)
					, certificate, imgUrl);
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
	 * 编辑教练信息
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/edit")
	public String coachEdit(Model model, HttpServletRequest request) throws IOException{
		String cId = request.getParameter("coach_id");
		if(!ValidatorHelper.isNumber(cId)){
			return VariableHelper.ERROR_JSP;
		}
		long coachId = Long.parseLong(cId);
		
		Map coachMap = coachBIZ.getEditCoach(coachId);
		if(coachMap == null){
			return VariableHelper.ERROR_JSP;
		}
		model.addAttribute("coach", coachMap);
		
		List gymList = gymBIZ.querySimpleGymList();
		model.addAttribute("gymList", gymList);
		
		return "/coach/coach_edit";
	}
	
	/**
	 * 教练信息编辑
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doEdit")
	@ResponseBody
	public Object doEdit(HttpServletRequest request,HttpServletResponse response){
		String cId = request.getParameter("coach_id");
		String name = request.getParameter("name");
		String gymId = request.getParameter("gym_id");
		String sex = request.getParameter("sex");
		String phone = request.getParameter("phone");
		String age = request.getParameter("age");
		String birth = request.getParameter("birth");
		String coachYear = request.getParameter("coach_year");
		String certificate = request.getParameter("certificate");
		String imgUrl = request.getParameter("img_url");
		if(!ValidatorHelper.isNumber(cId) || ValidatorHelper.isEmpty(name) 
				|| !ValidatorHelper.isNumber(gymId)
				|| !ValidatorHelper.isNumber(sex) || ValidatorHelper.isEmpty(phone)
				|| !ValidatorHelper.isNumber(age) || ValidatorHelper.isEmpty(birth)
				|| !ValidatorHelper.isNumber(coachYear) || ValidatorHelper.isEmpty(certificate)
				|| ValidatorHelper.isEmpty(imgUrl)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		long coachId = Long.parseLong(cId);
		
		try {
			Coach coach = coachBIZ.getById(coachId);
			if(coach == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
			}
			
			Boolean result = coachBIZ.editCoach(coachId,name, Long.parseLong(gymId), Integer.parseInt(sex), phone
					, Integer.parseInt(age), birth, Integer.parseInt(coachYear)
					, certificate, imgUrl);
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
		String cId = request.getParameter("coach_id");
		String f = request.getParameter("flag");
		if(!ValidatorHelper.isNumber(cId) || !ValidatorHelper.isNumber(f)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		if(!f.equals("0") && !f.equals("1")){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		long coachId = Long.parseLong(cId);
		int flag = Integer.parseInt(f);
		
		try {
			
			Boolean result = coachBIZ.updateFlag(coachId, flag);
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
	 * 教练相册列表
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/album/list")
	public String coachAlbumList(Model model, HttpServletRequest request) throws IOException{
		String cId = request.getParameter("coach_id");
		String page = request.getParameter("page");
		if(!ValidatorHelper.isNumber(cId)){
			return VariableHelper.ERROR_JSP;
		}
		long coachId = Long.parseLong(cId);
		
		Coach coach = coachBIZ.getById(coachId);
		if(coach == null){
			return VariableHelper.ERROR_JSP;
		}
		model.addAttribute("coach", coach);
		
		List coachList = coachBIZ.queryCoachAlbumList(coachId, null, null);
		model.addAttribute("list", coachList);
		
		return "/coach/coach_album_list";
	}
	
	/**
	 * 教练相册添加页面
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/album/add")
	public String coachAlbumAdd(Model model, HttpServletRequest request) throws IOException{
		String cId = request.getParameter("coach_id");
		if(!ValidatorHelper.isNumber(cId)){
			return VariableHelper.ERROR_JSP;
		}
		long coachId = Long.parseLong(cId);
		
		Coach coach = coachBIZ.getById(coachId);
		if(coach == null){
			return VariableHelper.ERROR_JSP;
		}
		model.addAttribute("coach", coach);
		
		return "/coach/coach_album_add";
	}
	
	/**
	 * 教练相册添加
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/album/doAdd")
	@ResponseBody
	public Object coachAlbumDoAdd(HttpServletRequest request,HttpServletResponse response){
		String cId = request.getParameter("coach_id");
		String imgUrl = request.getParameter("img_url");
		if(!ValidatorHelper.isNumber(cId) || ValidatorHelper.isEmpty(imgUrl)){
			return VariableHelper.ERROR_JSP;
		}
		long coachId = Long.parseLong(cId);
		
		try {
			
			Boolean result = coachBIZ.addCoachAlbum(coachId, imgUrl);
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
	 * 删除一张教练相册图片
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/album/doRemove")
	@ResponseBody
	public Object coachAlbumDoRemove(HttpServletRequest request,HttpServletResponse response){
		String caId = request.getParameter("coach_album_id");
		if(!ValidatorHelper.isNumber(caId) ){
			return VariableHelper.ERROR_JSP;
		}
		long coachAlbumId = Long.parseLong(caId);
		
		try {
			
			Boolean result = coachBIZ.removeCoachAlbum(coachAlbumId);
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
