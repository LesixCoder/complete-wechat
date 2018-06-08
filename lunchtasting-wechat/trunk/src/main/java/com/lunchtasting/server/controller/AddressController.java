package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.user.UserAddressBIZ;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.po.lt.UserAddress;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/address")
public class AddressController {
		
	@Autowired
	private UserAddressBIZ addressBIZ;
	
	/**
	 * 地址列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request, HttpServletResponse response){
		String bizId = request.getParameter("biz_id");
		String bizType = request.getParameter("biz_type");
		String bizStr = request.getParameter("biz_str");
		
		long userId = (long)request.getSession().getAttribute(
				VariableHelper.LOGIN_SESSION_USER);
		
		List list = addressBIZ.queryUserAddressList(userId, null, null);
		model.addAttribute("list", list);
		model.addAttribute("bizId", bizId);
		model.addAttribute("bizType", bizType);
		model.addAttribute("bizStr", bizStr);
		return "/goods/address_list";
	}
	
	/**
	 * 新增地址页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add(Model model, HttpServletRequest request){
		String bizId = request.getParameter("biz_id");
		String bizType = request.getParameter("biz_type");
		String bizStr = request.getParameter("biz_str");
		model.addAttribute("bizId", bizId);
		model.addAttribute("bizType", bizType);
		model.addAttribute("bizStr", bizStr);
		return "/goods/address_add";
	}
	
	/**
	 * 用户新增地址
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doAdd")
	@ResponseBody
	public Object doAdd(HttpServletRequest request, HttpServletResponse response){
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String town = request.getParameter("town");
		String detail = request.getParameter("detail");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
//		String longitude = request.getParameter("lng");
//		String latitude = request.getParameter("lat");
		String postalCode = request.getParameter("postal_code");
		String isFrequently = request.getParameter("is_frequently");
		
		if(ValidatorHelper.isEmpty(province)||
				ValidatorHelper.isEmpty(city)||
				ValidatorHelper.isEmpty(town)||
				ValidatorHelper.isEmpty(detail)||
				ValidatorHelper.isEmpty(name)||
				ValidatorHelper.isEmpty(phone)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		long userId = (long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
		
		try{
			boolean result = addressBIZ.doAdd(userId,province,city,town,detail,name,phone,null,postalCode,isFrequently,null,null);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
			}
		}catch(Exception e){
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
		}
		return MapResult.build(MapResult.CODE_FAILURE,MapResult.MESSAGE_FAILURE);
	}
	
	/**
	 * 修改编辑地址页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modify")
	public String modify(Model model, HttpServletRequest request){
		String aId = request.getParameter("address_id");
		String bizId = request.getParameter("biz_id");
		String bizType = request.getParameter("biz_type");
		String bizStr = request.getParameter("biz_str");
		
		if(!ValidatorHelper.isNumber(aId)){
			return "";
		}
		long addressId = Long.parseLong(aId);
		
		Map addressMap = addressBIZ.getByAddressId(addressId);
		if(addressMap == null){
			return "";
		}
		model.addAttribute("address", addressMap);
		model.addAttribute("bizId", bizId);
		model.addAttribute("bizType", bizType);
		model.addAttribute("bizStr", bizStr);
		return "/goods/address_edit";
	}
	
	/**
	 * 用户修改地址
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doModify")
	@ResponseBody
	public Object doModify(HttpServletRequest request, HttpServletResponse response){
		String addressId = request.getParameter("address_id");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String town = request.getParameter("town");
		String detail = request.getParameter("detail");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
		String postalCode = request.getParameter("postal_code");
		String isFrequently = request.getParameter("is_frequently");
		if(ValidatorHelper.isEmpty(province)||
				ValidatorHelper.isEmpty(city)||
				ValidatorHelper.isEmpty(town)||
				ValidatorHelper.isEmpty(detail)||
				ValidatorHelper.isEmpty(name)||
				!ValidatorHelper.isNumber(addressId)||
				ValidatorHelper.isEmpty(phone)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		
		try{
			long userId = (long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
//			String address = province + city + town + detail;
//			address = address.replaceAll(" ", "");
			boolean result = addressBIZ.doEdit(Long.parseLong(addressId),userId,province,city,town,detail,name,phone,null,postalCode,isFrequently,null,null);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
			}	
		}catch(Exception e){
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
		}
		return MapResult.build(MapResult.CODE_FAILURE,MapResult.MESSAGE_FAILURE);
	}
	
	/**
	 * 删除地址
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doRemove")
	@ResponseBody
	public Object doRemove(HttpServletRequest request, HttpServletResponse response){
		String aId = request.getParameter("address_id");
		if(!ValidatorHelper.isNumber(aId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		long addressId = Long.parseLong(aId);
		long userId = (long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
		
		try {
			UserAddress address = addressBIZ.getById(addressId);
			if(address == null || address.getUserId().longValue() != userId){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
			}
			
			boolean result = addressBIZ.doRemove(addressId, userId);
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
	 * 修改默认地址
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doUpdateFrequently")
	@ResponseBody
	public Object doUpdateFrequently(HttpServletRequest request, HttpServletResponse response){
		String aId = request.getParameter("address_id");
		if(!ValidatorHelper.isNumber(aId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		long addressId = Long.parseLong(aId);
		long userId = (long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
		
		try {
			UserAddress address = addressBIZ.getById(addressId);
			if(address == null || address.getUserId().longValue() != userId){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
			}
			
			/**
			 * 已经是常用地址，直接返回成功
			 */
			if(address.getIsFrequently().intValue() == 1){
				return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
			}
			
