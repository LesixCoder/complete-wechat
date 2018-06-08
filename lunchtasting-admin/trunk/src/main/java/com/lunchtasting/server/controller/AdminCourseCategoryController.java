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
import com.lunchtasting.server.biz.AdminCourseCategoryBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.Course;
import com.lunchtasting.server.po.lt.CourseCategory;
import com.lunchtasting.server.util.IdWorker;
@Controller
public class AdminCourseCategoryController extends BaseController{
	
	@Autowired
	private AdminCourseCategoryBIZ adminCourseCategoryBIZ;
	
	/**
	 * 查询课程分类列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryCourseCategoryList")
	@ResponseBody
	public Object queryCourseCategoryList(HttpServletRequest request){
		Map map = new HashMap();//返回结果
		String aoData = request.getParameter("aoData");
		String sEcho = "";
		int iDisplayStart =0;
		int iDisplayLength = 0;
		JSONArray jo = JSON.parseArray(aoData);
        //System.out.println(aoData);//[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,{"name":"name","value":""},{"name":"level","value":""}]


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
		mapCondition.put("name", !JSON.parseObject(jo.get(19).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(19).toString()).get("value").toString():"");
		mapCondition.put("level", !JSON.parseObject(jo.get(20).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(20).toString()).get("value").toString():"");
		mapCondition.put("curPage", iDisplayStart);
		mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
		
		HashMap strmap = adminCourseCategoryBIZ.queryCourseCategory(mapCondition);
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
	@RequestMapping(value = "/goCourseCategoryList")
	public String goCourseCategoryList(Model model){
		model.addAttribute("courseCategoryFromLevel", adminCourseCategoryBIZ.queryCourseCategoryByLevel());
		return "courseCategory/admin_course_category_list";
	}
	
	/**
	 * 前往保存页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goCourseCategorySave")
	public String goCourseCategorySave(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		if(id != null && id != "null" && id != ""){
			CourseCategory ac =  adminCourseCategoryBIZ.queryCourseCategoryById(id);
			model.addAttribute("courseCategory", ac);
		}
		model.addAttribute("courseCategoryFromLevel", adminCourseCategoryBIZ.queryCourseCategoryByLevel());
		return "courseCategory/admin_course_category_save";
	}
	
	/**
	 * 保存课程分类
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 * @throws IOException 
	 */
	@RequestMapping(value = "/saveCourseCategory")
	@ResponseBody
	public Model saveCourseCategory(Model model,HttpServletRequest request) throws SQLException, ParseException, IOException{
		String id = request.getParameter("id");
		CourseCategory ac = new CourseCategory();
		ac.setName(request.getParameter("name"));
		ac.setLevel(Integer.parseInt(request.getParameter("level")));
		ac.setParentId(Integer.parseInt(request.getParameter("parentId")));
		if(request.getParameter("sort") != null && request.getParameter("sort") != "" && request.getParameter("sort") != "null"){
			ac.setSort(Integer.parseInt(request.getParameter("sort")));
		}else{
			ac.setSort(99);
		}
		
		HashMap map = null;
		
		try {
			map = new HashMap();
			map.put("name", request.getParameter("name"));
			if(id != "null" && id != null && id != ""){
				ac.setId(Long.parseLong(id));
				if(request.getParameter("name").equals(adminCourseCategoryBIZ.queryCourseCategoryById(id).getName())){
					adminCourseCategoryBIZ.updateCourseCategory(ac);
					model.addAttribute("flag", "success");
				}else{
					if(adminCourseCategoryBIZ.getCourseCategoryByName(map) == 0){
						adminCourseCategoryBIZ.addCourseCategory(ac);
						model.addAttribute("flag", "success");
					}else{
						model.addAttribute("flag", "fail");
						model.addAttribute("msg", "已有此分类!");
					}
				}
			}else{
				if(adminCourseCategoryBIZ.getCourseCategoryByName(map) == 0){
					adminCourseCategoryBIZ.addCourseCategory(ac);
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
	 * 启用或禁用或删除
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateCourseCategory",method=RequestMethod.POST)
	@ResponseBody
	public Model updateCourseCategory(Model model,HttpServletRequest request){
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		String level = request.getParameter("level");
		HashMap map = new HashMap();
		HashMap mapBach = new HashMap();
		if(type.equals("pub")){
			map.put("flag", 0);
		}else if(type.equals("cel")){
			map.put("flag", 1);
		}else if("del".equals(type)){
			map.put("flag", 2);
		}
		map.put("id", id);
		mapBach.put("id", id);
		try {
			if("1".equals(level) && "del".equals(type)){
				mapBach.put("flag", 2);
				adminCourseCategoryBIZ.deleteCategoryByParentId(mapBach);
			}else if("1".equals(level) && "pub".equals(type)){
				mapBach.put("flag", 0);
				adminCourseCategoryBIZ.deleteCategoryByParentId(mapBach);
			}else if("1".equals(level) && "cel".equals(type)){
				mapBach.put("flag", 1);
				adminCourseCategoryBIZ.deleteCategoryByParentId(mapBach);
			}
			    adminCourseCategoryBIZ.updateCourseCategory(map);
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
		}
		return model;
	}
}
