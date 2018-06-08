package com.lunchtasting.server.biz.user;

import java.util.List;

public interface UserHotBIZ {
		/**
		 * 列表榜单
		 * @param page
		 * @param pageSize
		 * @param time
		 * @return
		 */
		List queryUserHotList(Integer page,Integer pageSize,Integer time);
		/**
		 * 新建
		 * @return
		 */
		Boolean create();
		/**
		 * 个数
		 * @param time
		 * @return
		 */
		Integer getUserHotCount(Integer time);
		/**
		 * 个人活跃度
		 * @param userId
		 * @param time
		 * @return
		 */
		Integer getUserHotByUser(Long userId,Integer time);
		
		/**
		 * 个人排名
		 * @param userId
		 * @param time
		 * @return
		 */
		Integer getUserHotRank(Long userId,Integer time);
}
