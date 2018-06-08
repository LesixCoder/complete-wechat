package com.lunchtasting.server.dao.seller;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Area;

public interface AreaDAO extends GenericDAO<Area>  {

	/**
	 * 查询商圈列表
	 * @return
	 */
	List queryAreaList();
}
