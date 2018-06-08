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

import com.lunchtasting.server.biz.user.UserHotBIZ;
import com.lunchtasting.server.biz.user.UserScoreBIZ;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.po.lt.UserScore;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/rank")
public class RankingController {
	@Autowired
	private UserHotBIZ userHotBIZ;
	@Autowired
	private UserScoreBIZ userScoreBIZ;
	
	
	@RequestMapping(value = "/userHot")
	@ResponseBody
	public Object userHot(HttpServletRequest request){
		String page = request.getParameter("page");
		String timeString = request.getParameter("time");
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		Integer time = null;
		if(ValidatorHelper.isNumber(timeString)){
			time = Integer.parseInt(timeString);
		}
		
		try {
			//Long userId = 10019L;
			Long userId = EncryptAuth.getUserId(request);
			List list =  userHotBIZ.queryUserHotList((pPage-1)*VariableConfig.PAGE_SIZE,VariableConfig.PAGE_SIZE, time);
			Map dataMap = new HashMap();
			dataMap.put("list", list);
			dataMap.put("user_hot",userHotBIZ.getUserHotByUser(userId, time));
			dataMap.put("user_rank",userHotBIZ.getUserHotRank(userId, time));
			dataMap.put("total_page", 1);
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	@RequestMapping(value = "/userScore")
	@ResponseBody
	public Object userScore(HttpServletRequest request){
		String page = request.getParameter("page");
		String timeString = request.getParameter("time");
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		try {
			//Long userId = 10019L;
			Long userId = EncryptAuth.getUserId(request);
			List list =  userScoreBIZ.queryUserScoreRank((pPage-1)*VariableConfig.PAGE_SIZE,VariableConfig.PAGE_SIZE);
			Map dataMap = new HashMap();
			dataMap.put("list", list);
			dataMap.put("user_score",userScoreBIZ.getUserScoreTotal(userId));
			dataMap.put("user_rank",userScoreBIZ.getUserScoreRank(userId));
			dataMap.put("total_page",1);
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
}
