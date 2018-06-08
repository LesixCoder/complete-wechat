package com.lunchtasting.server.biz.gym;

import java.util.List;
import java.util.Map;

public interface CoachBIZ {
	
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
