package com.lunchtasting.server.biz.area;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.Area;
import com.lunchtasting.server.vo.DataModel;

public interface AreaBIZ {
	/**
	 * 前台查询地址
	 * @return
	 */
	List<Area> queryAreaListNew();
	
	
	/**
	 * 前台大厦地址
	 * @return
	 */
	List<DataModel> queryEdificeList(HashMap map);
}
