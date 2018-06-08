package com.perfit.server.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lunchtasting.server.mvc.BaseController;
import com.lunchtasting.server.util.ValidatorHelper;
import com.perfit.server.biz.activity.ActivityBIZ;
import com.perfit.server.biz.activity.ActivityEnrollBIZ;
import com.perfit.server.helper.MapResult;
import com.perfit.server.helper.VariableHelper;

@Controller
public class ActivityEnrollController extends BaseController{
	@Autowired
	private ActivityEnrollBIZ activityEnrollBIZ;
	
	/**
	 * 查询活动报名列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryActivityEnrollList")
	@ResponseBody
	public Object queryActivityEnrollList(HttpServletRequest request){
		String aoData = request.getParameter("aoData");
		String sEcho = "";
		int iDisplayStart =0;
		int iDisplayLength = 0;
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
		HashMap mapCondition = new HashMap<String, Object>();//条件
		mapCondition.put("curPage", iDisplayStart);
		mapCondition.put("pageSize", VariableHelper.PAGE_SIZE);
		String activityId=(String) request.getSession().getAttribute("activityId");
		if(!ValidatorHelper.isNumber(activityId)){
			return null;
		}
		mapCondition.put("activityId", activityId);
		List list = null;
		int con=0;
		try{
			list = activityEnrollBIZ.queryEnrollerList(mapCondition);
			con = activityEnrollBIZ.queryEnrollerListCount(Long.parseLong(activityId));
		}catch (Exception e) {
			return MapResult.getPaging(1,null,null,0);
		}
		return MapResult.getPaging(0, list, sEcho, con);
	}
	@RequestMapping(value = "/goActivityEnrollList")
	public String goActivityEnrollList(HttpServletRequest request){
		String id =request.getParameter("id");
		if(ValidatorHelper.isNumber(id)){
			request.getSession().setAttribute("activityId",id);
		}
		return "/activity_enroll_list";
	}
}
