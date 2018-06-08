package com.lunchtasting.server.biz;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.Area;

public interface AdminAreaBIZ {
	List queryArea();
	Area queryAreaByName(String name);
	HashMap queryAreaList(HashMap map);
	Long addArea(Area area);
	Long updateArea(Area area);
	Long updateArea(HashMap map);
	int queryNameCount(String name);
	Area queryAreaById(String id);
}
