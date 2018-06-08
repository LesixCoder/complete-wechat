package com.lunchtasting.server.biz.common;

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
}
