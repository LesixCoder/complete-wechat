package com.lunchtasting.server.dao.match.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.match.MatchGoodsDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.MatchGoods;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class MatchGoodsDAOImpl extends GenericDAOSupport<Long,MatchGoods> implements MatchGoodsDAO{

	@Override
	protected Class<?> getObjectClass() {
		return MatchGoods.class;
	}

	@Override
	protected String getTableName() {
		return "match_goods";
	}

	@Override
	public List queryMatchTicketGoodsList(Long ticketId) {
		if(ticketId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("ticketId", ticketId);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryMatchTicketGoodsList", map);
	}

	@Override
	public List queryUserSelectGoodsList(Long ticketId,String goodsStr) {
		if(ticketId == null || ValidatorHelper.isEmpty(goodsStr)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("ticketId", ticketId);
		map.put("goodsStr", goodsStr);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserSelectGoodsList", map);
	}

	@Override
	public Double getUserSelectGoodsPrice(String goodsStr) {
		if(ValidatorHelper.isEmpty(goodsStr)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("goodsStr", goodsStr);
		return (Double)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserSelectGoodsPrice", map);	
	}

}
