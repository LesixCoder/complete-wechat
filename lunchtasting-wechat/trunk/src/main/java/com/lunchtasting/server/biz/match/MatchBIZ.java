package com.lunchtasting.server.biz.match;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.lunchtasting.server.po.lt.Match;

public interface MatchBIZ {
	
	/**
	 * 得到对象
	 * @param id
	 * @return
	 */
	Match getById(Long id);
	
	/**
	 * 查询详细
	 * @param matchId
	 * @param userId
	 * @return
	 */
	Map getMatchDetail(Long matchId ,Long userId)throws ParseException;
	
//	/**
//	 * 获得推荐用户
//	 * @param userId
//	 * @param matchId
//	 * @param sex
//	 * @param size
//	 * @return
//	 */
//	List getRecommendUser(Long userId,Long matchId,int sex,int size);
	
	/**
	 * 邀请用户组队
	 * @param srcUserId
	 * @param desUserId
	 * @param matchId
	 * @param content
	 * @return
	 */
	Boolean inviteGroupUser(Long srcUserId,Long desUserId,Long matchId,String content) throws Exception;
	
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
	Boolean checkIsInvite(Long srcUserId,Long desUserId,Long matchId);
	
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
