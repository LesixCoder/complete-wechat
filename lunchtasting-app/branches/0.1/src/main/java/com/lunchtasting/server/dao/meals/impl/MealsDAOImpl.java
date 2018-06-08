package com.lunchtasting.server.dao.meals.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.meals.MealsDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Meals;
@Repository
public class MealsDAOImpl extends GenericDAOSupport<Long, Meals> implements MealsDAO{

	@Override
	protected Class<?> getObjectClass() {
		return Meals.class;
	}

	@Override
	protected String getTableName() {
		return "meals";
	}

	@Override
	public List queryTodayMeals(HashMap map) {
		// TODO Auto-generated method stub
		if(map.get("areaId")==null){
			return null;
		}
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryTodayMeals", map);
	}

	@Override
	public List queryMealById(HashMap map) {
		// TODO Auto-generated method stub
		if(map.get("mealId")==null){
			return null;
		}
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryMealById", map);
	}

	@Override
	public int updateMealsBuyNum(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update(getNamespace() + ".queryMealById", map);
	}

	@Override
	public Meals queryMealByIdTo(HashMap map) {
		// TODO Auto-generated method stub
		return (Meals)getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryMealByIdTo", map);
	}
	
}
