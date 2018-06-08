package com.lunchtasting.server.biz.common;

import java.util.List;

import com.lunchtasting.server.po.lt.CommonCollect;

public interface CommonCollectBIZ {

	/**
	 * 创建一个用户收藏
	 * @param userId
	 * @param bizId
	 * @param bizType
	 * @return
	 */
	Boolean createCollect(Long userId,Long bizId,Integer bizType);
	
	/**
	 * 判断用户是否收藏过某个类别
	 * @param userId
	 * @param bizId
	 * @param bizType
	 * @return
	 */
	Boolean checkUserCollect(Long userId,Long bizId,Integer bizType);
	
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
	List queryActivtyCollectList(Long userId,Integer page,Integer pageSize) throws Exception;
	
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
	Boolean deleteCollect(Long userId,Integer bizType,String ids);

}
