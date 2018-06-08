package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.common.CommonCollectBIZ;
import com.lunchtasting.server.biz.user.UserBIZ;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 收藏模块
 * @author xq
 *
 */
@Controller
@RequestMapping("/collect")
public class CollectController {
	
	@Autowired
	private UserBIZ userBIZ;
	@Autowired
	private CommonCollectBIZ collectBIZ;

	@RequestMapping(value = "/add" , method=RequestMethod.POST)
	@ResponseBody
	public Object add(HttpServletRequest request) throws Exception {
		String bId = request.getParameter("biz_id");
		String bType = request.getParameter("biz_type");
		if(!ValidatorHelper.isNumber(bId) || !ValidatorHelper.isNumber(bType)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			long userId = EncryptAuth.getUserId(request);
			long bizId = Long.parseLong(bId);
			int bizType = Integer.parseInt(bType);
			
			/**
			 * 判断用户是否收藏过
			 */
			Boolean checkCollect = collectBIZ.checkUserCollect(userId, bizId, bizType);
			if(checkCollect){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, "亲，你已经收藏过了！", request);
			}
			
			/**
			 * 收藏
			 */
			Boolean result = collectBIZ.createCollect(userId, bizId, bizType);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, request);
			}
		} catch (Exception e) {
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
	
	/**
	 * 活动去掉收藏
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete" , method=RequestMethod.POST)
	@ResponseBody
	public Object delete(HttpServletRequest request) throws Exception {
		String bizIds = request.getParameter("biz_ids");
		String bType = request.getParameter("biz_type");
		if(ValidatorHelper.isEmpty(bizIds) || !ValidatorHelper.isNumber(bType)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			long userId = EncryptAuth.getUserId(request);
			int bizType = Integer.parseInt(bType);
			
			/**
			 * 取消收藏
			 */
			boolean result = collectBIZ.deleteCollect(userId, bizType, bizIds);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
	

	/**
	 * 活动收藏列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/activity/list" , method=RequestMethod.POST)
	@ResponseBody
	public Object activityList(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		
		
		try {
			long userId = EncryptAuth.getUserId(request);
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", collectBIZ.queryActivtyCollectList(userId,(pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			dataMap.put("total_page",Utils.getTotalPage(collectBIZ.getUserCollectCount(userId,StateEnum.COLLECT_ACTIVITY.getValue()),VariableConfig.PAGE_SIZE));
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	/**
	 * 文章收藏列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/article/list" , method=RequestMethod.POST)
	@ResponseBody
	public Object articleList(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		
		
		try {
			long userId = EncryptAuth.getUserId(request);
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", collectBIZ.queryArticleCollectList(userId,(pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			dataMap.put("total_page",Utils.getTotalPage(collectBIZ.getUserCollectCount(userId,StateEnum.COLLECT_ARTICLE.getValue()),VariableConfig.PAGE_SIZE));
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	/**
	 * 文章收藏列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/venue/list" , method=RequestMethod.POST)
	@ResponseBody
	public Object venueList(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		
		
		try {
			long userId = EncryptAuth.getUserId(request);
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", collectBIZ.querySellerCollectList(userId,(pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			dataMap.put("total_page",Utils.getTotalPage(collectBIZ.getUserCollectCount(userId,StateEnum.COLLECT_SELLER.getValue()),VariableConfig.PAGE_SIZE));
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
}
