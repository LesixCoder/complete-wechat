package com.lunchtasting.server.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.gym.GymBIZ;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.po.lt.gym.Gym;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/gym")
public class GymController {
	
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
	public String gymList(Model model, HttpServletRequest request) throws IOException{
		String page = request.getParameter("page");
		String flag = request.getParameter("flag");
		
		List list = gymBIZ.queryGymList(null,0, 30);
		model.addAttribute("list", list);
		
		return "/gym/gym_list";
	}
	
	/**
	 * 添加健身房
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/add")
	public String gymAdd(Model model, HttpServletRequest request) throws IOException{
		return "/gym/gym_add";
	}
	
	/**
	 * 添加健身房信息
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doAdd")
	@ResponseBody
	public Object doAdd(HttpServletRequest request,HttpServletResponse response){
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String simpleAddress = request.getParameter("simple_address");
		String introduce = request.getParameter("introduce");
		String imgUrl = request.getParameter("img_url");
		String imgArray = request.getParameter("img_array");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		
		if(ValidatorHelper.isEmpty(name)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		
		try {
			Double lon = null;
			if(ValidatorHelper.isNotEmpty(longitude)){
				lon = Double.parseDouble(longitude);
			}
			Double lat = null;
			if(ValidatorHelper.isNotEmpty(latitude)){
				lat = Double.parseDouble(latitude);
			}
			
			Boolean result = gymBIZ.addGym(name, phone, address, simpleAddress, introduce
					, imgUrl, imgArray, lon,lat);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
		}
		
		return MapResult.build(MapResult.CODE_FAILURE,MapResult.MESSAGE_FAILURE);
	}
	
	/**
	 * 编辑健身房说信息
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/edit")
	public String gymEdit(Model model, HttpServletRequest request) throws IOException{
		String gId = request.getParameter("gym_id");
		if(!ValidatorHelper.isNumber(gId)){
			return VariableHelper.ERROR_JSP;
		}
		long gymId = Long.parseLong(gId);
		Map gymMap = gymBIZ.getEditGym(gymId);
		if(gymMap == null){
			return VariableHelper.ERROR_JSP;
		}
		model.addAttribute("gym", gymMap);
		return "/gym/gym_edit";
	}
	
	/**
	 * 健身房信息编辑
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doEdit")
	@ResponseBody
	public Object doEdit(HttpServletRequest request,HttpServletResponse response){
		String gId = request.getParameter("gym_id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String simpleAddress = request.getParameter("simple_address");
		String introduce = request.getParameter("introduce");
		String imgUrl = request.getParameter("img_url");
		String imgArray = request.getParameter("img_array");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		if(!ValidatorHelper.isNumber(gId) || ValidatorHelper.isEmpty(name)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		long gymId = Long.parseLong(gId);
		
		try {
			Gym gym = gymBIZ.getById(gymId);
			if(gym == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
			}
			
			Boolean result = gymBIZ.editGym(gymId, name, phone, address, simpleAddress, introduce
					, imgUrl, imgArray, Double.parseDouble(longitude), Double.parseDouble(latitude));
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
		String gId = request.getParameter("gym_id");
		String f = request.getParameter("flag");
		if(!ValidatorHelper.isNumber(gId) || !ValidatorHelper.isNumber(f)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		if(!f.equals("0") && !f.equals("1")){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		long gymId = Long.parseLong(gId);
		int flag = Integer.parseInt(f);
		
		
		try {
			boolean result = gymBIZ.updateFlag(gymId, flag);
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
