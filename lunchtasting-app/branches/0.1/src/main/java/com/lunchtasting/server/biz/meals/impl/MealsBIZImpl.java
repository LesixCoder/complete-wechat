package com.lunchtasting.server.biz.meals.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.meals.MealsBIZ;
import com.lunchtasting.server.dao.meals.MealsDAO;
import com.lunchtasting.server.po.lt.Meals;

@Service 
public class MealsBIZImpl implements MealsBIZ {
	@Autowired
	private MealsDAO mealsDAO;

	@Override
	public List queryTodayMeals(HashMap map) {
		// TODO Auto-generated method stub
		return mealsDAO.queryTodayMeals(map);
	}

	@Override
	public List queryMealById(Integer mealId) {
		// TODO Auto-generated method stub
		if(mealId==null || mealId==0){
			return null;
		}
		HashMap map = new HashMap();
		map.put("mealId", mealId);
		return mealsDAO.queryMealById(map);
	}
	
	
}
