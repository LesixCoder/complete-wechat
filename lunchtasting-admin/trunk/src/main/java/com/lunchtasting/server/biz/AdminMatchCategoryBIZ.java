package com.lunchtasting.server.biz;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.MatchCategory;

public interface AdminMatchCategoryBIZ {
	Long saveMatchCategory(MatchCategory matchCategory);
	Long updateMatchCategory(MatchCategory matchCategory);
	HashMap queryMatchCategoryList(HashMap map);
	MatchCategory queryMatchCategoryById(String id);
	int queryMatchCategoryCount(HashMap map);
	int deleteMatchCategory(HashMap map);
	List queryMatchCategoryNotLimit();
	int queryMatchCategoryByName(String name);
}
