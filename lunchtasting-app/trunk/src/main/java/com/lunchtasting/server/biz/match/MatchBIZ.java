package com.lunchtasting.server.biz.match;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.po.lt.Match;

public interface MatchBIZ {
	
	Match getById(Long id);
	
	/**
	 * 查询赛事类别列表
	 * @param userId
	 * @return
	 */
	List queryMatchCategoryList(Long userId);
	
	/**
	 * 查询赛事列表
	 * @param categoryId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryMatchList(Long categoryId,Integer page, Integer pageSize) throws Exception;
	
	/**
	 * 查询赛事 
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
	Map getMatchDetail(Long matchId,Long userId) throws Exception;
	
}
