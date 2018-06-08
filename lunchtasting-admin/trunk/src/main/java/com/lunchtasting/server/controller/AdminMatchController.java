package com.lunchtasting.server.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.lunchtasting.server.biz.AdminMatchBIZ;
import com.lunchtasting.server.biz.AdminMatchCategoryBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.helper.GetXY;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.Activity;
import com.lunchtasting.server.po.lt.Match;
import com.lunchtasting.server.util.IdWorker;

@Controller
public class AdminMatchController extends BaseController{
	
	@Autowired
	private AdminMatchBIZ adminMatchBIZ;
	
	@Autowired
	private AdminMatchCategoryBIZ adminMatchCategoryBIZ;
	
	/**
	 * 查询赛事列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryMatchList")
	@ResponseBody
	public Object queryMatchList(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		Map map = new HashMap();//返回结果
		String aoData = request.getParameter("aoData");
		String sEcho = "";
		int iDisplayStart =0;
		int iDisplayLength = 0;
		String matchDate = "";
		JSONArray jo = JSON.parseArray(aoData);
//		System.out.println(jo);
//		[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,{"name":"matchDate","value":""},{"name":"name","value":""},{"name":"type","value":""},{"name":"address","value":""}]

		if("sEcho".equals(JSON.parseObject(jo.get(0).toString()).get("name"))){
			sEcho = JSON.parseObject(jo.get(0).toString()).get("value").toString();
		}
		if("iDisplayStart".equals(JSON.parseObject(jo.get(3).toString()).get("name"))){
			iDisplayStart = Integer.parseInt(JSON.parseObject(jo.get(3).toString()).get("value").toString());
		}
		if("iDisplayLength".equals(JSON.parseObject(jo.get(4).toString()).get("name"))){
			iDisplayLength = Integer.parseInt(JSON.parseObject(jo.get(4).toString()).get("value").toString());
		}
		if("matchDate".equals(JSON.parseObject(jo.get(31).toString()).get("name"))){
			matchDate = JSON.parseObject(jo.get(31).toString()).get("value").toString();
		}
		HashMap mapCondition = new HashMap<String, Object>();//条件
		if(!"".equals(matchDate.trim())){
			mapCondition.put("beginTime", matchDate.split("[ - ]")[0]);
			mapCondition.put("endTime", matchDate.split("[ - ]")[2] +" 23:59:59");
		}else{
			mapCondition.put("beginTime", "");
			mapCondition.put("endTime", "");
		}
		mapCondition.put("name", !JSON.parseObject(jo.get(32).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(32).toString()).get("value").toString():"");
		mapCondition.put("type", !JSON.parseObject(jo.get(33).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(33).toString()).get("value").toString():"");
		mapCondition.put("address", !JSON.parseObject(jo.get(34).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(34).toString()).get("value").toString():"");
		mapCondition.put("curPage", iDisplayStart);
		mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
		
		HashMap strmap = adminMatchBIZ.queryMatchList(mapCondition);
		if(Integer.parseInt(strmap.get("result").toString()) == 0){
			PageBean pageBean = (PageBean)strmap.get("page");
			map.put("result", 0);
			map.put("aaData", pageBean.getList());
			map.put("sEcho", sEcho);
			map.put("iTotalRecords", strmap.get("totalCount"));
			map.put("iTotalDisplayRecords", strmap.get("totalCount"));
		}
		mv.setViewName("/admin_match_list");
		return map;
    }
	
	/**
	 * 前往保存页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goMatchSave")
	public String goMatchSave(Model model,HttpServletRequest request){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String id = request.getParameter("id");
		if(id != null && id != "null" && !"".equals(id)){
			Match ac =  adminMatchBIZ.queryMatchById(id);
			model.addAttribute("match", ac);
			model.addAttribute("beginTime", df.format(ac.getBeginTime()));
			model.addAttribute("endTime", df.format(ac.getEndTime()));
			model.addAttribute("imgUrl", SysConfig.IMG_VISIT_URL+ac.getImgUrl());
		}
		model.addAttribute("matchCategory", adminMatchCategoryBIZ.queryMatchCategoryNotLimit());
		return "match/admin_match_save";
	}
	
	/**
	 * 前往List页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goMatchList")
	public String goMatchList(Model model){
		model.addAttribute("baseUrl", SysConfig.IMG_VISIT_URL);
		model.addAttribute("matchCategory", adminMatchCategoryBIZ.queryMatchCategoryNotLimit());
		return "match/admin_match_list";
	}
	
	/**
	 * 保存赛事
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 * @throws IOException 
	 */
	@RequestMapping(value = "/saveMatch")
	@ResponseBody
	public Model saveMatch(Model model,HttpServletRequest request) throws SQLException, ParseException, IOException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String id = request.getParameter("id");
		Match ac = new Match();
		ac.setName(request.getParameter("name"));
		ac.setAddress(request.getParameter("address"));
		ac.setPrice(Double.parseDouble(request.getParameter("price")));
		ac.setImgText(request.getParameter("imgText"));
		ac.setBeginTime(df.parse(request.getParameter("matchDate").split(",")[0]));
		ac.setEndTime(df.parse(request.getParameter("matchDate").split(",")[1]));
		ac.setContent(request.getParameter("content"));
		ac.setMarketPrice(Double.parseDouble(request.getParameter("marketPrice")));
		ac.setIsLock(Integer.parseInt(request.getParameter("isLock")));
		ac.setCategoryId(Long.parseLong(request.getParameter("categoryId")));
		if(request.getParameter("enrollNum") != null && !"".equals(request.getParameter("enrollNum")) && request.getParameter("enrollNum") != "null"){
			ac.setEnrollNum(Integer.parseInt(request.getParameter("enrollNum")));
		}else{
			ac.setEnrollNum(0);
		}
		String imgText = request.getParameter("imgText");
		String url = request.getParameter("url");
		if(url != null && !"".equals(url) && url != "null"){
			if("http://ocjp9x6x9.bkt.clouddn.com/".equals(SysConfig.IMG_VISIT_URL)){
				ac.setImgUrl(url.substring(33, 60));
			}else if("http://image.mperfit.com/".equals(SysConfig.IMG_VISIT_URL)){
				ac.setImgUrl(url.substring(25, 52));
			}
		}
		
