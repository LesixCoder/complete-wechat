package com.lunchtasting.server.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.meals.MealsBIZ;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.po.lt.Area;
import com.lunchtasting.server.po.lt.Meals;

/**
 * 菜品模块
 * @author xq
 *
 */
@Controller
@RequestMapping("/meal")
public class MealsController {
	
	@Autowired
	private MealsBIZ mealsBIZ;
	
	/**
	 * 查找今日菜品
	 * @param request
	 * @param response
	 * @return chenchen
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryTodayMeals"/*,method=RequestMethod.POST*/)
	@ResponseBody
	public Object getAreaList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map map = new HashedMap();
		HashMap requestMap =  new  HashMap();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//时间
		requestMap.put("areaId",request.getParameter("areaId"));
		requestMap.put("supplyWeek", Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1);
		requestMap.put("deliveryDate", df.format(new Date()));
		try{
			List mealList = mealsBIZ.queryTodayMeals(requestMap);
			map.put("mealList", mealList);
			return MapResult.build(MapResult.CODE_SUCCESS, "成功！", map, request);
		}catch (Exception e) {
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, null, request);
		}
	}
	/**
	 * 查找今日菜品
	 * @param request
	 * @param response
	 * @return chenchen
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryMealById"/*,method=RequestMethod.POST*/)
	@ResponseBody
	public Object queryMealById(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map map = new HashedMap();
		int mealId;
		try{
			 mealId =  Integer.parseInt(request.getParameter("mealId"));
		}catch (Exception e) {
			// TODO: handle exception
			return MapResult.build(MapResult.CODE_SYS_ERROR, "参数存在问题！", null, request);
		}
		try{
			List meal = mealsBIZ.queryMealById(mealId);
			map.put("meal", meal);
			return MapResult.build(MapResult.CODE_SUCCESS, "成功！", map, request);
		}catch (Exception e) {
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, null, request);
		}
	}
}
