package com.lunchtasting.server.dao.match;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Match;

public interface MatchDAO extends GenericDAO<Match> {
	
	/**
	 * 查询赛事类别列表
	 * @return
	 */
	List queryMatchCategoryList();
	
	/**
	 * 查询赛事列表
	 * @param categoryId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryMatchList(Long categoryId,Integer page, Integer pageSize);
	
	/**
	 * 查询赛事个数
	 * @param categoryId
	 * @return
	 */
	Integer getMatchCount(Long categoryId);
	
	/**
	 * 查看赛事详情页
	 * @param matchId
	 * @param userId
	 * @return
	 */
	Map getMatchDetail(Long matchId,Long userId);
	
	/**
	 * 获得当天推荐用户
	 * @param userId
	 * @param matchId
	 * @return
	 */
	List queryMatchRecommendUserList(Long userId,Long matchId);
	
	/**
	 * 创建赛事用户推荐表
	 * @param id
	 * @param userId
	 * @param recommendUserId
	 * @param matchId
	 */
	void createMatchRecommendUser(Long id,Long userId,Long recommendUserId
			,Long matchId);
	
	/**
	 * 创建赛事邀请
	 * @param id
	 * @param srcUserId
	 * @param desUserId
	 * @param matchId
	 * @param content
	 */
	void createMatchInvite(Long id,Long srcUserId,Long desUserId
				,Long matchId,String content);
	
	/**
	 * 查询邀请列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryMatchInviteList(Long userId,Integer page,Integer pageSize);
	
	/**
	 * 获取用户对某用户当天是否邀请过
	 * @param srcUserId
	 * @param desUserId
	 * @param matchId
	 * @return
	 */
	Integer getIsInvite(Long srcUserId,Long desUserId,Long matchId);
	
	/**
	 * 获得赛事邀请码
	 * @param code
	 * @return
	 */
	Map getMatchCodeByCode(String code);
	
	/**
	 * 查询咆哮狗成绩
	 * @param sex
	 * @return
	 */
	List queryPxgCjList(String sex);
	
	
}
