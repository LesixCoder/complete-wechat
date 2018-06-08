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
	
}
