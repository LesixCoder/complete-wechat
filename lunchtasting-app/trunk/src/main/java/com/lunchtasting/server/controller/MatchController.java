package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.match.MatchBIZ;
import com.lunchtasting.server.biz.match.MatchOrderBIZ;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.po.lt.Match;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/match")
public class MatchController {
	
	@Autowired
	private MatchBIZ matchBIZ;
	@Autowired
	private MatchOrderBIZ matchOrderBIZ;
	
	
	/**
	 * 赛事列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/category/list" )
	@ResponseBody
	public Object categoryList(HttpServletRequest request) throws Exception {
		
		try {
			Long userId = EncryptAuth.getUserId(request);
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", matchBIZ.queryMatchCategoryList(userId));
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	/**
	 * 赛事列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		String cId = request.getParameter("category_id");
		if(!ValidatorHelper.isNumber(cId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		int pPage = 1;
		Integer type = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		
		try {
			Long categoryId = Long.parseLong(cId);
			
			Map map = new HashMap();
			map.put("list",matchBIZ.queryMatchList(categoryId,(pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			map.put("total_page", Utils.getTotalPage(matchBIZ.getMatchCount(categoryId),VariableConfig.PAGE_SIZE));
			map.put("current_page", pPage);	 
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, map, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	/**
	 * 赛事详情
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/detail")
	@ResponseBody
	public Object detail(HttpServletRequest request) throws Exception {
		String mId = request.getParameter("match_id");
		if(!ValidatorHelper.isNumber(mId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			Long userId = EncryptAuth.getUserId(request);
			
			/**
			 * 赛事
			 */
			Map match = matchBIZ.getMatchDetail(Long.parseLong(mId),userId);
			if(match == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
			}
			
			Map dataMap = new HashMap();
			dataMap.put("match", match);
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	/**
	 * 赛事报名
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/enroll/add" )
	@ResponseBody
	public Object enrollAdd(HttpServletRequest request) throws Exception {
		String mId = request.getParameter("match_id");
		if(!ValidatorHelper.isNumber(mId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			long userId = EncryptAuth.getUserId(request);
			long matchId = Long.parseLong(mId);
			
			Match match = matchBIZ.getById(matchId);
			if(match == null || match.getPrice() > 0){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,"请先通过PERFIT公众号报名，谢谢！", request);
			}
			
			/**
			 * 判断用户已经报名过
			 */
			boolean enrollR = matchOrderBIZ.checkUserEnroll(matchId, userId);
			if(enrollR){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, "你已经报过名啦！", request);
			}
			
//			/**
//			 * 判断是否可以报名
//			 */
//			boolean checkEnroll = enrollBIZ.checkIsEnroll(activityId, userId);
//			if(checkEnroll == false){
//				return MapResult.build(MapResult.CODE_PARAM_ERROR, "当前活动不能报名！", request);
//			}
			
			boolean result = matchOrderBIZ.createMatchOrder(matchId, userId);
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