		ac.setImgText(imgText);
		
		if(request.getParameter("sort") != null && request.getParameter("sort") !="null" && !"".equals(request.getParameter("sort"))){
			ac.setSort(Integer.parseInt(request.getParameter("sort")));
		}else{
			ac.setSort(99);
		}
		
		if(request.getParameter("specificAddress") != null && !"".equals(request.getParameter("specificAddress")) && request.getParameter("specificAddress") != "null"){
			String specificAddress = request.getParameter("specificAddress");
			ac.setSpecificAddress(specificAddress);
			ac.setLongitude(Double.parseDouble(GetXY.getGaoDeXY(specificAddress.replaceAll("\\s*", "").trim()).split(",")[0]));
			ac.setLatitude(Double.parseDouble(GetXY.getGaoDeXY(specificAddress.replaceAll("\\s*", "").trim()).split(",")[1]));
		}
		
		try {
			if(id != "null" && id != null && !"".equals(id)){
				ac.setId(Long.parseLong(id));
				adminMatchBIZ.updateMatch(ac);
			}else{
				ac.setId(IdWorker.getId());
				adminMatchBIZ.saveMatch(ac);
			}
			model.addAttribute("flag", "success");
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
	@RequestMapping(value = "/deleteMatch",method=RequestMethod.POST)
	@ResponseBody
	public Model deleteMatch(Model model,HttpServletRequest request){
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
			adminMatchBIZ.deleteMatch(map);
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
	
	/**
	 * 图文
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findMatchText")
	@ResponseBody
	public Model findMatchText(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		try {
			model.addAttribute("imgText", adminMatchBIZ.queryMatchById(id).getImgText());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
	
	/**
	 * 置顶
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/topMatch",method=RequestMethod.POST)
	@ResponseBody
	public Model topMatch(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		HashMap map = new HashMap();
		map.put("id", id);
		try {
			adminMatchBIZ.topMatch(map);
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
}
