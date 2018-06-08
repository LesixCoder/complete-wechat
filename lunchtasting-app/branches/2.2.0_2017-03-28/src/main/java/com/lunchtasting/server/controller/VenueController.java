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

import com.lunchtasting.server.biz.activity.ActivityBIZ;
import com.lunchtasting.server.biz.course.CourseBIZ;
import com.lunchtasting.server.biz.orders.OrdersBIZ;
import com.lunchtasting.server.biz.seller.AreaBIZ;
import com.lunchtasting.server.biz.seller.SellerBIZ;
import com.lunchtasting.server.biz.seller.SellerCommentBIZ;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 场馆模块
 * @author xq
 *
 */
@Controller
@RequestMapping("/venue")
public class VenueController {
	
	@Autowired
	private SellerBIZ sellerBIZ;
	@Autowired
	private ActivityBIZ activityBIZ;
	@Autowired
	private AreaBIZ areaBIZ;
	@Autowired
	private SellerCommentBIZ sellerCommentBIZ;
	@Autowired
	private CourseBIZ courseBIZ;
	@Autowired
	private OrdersBIZ ordersBIZ;
	
	
	/**
	 * 场馆区域列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/area/list" , method=RequestMethod.POST)
	@ResponseBody
	public Object areaList(HttpServletRequest request) throws Exception {
		String aId = request.getParameter("area_id");
		
		try {
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", areaBIZ.queryAreaList());
			dataMap.put("area_id", aId);
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	/**
	 * 场馆列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list" , method=RequestMethod.POST)
	@ResponseBody
	public Object list(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		String lon = request.getParameter("longitude");
		String lat = request.getParameter("latitude");
		String aId = request.getParameter("area_id");
		
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
		
		try {
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", sellerBIZ.querySellerList(areaId, longitude, latitude,(pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			dataMap.put("total_page",Utils.getTotalPage(sellerBIZ.getSellerCount(areaId),VariableConfig.PAGE_SIZE));
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	/**
	 * 场馆详情
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/detail" , method=RequestMethod.POST)
	@ResponseBody
	public Object detail(HttpServletRequest request) throws Exception {
		String sId = request.getParameter("seller_id");
		if(!ValidatorHelper.isNumber(sId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			Long userId = EncryptAuth.getUserId(request);
			long sellerId = Long.parseLong(sId);
			
			/**
			 * 商家详情
			 */
			Map sellerMap = sellerBIZ.getSellerDetail(sellerId,userId);
			if(sellerMap == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
			}
			//作假 spacecycle评分4.8
			if(sellerId == 778479287597531136l){
				sellerMap.put("score", 4.8);
			}
			
			/**
			 * 商家课程
			 */
			List courseList = courseBIZ.querySellerCourseList(sellerId, 0, 2);
			
			/**
			 * 商家下的活动列表
			 */
			List activityList = activityBIZ.queryVenueActivityList(sellerId);
			
			/**
			 * 商家评论
			 */
			List commentList = sellerCommentBIZ.querySellerCommentList(sellerId, 0, 2);
			
			Map dataMap = new HashMap();
			dataMap.put("seller", sellerMap);
			dataMap.put("course_list", courseList);
			dataMap.put("activity_list", activityList);
			dataMap.put("comment_list", commentList);
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	/**
	 * 场馆评论列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/comment/list" , method=RequestMethod.POST)
	@ResponseBody
	public Object commentList(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		String sId = request.getParameter("seller_id");
		
		if(!ValidatorHelper.isNumber(sId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		
		long sellerId = Long.parseLong(sId);
		
		try {
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", sellerCommentBIZ.querySellerCommentList(sellerId,
						(pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			
			dataMap.put("total_page",Utils.getTotalPage(sellerCommentBIZ.getSellerCommentCount(sellerId)
						,VariableConfig.PAGE_SIZE));
			
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	/**
	 * 场馆评论添加
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/comment/add" , method=RequestMethod.POST)
	@ResponseBody
	public Object commentAdd(HttpServletRequest request) throws Exception {
		String oId = request.getParameter("order_id");
		String score = request.getParameter("score");
		String content = request.getParameter("content");
		String imgArray = request.getParameter("img_array");
		
		if(!ValidatorHelper.isNumber(oId) 
				|| ValidatorHelper.isEmpty(score) || ValidatorHelper.isEmpty(content)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			long orderId = Long.parseLong(oId);
			long userId = EncryptAuth.getUserId(request);

			/**
			 * 判断当前订单是否可评价
			 */
			Map orderMap = ordersBIZ.getCommentOrder(orderId, userId);
			if(orderMap == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
			}
			long sellerId = Long.parseLong(orderMap.get("seller_id").toString());
			
			
			boolean result = sellerCommentBIZ.createComment(sellerId, userId,orderId,Integer.parseInt(score), content, imgArray);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
	
}
