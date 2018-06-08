package com.lunchtasting.server.dao.temporaryEnroll;

import java.util.HashMap;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.temporaryEnroll.EnrollGroup;

public interface EnrollGroupDAO  extends GenericDAO<EnrollGroup>{
	/**
	 * id得到组
	 * @param map
	 * @return
	 */
	EnrollGroup getEnrollGroupOne(HashMap map);
	/**
	 * 修改组信息
	 * @param enrollGroup
	 * @return
	 */
	Boolean updateEnrollGroup(EnrollGroup enrollGroup);
	/**
	 * 得到总个数
	 * @return
	 */
	int getAllCounut();
}
