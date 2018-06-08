package com.lunchtasting.server.dao.meals;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Meals;

public interface MealsDAO extends GenericDAO<Meals> {
	/**
	 * 查找今日菜品
	 * @return
	 */
	List queryTodayMeals(HashMap map);
	
	/**
	 * 根据ID查询菜品
	 * @param map
	 * @return
	 */
	List queryMealById(HashMap map);
	
	/**
	 * 根据ID查询菜品  对象
	 * @param map
	 * @return
	 */
	Meals queryMealByIdTo(HashMap map);
	
	/**
	 * 修改已定数量
	 * @param map
	 * @return
	 */
	int updateMealsBuyNum(HashMap map);
	
}
