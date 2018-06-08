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
import com.lunchtasting.server.biz.AdminMatchCategoryBIZ;
import com.lunchtasting.server.biz.AdminMedalBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.MatchCategory;
import com.lunchtasting.server.util.IdWorker;
@Controller
public class AdminMatchCategoryController extends BaseController{
	
	@Autowired
	private AdminMatchCategoryBIZ adminMatchCategoryBIZ;
	
	@Autowired
	private AdminMedalBIZ adminMedalBIZ;
	/**
	 * 查询赛事分类列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryMatchCategoryList")
	@ResponseBody
	public Object queryMatchCategoryList(HttpServletRequest request){
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
		
		HashMap strmap = adminMatchCategoryBIZ.queryMatchCategoryList(mapCondition);
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
	@RequestMapping(value = "/goMatchCategoryList")
	public String goMatchCategoryList(Model model){
		return "matchCategory/admin_match_category_list";
	}
	
	/**
	 * 前往保存页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goMatchCategorySave")
	public String goMatchCategorySave(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		if(id != null && id != "null" && !"".equals(id)){
			MatchCategory ac =  adminMatchCategoryBIZ.queryMatchCategoryById(id);
			model.addAttribute("matchCategory", ac);
			model.addAttribute("imgUrl", SysConfig.IMG_VISIT_URL+ac.getImgUrl());
		}
		model.addAttribute("medal", adminMedalBIZ.queryMedalNotLimit());
		return "matchCategory/admin_match_category_save";
	}
	
	/**
	 * 保存赛事分类
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 * @throws IOException 
	 */
	@RequestMapping(value = "/saveMatchCategory")
	@ResponseBody
	public Model saveMatchCategory(Model model,HttpServletRequest request) throws SQLException, ParseException, IOException{
		String id = request.getParameter("id");
		MatchCategory ac = new MatchCategory();
		String name = request.getParameter("name");
		ac.setName(name);
		ac.setContent(request.getParameter("content"));
		ac.setUnlockContent(request.getParameter("unlockContent"));
		ac.setMedalId(Long.parseLong(request.getParameter("medalId")));
		ac.setScore(Integer.parseInt(request.getParameter("score")));
		String imgUrl = request.getParameter("imgUrl");
		if(imgUrl != null && !"".equals(imgUrl) && imgUrl != "null"){
			if("http://ocjp9x6x9.bkt.clouddn.com/".equals(SysConfig.IMG_VISIT_URL)){
				ac.setImgUrl(imgUrl.substring(33, 60));
			}else if("http://image.mperfit.com/".equals(SysConfig.IMG_VISIT_URL)){
				ac.setImgUrl(imgUrl.substring(25, 52));
			}
		}
		if(request.getParameter("sort") != null && request.getParameter("sort") != "" && request.getParameter("sort") != "null"){
			ac.setSort(Integer.parseInt(request.getParameter("sort")));
		}else{
			ac.setSort(99);
		}
		
		try {
			if(id != "null" && id != null && id != ""){
				ac.setId(Long.parseLong(id));
				if(name.equals(adminMatchCategoryBIZ.queryMatchCategoryById(id).getName())){
					adminMatchCategoryBIZ.updateMatchCategory(ac);
					model.addAttribute("flag", "success");
				}else{
					if(adminMatchCategoryBIZ.queryMatchCategoryByName(name) == 0){
						adminMatchCategoryBIZ.updateMatchCategory(ac);
						model.addAttribute("flag", "success");
					}else{
						model.addAttribute("flag", "fail");
						model.addAttribute("msg", "已有此分类!");
					}
				}
			}else{
				ac.setId(IdWorker.getId());
				if(adminMatchCategoryBIZ.queryMatchCategoryByName(name) == 0){
					adminMatchCategoryBIZ.saveMatchCategory(ac);
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
	 * 删除或发布或撤销
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteMatchCategory",method=RequestMethod.POST)
	@ResponseBody
	public Model deleteMatchCategory(Model model,HttpServletRequest request){
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
			adminMatchCategoryBIZ.deleteMatchCategory(map);
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
//	public static void main(String[] args) {
//		String a = "http://ocjp9x6x9.bkt.clouddn.com/match/20170220190448975.jpg";
//		System.out.println(a.substring(33, 60));
//	}
}
