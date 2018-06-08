package com.lunchtasting.server.dao.temporaryEnroll;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.temporaryEnroll.TemporaryEnroll;

public interface TemporaryEnrollDAO extends GenericDAO<TemporaryEnroll>{
	/**
	 * 随机得到一条否和条件的
	 * @param map
	 * @return
	 */
	TemporaryEnroll getRandomOneRandom(HashMap map);
	/**
	 * 修改
	 * @param temporaryEnrol
	 * @return
	 */
	int updateTemporaryEnroll(TemporaryEnroll temporaryEnroll);
	
	
	int checkUser(String phone);
	/**
	 * 根据条件查找报名列表
	 * @param map
	 * @return
	 */
	TemporaryEnroll getTemporaryEnrollByMap(HashMap map);
	/**
	 * show
	 * @param map
	 * @return
	 */
	List show(HashMap map);
	
}
