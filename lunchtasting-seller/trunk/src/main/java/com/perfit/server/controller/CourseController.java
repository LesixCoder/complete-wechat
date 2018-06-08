package com.perfit.server.controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.po.lt.Course;
import com.lunchtasting.server.po.lt.CourseCategory;
import com.lunchtasting.server.po.lt.Seller;
import com.lunchtasting.server.util.ValidatorHelper;
import com.perfit.server.biz.course.CourseBIZ;
import com.perfit.server.biz.course.CourseCategoryBIZ;
import com.perfit.server.helper.MapResult;
import com.perfit.server.helper.VariableHelper;
@Controller
public class CourseController extends BaseController{
	@Autowired 
	private CourseBIZ courseBIZ;
	@Autowired 
	private CourseCategoryBIZ courseCategoryBIZ;	
	
	@RequestMapping(value = "toCourseList")
	public String index(){
		return "course/course_list";
	}
	@RequestMapping(value = "/getCourseList")
	@ResponseBody
	public Object getCourseList(HttpServletRequest request){
		String sEcho = "";
		int iDisplayStart =0;
		int iDisplayLength = 0;
		String activityDate = "";
		HashMap mapCondition = new HashMap<String, Object>();//条件
		try{
			Seller seller = (Seller)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
			String aoData = request.getParameter("aoData");
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
			if("date".equals(JSON.parseObject(jo.get(27).toString()).get("name"))){
				activityDate = JSON.parseObject(jo.get(27).toString()).get("value").toString();
			}
/*			if(!"".equals(activityDate.trim())){
				mapCondition.put("beginTime", activityDate.split("[ - ]")[0]);
				mapCondition.put("endTime", activityDate.split("[ - ]")[2] +" 23:59:59");
			}else{
				mapCondition.put("beginTime", "");
				mapCondition.put("endTime", "");
			}*/
			mapCondition.put("name", !JSON.parseObject(jo.get(28).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(28).toString()).get("value").toString():"");
			mapCondition.put("flag", !JSON.parseObject(jo.get(29).toString()).get("value").toString().equals("null")?JSON.parseObject(jo.get(29).toString()).get("value").toString():"");
			mapCondition.put("curPage", iDisplayStart);
			mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
			//商家id
			mapCondition.put("sellerId", seller.getId());

		}catch (Exception e) {
			return MapResult.build(1,"参数存在问题",null);//参数存在问题 查询失败
		}
		try{
			List list = courseBIZ.queryCourseList(mapCondition);
			int con = courseBIZ.queryCourseCount(mapCondition);
			if(ValidatorHelper.isNotEmpty(list)){
				for (Object object : list) {
					HashMap map = (HashMap)object;
					map.put("id", map.get("id").toString()+"");
				}
			}
			return MapResult.getPaging(0,list, sEcho,con);
		}catch (Exception e) {
			return  MapResult.build(1,"服务器错误",null);//服务器报错 查询失败
		}
	}
	
	
	@RequestMapping(value = "/toSaveCourse")
	public String toSaveCourse(Model model,HttpServletRequest request){
		try{
			Seller seller = (Seller)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
			String id = request.getParameter("id");
			if(ValidatorHelper.isNumber(id) && seller!=null){
				Course course = courseBIZ.courseById(Long.parseLong(id), seller.getId());
				course.setImgUrl(VariableHelper.IMG_URL_HEAD+course.getImgUrl());
				String imgArr = course.getImgArray();
				if(imgArr!=null && !imgArr.equals("")){
					course.setImgArray(VariableHelper.IMG_URL_HEAD+imgArr.replaceAll(",",","+VariableHelper.IMG_URL_HEAD));
				}
				/*DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");*/
				/*model.addAttribute("beginTime", df.format(course.getBeginTime()));
				model.addAttribute("endTime", df.format(course.getEndTime()));	*/
				course.getCategoryId();
				model.addAttribute("bean",course);
				model.addAttribute("cid",course.getCategoryId());
			}
			List<CourseCategory> cyList = courseCategoryBIZ.queryCourseCategoryListByHead();
			List<CourseCategory> cyList2 = courseCategoryBIZ.queryCourseCategoryListByBelow(null);
			model.addAttribute("bean2",cyList);
			model.addAttribute("bean3",cyList2);
		}catch (Exception e) {
			return "course/course_save";
		}
		return "course/course_save";
	}
	
	@RequestMapping(value = "/saveCourse")
	@ResponseBody
	public Object saveCourse(HttpServletRequest request){
		Seller seller = (Seller)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
		boolean pf = false;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String id;
		String flag;
		try{
			id = request.getParameter("id");
			flag = request.getParameter("flag");
			Course course = new Course();
			course.setName(request.getParameter("name"));
			course.setContent(request.getParameter("content"));
			course.setBeginTime(request.getParameter("beginTime"));
			course.setEndTime(request.getParameter("endTime"));
			course.setPrice(Double.parseDouble(request.getParameter("price")));
			course.setMarketPrice(Double.parseDouble(request.getParameter("marketPrice")));
			course.setImgUrl(request.getParameter("imgurl"));
			course.setSellerId(seller.getId());
			String imgArr = request.getParameter("imgArr");
			String categoryId =  request.getParameter("categoryId");
			course.setCategoryId(Long.parseLong(categoryId));
			/**
			 * 多图
			 */
			if(imgArr!=null){
				imgArr = imgArr.replaceAll(VariableHelper.IMG_URL_HEAD,"");
				course.setImgArray(imgArr);
			}

			/**
			 * 新建or更新
			 */
			if(ValidatorHelper.isNumber(id)){
				course.setId(Long.parseLong(id));
				pf = courseBIZ.updateCourse(course);
			}else if(id==null || id.equals("")){
				course.setFlag(1);
				pf = courseBIZ.creat(course);
			}
		}catch (Exception e) {
			return MapResult.build(1,"服务器错误",null);
		}
		if(pf){
			return MapResult.build(0,"提交成功",null);
		}else{
			return MapResult.build(1,"提交失败",null);
		}
	}
	@RequestMapping(value = "/deleteCourse")
	@ResponseBody
	public Object deleteCourse(HttpServletRequest request){
		boolean pf = false;
		try{
			Seller seller = (Seller)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
			Course course = new Course();
			course.setId(Long.parseLong(request.getParameter("id")));
			course.setSellerId(seller.getId());
			course.setFlag(2);
			pf= courseBIZ.updateCourse(course);
		}catch (Exception e) {
			return "fail";
		}
		if(pf){
			return "success";
		}else{
			return "fail";
		}
	}
	@RequestMapping(value = "/pubCourse")
	@ResponseBody
	public Object pubCourse(HttpServletRequest request){
		boolean pf = false;
		try{
			Seller seller = (Seller)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
			Course course = new Course();
			course.setId(Long.parseLong(request.getParameter("id")));
			course.setSellerId(seller.getId());
			String type =request.getParameter("type");
			if(type.equals("pub")){
				course.setFlag(0);
			}else if(type.equals("cel")){
				course.setFlag(1);
			}
			pf= courseBIZ.updateCourse(course);
		}catch (Exception e) {
			return "fail";
		}
		if(pf){
			return "success";
		}else{
			return "fail";
		}
	}
}
