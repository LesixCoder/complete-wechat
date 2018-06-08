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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lunchtasting.server.biz.AdminTopicBIZ;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.po.lt.Topic;
import com.lunchtasting.server.util.IdWorker;

@Controller
public class AdminTopicController {
	
	@Autowired
	private AdminTopicBIZ adminTopicBIZ;
	
	/**
	 * 查询帖子话题列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryTopicList")
	@ResponseBody
	public Object queryTopicList(HttpServletRequest request){
		Map map = new HashMap();//返回结果
		String aoData = request.getParameter("aoData");
		String sEcho = "";
		int iDisplayStart =0;
		int iDisplayLength = 0;
		JSONArray jo = JSON.parseArray(aoData);
//        System.out.println(aoData);//[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,{"name":"sellerId","value":""},{"name":"status","value":""}]

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
//		mapCondition.put("sellerId", !JSON.parseObject(jo.get(39).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(39).toString()).get("value").toString():"");
//		mapCondition.put("flag", !JSON.parseObject(jo.get(40).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(40).toString()).get("value").toString():"");
		mapCondition.put("curPage", iDisplayStart);
		mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
		
		HashMap strmap = adminTopicBIZ.queryTopicList(mapCondition);
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
	@RequestMapping(value = "/goTopicList")
	public String goTopicList(Model model){
		return "topic/admin_topic_list";
	}
	
	/**
	 * 启用或禁用
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateTopic",method=RequestMethod.POST)
	@ResponseBody
	public Model updateTopic(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		HashMap map = new HashMap();
		if(type.equals("pub")){
			map.put("flag", 0);
		}else if(type.equals("cel")){
			map.put("flag", 1);
		}else if("del".equals(type)){
			map.put("flag", 2);
		}
		map.put("id", id);
		try {
			adminTopicBIZ.updateTopic(map);
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
	
	/**
	 * 前往保存页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goTopicSave")
	public String goNoteSave(Model model,HttpServletRequest request){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String id = request.getParameter("id");
		if(id != null && id != "null" && id != ""){
			Topic ac =  adminTopicBIZ.queryTopicById(id);
			model.addAttribute("bean", ac);
		}
		return "topic/admin_topic_save";
	}
	
	/**
	 * 保存帖子话题
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 * @throws IOException 
	 */
	@RequestMapping(value = "/saveTopic")
	@ResponseBody
	public Model saveTopic(Model model,HttpServletRequest request) throws SQLException, ParseException, IOException{
		String id = request.getParameter("id");
		Topic ac = new Topic();
		String name = request.getParameter("name");
		ac.setName(name);
		ac.setType(Integer.parseInt(request.getParameter("type")));
		if(request.getParameter("sort") != null && !"null".equals(request.getParameter("sort")) && !"".equals(request.getParameter("sort"))){
			ac.setSort(Integer.parseInt(request.getParameter("sort")));
		}else{
			ac.setSort(99);
		}
		
		try {
			if(id != "null" && id != null && !"".equals(id)){
				ac.setId(Long.parseLong(id));
				if(name.equals(adminTopicBIZ.queryTopicById(id).getName())){
					adminTopicBIZ.updateTopic(ac);
					model.addAttribute("flag", "success");
				}else{
					if(adminTopicBIZ.queryNameCount(name) == 0){
						adminTopicBIZ.updateTopic(ac);
						model.addAttribute("flag", "success");
					}else{
						model.addAttribute("flag", "fail");
						model.addAttribute("msg", "已有此名称!");
					}
				}
			}else{
				if(adminTopicBIZ.queryNameCount(name) == 0){
					ac.setId(IdWorker.getId());
					adminTopicBIZ.addTopic(ac);
					model.addAttribute("flag", "success");
				}else{
					model.addAttribute("flag", "fail");
					model.addAttribute("msg", "已有此名称!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
			return null;
		}
		return model;
	}
	public static void main(String[] args) {
		 
	}
}