			addressBIZ.updateFrequently(addressId, userId);
			return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
		}
	}
	
	/**
	 * 获得省会列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getProvincesList")
	@ResponseBody
	public Object getProvincesList(HttpServletRequest request){
		
		try {
			
			/**
			 * 省份列表
			 */
			List provinceList  = addressBIZ.queryProvinceList();
			if(ValidatorHelper.isEmpty(provinceList)){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
			}
				
			/**
			 * 默认获得第一个省会下面的城市
			 */
			Map provinceMap = (HashMap) provinceList.get(0);
			String provinceCode = (String) provinceMap.get("code");
			List cityList = addressBIZ.queryCityList(provinceCode);
			
			
			/**
			 *  默认获得第一个城市下面的县区
			 */
			Map cityMap = (HashMap) cityList.get(0);
			String cityCode = (String) cityMap.get("code");
			List townList = addressBIZ.queryTownList(cityCode);
			
			/**
			 * 拼装数据
			 */
			Map map = new HashMap();
			map.put("provinceList", provinceList);
			map.put("cityList", cityList);
			map.put("townList", townList);
			return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS,map);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
		}
	}
	
	/**
	 * 获得城市列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getCityList")
	@ResponseBody
	public Object getCityList(HttpServletRequest request, HttpServletResponse response){
		String code = request.getParameter("code");
		if(ValidatorHelper.isEmpty(code)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		
		try {
		
			/**
			 * 省会下面的城市
			 */
			List cityList = addressBIZ.queryCityList(code);
			if(ValidatorHelper.isEmpty(cityList)){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
			}
			
			/**
			 *  默认获得第一个城市下面的县区
			 */
			Map cityMap = (HashMap) cityList.get(0);
			String cityCode = (String) cityMap.get("code");
			List townList = addressBIZ.queryTownList(cityCode);
			
			/**
			 * 拼装数据
			 */
			Map map = new HashMap();
			map.put("cityList", cityList);
			map.put("townList", townList);
			return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS,map);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
		}
	}
	
	/**
	 * 获得县区列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getTownList")
	@ResponseBody
	public Object getTownList(HttpServletRequest request, HttpServletResponse response){
		
		String code = request.getParameter("code");
		if(ValidatorHelper.isEmpty(code)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		
		try {
			
			/**
			 * 拼装数据
			 */
			Map map = new HashMap();
			map.put("townList", addressBIZ.queryTownList(code));
			return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS,map);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
		}
		
	}
	
	/**
	 * 获得所有省/市/县区
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getAllCity")
	@ResponseBody
	public Object getAllCityMap(HttpServletRequest request){
		
		Map map = addressBIZ.getAllCityMap();
		
		return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS,map);

	}
		
}
