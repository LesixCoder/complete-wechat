package com.lunchtasting.server.biz.coach;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.lunchtasting.server.po.lt.gym.Coach;

public interface CoachBIZ {
	
	Coach getById(Long id);
	
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
	 * 添加一个教练
	 * @param name
	 * @param gymId
	 * @param sex
	 * @param phone
	 * @param age
	 * @param birth
	 * @param coachYear
	 * @param certificate
	 * @param imgUrl
	 * @return
	 */
	Boolean addCoach(String name,Long gymId,Integer sex,String phone
			,Integer age,String birth,Integer coachYear,String certificate,String imgUrl);
	
	/**
	 * 获得一个需要编辑的教练信息
	 * @param coachId
	 * @return
	 */
	Map getEditCoach(Long coachId);
	
	/**
	 * 编辑教练信息
	 * @param coachId
	 * @param name
	 * @param gymId
	 * @param sex
	 * @param phone
	 * @param age
	 * @param birth
	 * @param coachYear
	 * @param certificate
	 * @param imgUrl
	 * @return
	 */
	Boolean editCoach(Long coachId,String name,Long gymId,Integer sex,String phone
			,Integer age,String birth,Integer coachYear,String certificate,String imgUrl);
	
	
	/**
	 * 修改状态
	 * @param coachId
	 * @param flag
	 * @return
	 */
	Boolean updateFlag(Long coachId,Integer flag);
	
	/**
	 * 查询教练相册列表
	 * @param coachId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryCoachAlbumList(Long coachId,Integer page,Integer pageSize);
	
	/**
	 * 添加一个教练相册图片
	 * @param coachId
	 * @param imgUrl
	 * @return
	 */
	Boolean addCoachAlbum(Long coachId,String imgUrl);
	
	/**
	 * 删除一张相册图片
	 * @param coachAlbumId
	 * @return
	 */
	Boolean removeCoachAlbum(Long coachAlbumId);
}

