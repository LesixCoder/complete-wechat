package com.lunchtasting.server.dao;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.MatchCategory;
import com.lunchtasting.server.po.lt.Medal;

public interface AdminMedalDao extends GenericDAO<Medal>{
	Long saveMedal(Medal medal);
	Long updateMedal(Medal medal);
	List queryMedalList(HashMap map);
	Medal queryMedalById(String id);
	int queryMedalCount(HashMap map);
	int deleteMedal(String id);
	int queryMedalByName(String name);
	List queryMedalNotLimit();
}
