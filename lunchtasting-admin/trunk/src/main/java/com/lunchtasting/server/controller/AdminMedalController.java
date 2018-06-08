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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lunchtasting.server.biz.AdminMedalBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.Medal;
import com.lunchtasting.server.util.IdWorker;

@Controller
public class AdminMedalController extends BaseController{
	
	@Autowired
	private AdminMedalBIZ adminMedalBIZ;
	
	/**
	 * 查询勋章列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryMedalList")
	@ResponseBody
	public Object queryMedalList(HttpServletRequest request){
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
		mapCondition.put("curPage", iDisplayStart);
		mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
		
		HashMap strmap = adminMedalBIZ.queryMedalList(mapCondition);
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
	@RequestMapping(value = "/goMedalList")
	public String goMedalList(Model model){
		return "medal/admin_medal_list";
	}
	
	/**
	 * 前往保存页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goMedalSave")
	public String goMedalSave(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		if(id != null && id != "null" && !"".equals(id)){
			Medal ac =  adminMedalBIZ.queryMedalById(id);
			model.addAttribute("medal", ac);
			model.addAttribute("imgUrl", SysConfig.IMG_VISIT_URL+ac.getImgUrl());
		}
		return "medal/admin_medal_save";
	}
	
	/**
	 * 保存勋章
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 * @throws IOException 
	 */
	@RequestMapping(value = "/saveMedal")
	@ResponseBody
	public Model saveMedal(Model model,HttpServletRequest request) throws SQLException, ParseException, IOException{
		String id = request.getParameter("id");
		Medal ac = new Medal();
		String name = request.getParameter("name");
		ac.setName(name);
		ac.setBizType(Integer.parseInt(request.getParameter("bizType")));
		ac.setBizName(request.getParameter("bizName"));
		String imgUrl = request.getParameter("imgUrl");
		if(imgUrl != null && !"".equals(imgUrl) && imgUrl != "null"){
			if("http://ocjp9x6x9.bkt.clouddn.com/".equals(SysConfig.IMG_VISIT_URL)){
				ac.setImgUrl(imgUrl.substring(33, 60));
			}else if("http://image.mperfit.com/".equals(SysConfig.IMG_VISIT_URL)){
				ac.setImgUrl(imgUrl.substring(25, 52));
			}
		}
		
		try {
			if(id != "null" && id != null && id != ""){
				ac.setId(Long.parseLong(id));
				if(name.equals(adminMedalBIZ.queryMedalById(id).getName())){
					adminMedalBIZ.updateMedal(ac);
					model.addAttribute("flag", "success");
				}else{
					if(adminMedalBIZ.queryMedalByName(name) == 0){
						adminMedalBIZ.updateMedal(ac);
						model.addAttribute("flag", "success");
					}else{
						model.addAttribute("flag", "fail");
						model.addAttribute("msg", "已有此分类!");
					}
				}
			}else{
				ac.setId(IdWorker.getId());
				if(adminMedalBIZ.queryMedalByName(name) == 0){
					adminMedalBIZ.saveMedal(ac);
					model.addAttribute("flag", "success");
				}else{
					model.addAttribute("flag", "fail");
					model.addAttribute("msg", "已有此分类!");
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
	@RequestMapping(value = "/deleteMedal",method=RequestMethod.POST)
	@ResponseBody
	public Model deleteMedal(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		try {
			if(id != null && !"".equals(id) && id != "null"){
				adminMedalBIZ.deleteMedal(id);
				model.addAttribute("flag", "success");
			}else{
				model.addAttribute("flag", "fail");
				return model;
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
}
