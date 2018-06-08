package com.lunchtasting.server.dao.common;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.CommonCollect;

public interface CommonCollectDAO extends GenericDAO<CommonCollect> {

	Long getCollectId(Long userId, Long bizId, Integer bizType);
	
	/**
	 * 用户收藏总数
	 * @param userId
	 * @param bizType
	 * @return
	 */
	Integer getUserCollectCount(Long userId,Integer bizType);
	
	/**
	 * 活动收藏列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryActivtyCollectList(Long userId,Integer page,Integer pageSize);
	
	/**
	 * 文章收藏列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryArticleCollectList(Long userId,Integer page,Integer pageSize);
	
	/**
	 * 用户取消收藏
	 * @param userId
	 * @param bizType
	 * @param ids
	 * @return
	 */
	Integer deleteCollect(Long userId,Integer bizType,String ids);
}
