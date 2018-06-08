package com.lunchtasting.server.dao;

import java.util.HashMap;
import java.util.List;
import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Area;

public interface AdminAreaDao extends GenericDAO<Area>{
	
	List queryArea();
	Area queryAreaByName(String name);
	List queryAreaList(HashMap map);
	Long addArea(Area area);
	Long updateArea(Area area);
	Long updateArea(HashMap map);
	int queryNameCount(String name);
	int queryAreaCount(HashMap map);
	Area queryAreaById(String id);
}
