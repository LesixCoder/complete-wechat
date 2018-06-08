package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.activity.ActivityBIZ;
import com.lunchtasting.server.biz.seller.AreaBIZ;
import com.lunchtasting.server.biz.seller.SellerBIZ;
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
	
	
	/**
	 * 场馆区域列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/area/list")
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
	@RequestMapping(value = "/list")
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
	@RequestMapping(value = "/detail")
	@ResponseBody
	public Object detail(HttpServletRequest request) throws Exception {
		String sId = request.getParameter("seller_id");
		if(!ValidatorHelper.isNumber(sId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			Long userId = EncryptAuth.getUserId(request);
			Long sellerId = Long.parseLong(sId);
			
			/**
			 * 商家详情
			 */
			Map seller = sellerBIZ.getSellerDetail(sellerId,userId);
			if(seller == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
			}
			
			/**
			 * 商家下的活动列表
			 */
			List activityList = activityBIZ.queryVenueActivityList(sellerId);
			
			Map dataMap = new HashMap();
			dataMap.put("seller", seller);
			dataMap.put("activity_list", activityList);
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
}
