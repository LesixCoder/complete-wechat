package com.lunchtasting.server.dao.match.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.match.MatchTicketDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.MatchTicket;

@Repository
public class MatchTicketDAOImpl extends GenericDAOSupport<Long,MatchTicket> 
implements MatchTicketDAO {

	@Override
	protected Class<?> getObjectClass() {
		return MatchTicket.class;
	}

	@Override
	protected String getTableName() {
		return "match_ticket";
	}

	@Override
	public List queryMatchTicketList(Long matchId, Integer ticketType) {
		if(matchId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("matchId", matchId);
		map.put("ticketType", ticketType);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryMatchTicketList", map);	
	}

	@Override
	public Integer createMatchTicketSignup(Map map) {
		if(map == null){
			return null;
		}
		return (Integer)getSqlMapClientTemplate().insert(getNamespace() + ".createMatchTicketSignup", map);	
	}

	@Override
	public Integer createMatchTicketSignupInfo(Map map) {
		if(map == null){
			return null;
		}
		return (Integer)getSqlMapClientTemplate().insert(getNamespace() + ".createMatchTicketSignupInfo", map);		}

}
