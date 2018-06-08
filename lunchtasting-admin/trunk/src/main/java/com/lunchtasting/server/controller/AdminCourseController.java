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
import com.lunchtasting.server.biz.AdminCourseBIZ;
import com.lunchtasting.server.biz.AdminCourseCategoryBIZ;
import com.lunchtasting.server.biz.AdminSellerBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.po.lt.Course;
import com.lunchtasting.server.util.IdWorker;

@Controller
public class AdminCourseController {
	@Autowired
    private AdminCourseBIZ adminCourseBIZ;
	@Autowired
	private AdminSellerBIZ adminSellerBIZ;
	@Autowired
	private AdminCourseCategoryBIZ adminCourseCategoryBIZ;
	/**
	 * 查询课程列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryCourseList")
	@ResponseBody
	public Object queryCourseList(HttpServletRequest request){
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
		mapCondition.put("sellerId", !JSON.parseObject(jo.get(39).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(39).toString()).get("value").toString():"");
		mapCondition.put("flag", !JSON.parseObject(jo.get(40).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(40).toString()).get("value").toString():"");
		mapCondition.put("curPage", iDisplayStart);
		mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
		
		HashMap strmap = adminCourseBIZ.queryCourse(mapCondition);
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
	@RequestMapping(value = "/goCourseList")
	public String goCourseList(Model model){
		model.addAttribute("sellerList", adminSellerBIZ.querySellerNotLimit(new HashMap()));
		return "course/admin_course_list";
	}
	
	/**
	 * 启用或禁用
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateCourse",method=RequestMethod.POST)
	@ResponseBody
	public Model updateCourse(Model model,HttpServletRequest request){
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
			adminCourseBIZ.updateCourse(map);
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
	@RequestMapping(value = "/goCourseSave")
	public String goCourseSave(Model model,HttpServletRequest request){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String id = request.getParameter("id");
		if(id != null && id != "null" && id != ""){
			Course ac =  adminCourseBIZ.queryCourseById(id);
			model.addAttribute("bean", ac);
			if(!"".equals(ac.getImgUrl()) && null != ac.getImgUrl()){
				model.addAttribute("imgUrl", SysConfig.IMG_VISIT_URL+ac.getImgUrl());
			}
		}
		model.addAttribute("baseUrl", SysConfig.IMG_VISIT_URL);
		model.addAttribute("sellerList", adminSellerBIZ.querySellerNotLimit(new HashMap()));
		model.addAttribute("level1List", adminCourseCategoryBIZ.getlevel2());
		return "course/admin_course_save";
	}
	
	/**
	 * 保存课程
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 * @throws IOException 
	 */
	@RequestMapping(value = "/saveCourse")
	@ResponseBody
	public Model saveCourse(Model model,HttpServletRequest request) throws SQLException, ParseException, IOException{
		String id = request.getParameter("id");
		Course ac = new Course();
		ac.setName(request.getParameter("name"));
		ac.setTitle(request.getParameter("title"));
		ac.setPrice(Double.parseDouble(request.getParameter("price")));
		ac.setBeginTime(request.getParameter("beginTime"));
		ac.setEndTime(request.getParameter("endTime"));
		ac.setCategoryId(Long.parseLong(request.getParameter("categoryId")));
		if(request.getParameter("sellerId") != null && request.getParameter("sellerId") != "" && request.getParameter("sellerId") != "null"){
			ac.setSellerId(Long.parseLong(request.getParameter("sellerId")));
		}
		ac.setContent(request.getParameter("content"));
		String url = request.getParameter("imgUrl");
		if(url != null && !"".equals(url) && url != "null"){
			if("http://ocjp9x6x9.bkt.clouddn.com/".equals(SysConfig.IMG_VISIT_URL)){
				ac.setImgUrl(url.substring(33, 61));
			}else if("http://image.mperfit.com/".equals(SysConfig.IMG_VISIT_URL)){
				ac.setImgUrl(url.substring(25, 53));
			}
		}
		ac.setMarketPrice(Double.parseDouble(request.getParameter("marketPrice")));
		
		ac.setImgArray(request.getParameter("imgArr"));
		
		try {
			if(id != "null" && id != null && id != ""){
				ac.setId(Long.parseLong(id));
				adminCourseBIZ.updateCourse(ac);
			}else{
				ac.setId(IdWorker.getId());
				adminCourseBIZ.addCourse(ac);
			}
			model.addAttribute("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("flag", "fail");
			return null;
		}
		return model;
	}
	
	/*public static void main(String[] args) {
		String s = "http://image.mperfit.com/course/20161128201509812.jpg";
		System.out.println(s.substring(25,53));
	}*/
}
