package com.lunchtasting.server.dao;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.MatchCategory;

public interface AdminMatchCategoryDao extends GenericDAO<MatchCategory>{
	Long saveMatchCategory(MatchCategory matchCategory);
	Long updateMatchCategory(MatchCategory matchCategory);
	List queryMatchCategoryList(HashMap map);
	MatchCategory queryMatchCategoryById(String id);
	int queryMatchCategoryCount(HashMap map);
	int deleteMatchCategory(HashMap map);
	List queryMatchCategoryNotLimit();
	int getMatchCategoryByName(String name);
}
