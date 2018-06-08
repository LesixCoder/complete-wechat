package com.lunchtasting.server.dao.user;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.UserScore;

public interface UserScoreDAO extends GenericDAO<UserScore> {

	/**
	 * 获得用户积分获取方式的总数
	 * @param userId
	 * @return
	 */
	Integer getUserScoreCount(Long userId);
	
	/**
	 * 获得用户积分列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryUserScoreList(Long userId,Integer page,Integer pageSize);
	
	/**
	 * 获得用户积分数量的总数
	 * @param userId
	 * @return
	 */
	Integer getUserScoreTotal(Long userId);
	
	/**
	 * 榜单
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryUserScoreRank(Integer page,Integer pageSize);
	
	/**
	 * 个人排名
	 * @param userId
	 * @return
	 */
	Integer getUserScoreRank(Long userId);
	
	/**
	 * 排行总个数
	 * @return
	 */
	Integer getScoreRankCount();
	
	/**
	 * 查询是否线下得到名次
	 * @return
	 */
	Integer getUserScoreByPhone(String phone);
	
	/**
	 * 注册时，补全user_id
	 * @param phone
	 * @param userId
	 * @return
	 */
	Integer completion(String phone,Long userId);
}
