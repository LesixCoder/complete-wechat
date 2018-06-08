package com.lunchtasting.server.biz.gym;

import java.util.List;
import java.util.Map;

public interface GymBIZ {
	
	/**
	 * 查询健身房列表
	 * @return
	 */
	List queryGymList();
	
	/**
	 * 查询健身房详情
	 * @param gymId
	 * @return
	 */
	Map getGymDetail(Long gymId);
	
	/**
	 * 查询课程下的健身房列表
	 * @param courseId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryCourseGymList(Long courseId,Integer page,Integer pageSize);
}
