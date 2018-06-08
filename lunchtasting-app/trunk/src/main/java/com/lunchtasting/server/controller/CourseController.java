package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.course.CourseBIZ;
import com.lunchtasting.server.biz.seller.AreaBIZ;
import com.lunchtasting.server.biz.seller.SellerCommentBIZ;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.po.lt.Course;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 课程模块
 * Created on 2016-10-9
 * @author xuqian
 *
 */
@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseBIZ courseBIZ;
	@Autowired
	private AreaBIZ areaBIZ;
	@Autowired
	private SellerCommentBIZ sellerCommentBIZ;
	
	
	/**
	 * 课程列表排序列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sort/list" , method=RequestMethod.POST)
	@ResponseBody
	public Object sortList(HttpServletRequest request) throws Exception {
		String areaId = request.getParameter("area_id");
		String categoryId = request.getParameter("category_id");
		String sortId = request.getParameter("sort_id");
		
		try {
			
			Map dataMap = new HashMap();
			
			dataMap.put("area_list", areaBIZ.queryAreaList());
			//dataMap.put("area_id", areaId);

			dataMap.put("category_list", courseBIZ.queryAllCourseCategoryList());
			//dataMap.put("category_id", categoryId);
			
			dataMap.put("sort_list", courseBIZ.queryCourseSortList());
			//dataMap.put("sort_id", sortId);
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}

	/**
	 * 课程列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list" , method=RequestMethod.POST)
	@ResponseBody
	public Object list(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		String aId = request.getParameter("area_id");
		String cId = request.getParameter("category_id");
		String sId = request.getParameter("sort_id");
		String lon = request.getParameter("longitude");
		String lat = request.getParameter("latitude");
		
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		
		Double longitude = null;
		if(ValidatorHelper.isNotEmpty(lon)){
			longitude = Double.parseDouble(lon);
		}
		
		Double latitude = null;
		if(ValidatorHelper.isNotEmpty(lat)){
			latitude = Double.parseDouble(lat);
		}
		
		Long areaId = null;
		if(ValidatorHelper.isNumber(aId)){
			if(!aId.equals("0")){
				areaId = Long.parseLong(aId);
			}
		}
		
		Long categoryId = null;
		if(ValidatorHelper.isNumber(cId)){
			if(!cId.equals("0")){
				categoryId = Long.parseLong(cId);
			}
		}
		
		Long sortId = null;
		if(ValidatorHelper.isNumber(sId)){
			if(!sId.equals("0")){
				sortId = Long.parseLong(sId);
			}
		}
		
		try {
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", courseBIZ.queryCourseList(areaId, categoryId, sortId, longitude, latitude
							,(pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			
			dataMap.put("total_page",Utils.getTotalPage(
							courseBIZ.getCourseCount(areaId, categoryId, sortId),VariableConfig.PAGE_SIZE));
			
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	
	/**
	 * 课程详情
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/detail" , method=RequestMethod.POST)
	@ResponseBody
	public Object detail(HttpServletRequest request) throws Exception {
		String cId = request.getParameter("course_id");
		if(!ValidatorHelper.isNumber(cId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		long courseId = Long.parseLong(cId);
		
		try {
			
			/**
			 * 课程不存在
			 */
			Map courseMap = courseBIZ.getCourseDtail(courseId);
			if(courseMap == null){
				return MapResult.build(20001, MapResult.MESSAGE_PARAMETER, request);
			}
			long sellerId = Long.parseLong(courseMap.get("seller_id").toString());
			
			
			/**
			 * 点评
			 */
			List commentList = sellerCommentBIZ.querySellerCommentList(sellerId, 0, 2);
			
			Map dataMap = new HashMap();
			dataMap.put("course", courseMap);
			dataMap.put("comment_list", commentList);
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
			
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
}
