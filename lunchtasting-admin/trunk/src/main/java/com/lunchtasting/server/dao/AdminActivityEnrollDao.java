package com.lunchtasting.server.dao;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.ActivityEnroll;

public interface AdminActivityEnrollDao extends GenericDAO<ActivityEnroll>{
	List queryEnrollList(HashMap map);
	int queryEnrollListCount(HashMap map);
}
