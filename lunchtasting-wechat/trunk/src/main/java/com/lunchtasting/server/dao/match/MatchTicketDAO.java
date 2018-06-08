package com.lunchtasting.server.dao.match;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.MatchTicket;

public interface MatchTicketDAO extends GenericDAO<MatchTicket> {

	/**
	 * 查询赛事下的票务信息
	 * @param matchId
	 * @param ticketType
	 * @return
	 */
	List queryMatchTicketList(Long matchId,Integer ticketType);
	
	/**
	 * 创建赛事报名
	 * @return
	 */
	Integer createMatchTicketSignup(Map map);
	
	/**
	 * 创建赛事报名详细信息
	 * @return
	 */
	Integer createMatchTicketSignupInfo(Map map);
}
