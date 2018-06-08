package com.lunchtasting.server.biz.match;

import java.util.List;

import com.lunchtasting.server.po.lt.MatchTicket;

public interface MatchTicketBIZ {

	MatchTicket getById(Long id);
	
	/**
	 * 查询赛事下的票务信息
	 * @param matchId
	 * @param ticketType
	 * @return
	 */
	List queryMatchTicketList(Long matchId,Integer ticketType);
	
}
