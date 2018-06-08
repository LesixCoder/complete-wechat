package com.lunchtasting.server.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lunchtasting.server.biz.AdminAreaBIZ;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.Area;

@Controller
public class AdminAreaController extends BaseController{
	
	@Autowired
	private AdminAreaBIZ adminAreaBIZ;
	/**
	 * 查询区域列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAreaList")
	@ResponseBody
	public Object queryAreaList(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		Map map = new HashMap();//返回结果
		String aoData = request.getParameter("aoData");
		String sEcho = "";
		int iDisplayStart =0;
		int iDisplayLength = 0;
		JSONArray jo = JSON.parseArray(aoData);
		if("sEcho".equals(JSON.parseObject(jo.get(0).toString()).get("name"))){
			sEcho = JSON.parseObject(jo.get(0).toString()).get("value").toString();
		}
		if("iDisplayStart".equals(JSON.parseObject(jo.get(3).toString()).get("name"))){
			iDisplayStart = Integer.parseInt(JSON.parseObject(jo.get(3).toString()).get("value").toString());
		}
		if("iDisplayLength".equals(JSON.parseObject(jo.get(4).toString()).get("name"))){
			iDisplayLength = Integer.parseInt(JSON.parseObject(jo.get(4).toString()).get("value").toString());
		}
		HashMap mapCondition = new HashMap<String, Object>();//条件
//		mapCondition.put("name", !JSON.parseObject(jo.get(23).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(23).toString()).get("value").toString():"");
//		mapCondition.put("type", !JSON.parseObject(jo.get(24).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(24).toString()).get("value").toString():"");
		mapCondition.put("curPage", iDisplayStart);
		mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
		
		HashMap strmap = adminAreaBIZ.queryAreaList(mapCondition);
		if(Integer.parseInt(strmap.get("result").toString()) == 0){
			PageBean pageBean = (PageBean)strmap.get("page");
			map.put("result", 0);
			map.put("aaData", pageBean.getList());
			map.put("sEcho", sEcho);
			map.put("iTotalRecords", strmap.get("totalCount"));
			map.put("iTotalDisplayRecords", strmap.get("totalCount"));
		}
		return map;
}
	
	/**
	 * 前往List页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goAreaList")
	public String goAreaList(){
		return "area/admin_area_list";
	}
	
	/**
	 * 前往保存页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goAreaSave")
	public String goAreaSave(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		if(id != null && id != "null" && id != ""){
			Area ac =  adminAreaBIZ.queryAreaById(id);
			model.addAttribute("area", ac);
		}
		return "area/admin_area_save";
	}
	
	/**
	 * 保存区域
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 * @throws IOException 
	 */
	@RequestMapping(value = "/saveArea")
	@ResponseBody
	public Model saveArea(Model model,HttpServletRequest request) throws SQLException, ParseException, IOException{
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		Area ac = new Area();
		ac.setName(name);
		HashMap map = null;
		
		try {
			map = new HashMap();
			map.put("name", request.getParameter("name"));
			if(id != "null" && id != null && id != ""){
				ac.setId(Long.parseLong(id));
				if(name.equals(adminAreaBIZ.queryAreaById(id).getName())){
					adminAreaBIZ.updateArea(ac);
					model.addAttribute("flag", "success");
				}else{
					if(adminAreaBIZ.queryNameCount(name.trim()) == 0){
						adminAreaBIZ.updateArea(ac);
						model.addAttribute("flag", "success");
					}else{
						model.addAttribute("flag", "fail");
						model.addAttribute("msg", "已有此区域!");
					}
				}
			}else{
				if(adminAreaBIZ.queryNameCount(name.trim()) == 0){
					adminAreaBIZ.addArea(ac);
					model.addAttribute("flag", "success");
				}else{
					model.addAttribute("flag", "fail");
					model.addAttribute("msg", "已有此区域!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
			return null;
		}
		return model;
	}
	
	/**
	 * 删除
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteArea",method=RequestMethod.POST)
	@ResponseBody
	public Model deleteArea(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		HashMap map = new HashMap();
		if(type.equals("del")){
			map.put("flag", 2);
		}else if(type.equals("pub")){
			map.put("flag", 0);
		}else if(type.equals("cel")){
			map.put("flag", 1);
		}
		map.put("id", id);
		try {
			adminAreaBIZ.updateArea(map);
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
}
