package com.lunchtasting.server.biz.common;

public interface CommonPraiseBIZ {

	/**
	 * 创建一个用户点赞
	 * @param userId
	 * @param bizId
	 * @param bizType
	 * @return
	 */
	Boolean createPraise(Long userId,Long bizId,Integer bizType);
	
	/**
	 * 判断用户是否收藏过某个类别
	 * @param userId
	 * @param bizId
	 * @param bizType
	 * @return
	 */
	Boolean checkUserPraise(Long userId,Long bizId,Integer bizType);
}
