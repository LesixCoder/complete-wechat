package com.lunchtasting.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.activity.ActivityBIZ;
import com.lunchtasting.server.biz.article.ArticleBIZ;
import com.lunchtasting.server.biz.common.BannerIndexBIZ;
import com.lunchtasting.server.biz.course.CourseBIZ;
import com.lunchtasting.server.biz.seller.SellerBIZ;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 首页模块
 * @author xq
 *
 */
@Controller
public class IndexController {
	
	@Autowired
	private ActivityBIZ activityBIZ;
	@Autowired
	private BannerIndexBIZ bannerIndexBIZ;
	@Autowired
	private ArticleBIZ articleBIZ;
	@Autowired
	private SellerBIZ sellerBIZ;
//	@Autowired
//	private CourseBIZ courseBIZ;

	/**
	 * 首页
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/index")
	@ResponseBody
	public Object index(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		
		try {
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", activityBIZ.queryIndexList((pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			dataMap.put("total_page",Utils.getTotalPage(activityBIZ.getIndexCount(),VariableConfig.PAGE_SIZE));
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, null, request);
		}
	}
	
	

	/**
	 * 首页列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/index_v2_0")
	@ResponseBody
	public Object indexV2_0(HttpServletRequest request) throws Exception {
		
		try {
			
			Map dataMap = new HashMap();
			
			/**
			 * 推荐图
			 */
			List bannerList =  bannerIndexBIZ.queryBannerIndexList(5);
			dataMap.put("banner_list", bannerList);
			
			/**
			 * 推荐商家
			 */
			List sellerList = sellerBIZ.queryRecommendSellerList(3);
			dataMap.put("seller_list", sellerList);
			
//			/**
//			 * 精选课程类目
//			 */
//			List courseCategoryList = courseBIZ.queryCourseCategoryList(1);
//			dataMap.put("course_category_list", courseCategoryList);
			
			
			/**
			 * 文章
			 */
			String articeleCategoryStr = articleBIZ.getArticleCategoryStr();
			dataMap.put("articele_category_str", articeleCategoryStr);
			
			List articeleList = articleBIZ.queryArticleList(null,0,VariableConfig.PAGE_SIZE);
			dataMap.put("articele_list", articeleList);
			
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, null, request);
		}
	}
}
