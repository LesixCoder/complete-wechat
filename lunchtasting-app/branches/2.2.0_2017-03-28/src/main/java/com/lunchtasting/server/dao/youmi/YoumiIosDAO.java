package com.lunchtasting.server.dao.youmi;

import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.YoumiIos;

public interface YoumiIosDAO extends GenericDAO<YoumiIos>{
	/**
	 * 新建
	 * @param map
	 * @return
	 */
	Integer createYoumiIos(String idfa,String url);
	/**
	 * 更新
	 * @param url
	 * @param code
	 * @return
	 */
	Integer updateYoumi(String url,Integer code,String idfa);
	
	/**
	 * 查询
	 * @param idfa
	 * @return
	 */
	Map getYoumiByIdfa(String idfa);

}
