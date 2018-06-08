package com.lunchtasting.server.biz.meals;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.Meals;

public interface MealsBIZ {
	/**
	 * 查询今日菜品
	 * @param map
	 * @return chenchen
	 */
	List queryTodayMeals(HashMap map);
	
	/**
	 * 根据ID查询菜品
	 * @param map
	 * @return
	 */
	List queryMealById(Integer mealId);
}
