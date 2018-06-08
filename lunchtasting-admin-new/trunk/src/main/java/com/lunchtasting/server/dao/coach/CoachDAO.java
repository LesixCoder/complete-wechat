package com.lunchtasting.server.dao.coach;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.gym.Coach;

public interface CoachDAO extends GenericDAO<Coach> {
	
	/**
	 * 查询教练列表
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryCoachList(Integer page,Integer pageSize);
	
	/**
	 * 查询简单教练列表
	 * @return
	 */
	List querySimpleCoachList();
	
	/**
	 * 获得一个需要编辑的教练信息
	 * @param coachId
	 * @return
	 */
	Map getEditCoach(Long coachId);
	
	/**
	 * 修改状态
	 * @param coachId
	 * @param flag
	 * @return
	 */
	Integer updateFlag(Long coachId,Integer flag);
	
	/**
	 * 查询下教练相册列表
	 * @param coachId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryCoachAlbumList(Long coachId,Integer page,Integer pageSize);
	
	/**
	 * 添加一张教练相册图片
	 * @param map
	 */
	void addCoachAlbum(Map map);
	
	/**
	 * 删除一张教练相册图片
	 * @param coachAlbumId
	 */
	void removeCoachAlbum(Long coachAlbumId);

}
