package com.lunchtasting.server.dao.area;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Area;
import com.lunchtasting.server.vo.DataModel;

public interface AreaDAO extends GenericDAO<Area> {

	/**
	 * 查询区域列表
	 * @param map
	 * @return chenchen
	 */
	List<Area> queryAreaList(HashMap map);
	
	/**
	 * 查询广告列表总数
	 * @param map
	 * @return chenchen
	 */
	int queryAreaListCount(HashMap map);
	
	/**
	 * 增加区域
	 * @param area
	 * @return chenchen
	 */
	int addArea(Area area);

	/**
	 * 修改区域
	 * @param area
	 * @return chenchen
	 */
	int updateArea(Area area);
	
	/**
	 * 删除区域
	 * @param id
	 * @return chenchen
	 */
	int deleteArea(int id);
	
	/**
	 * 查询区域
	 * @param map
	 * @return chenchen
	 */
    List<Area> queryAreaListNew(HashMap map);
    
	/**
	 * 查询大厦
	 * @param map
	 * @return chenchen
	 */
    List<DataModel> queryEdificeList(HashMap map);
}
