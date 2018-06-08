package com.lunchtasting.server.dao.gym;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.gym.Coach;

public interface CoachDAO extends GenericDAO<Coach> {

	/**
	 * 获得教练详情
	 * @param coachId
	 * @return
	 */
	Map getCoachDetail(Long coachId);
	
	/**
	 * 查询教练相册列表
	 * @param coachId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryCoachAlbumList(Long coachId,Integer page,Integer pageSize);
}
