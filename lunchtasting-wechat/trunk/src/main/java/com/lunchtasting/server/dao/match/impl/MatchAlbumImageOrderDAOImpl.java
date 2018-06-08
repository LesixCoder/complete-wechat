package com.lunchtasting.server.dao.match.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.match.MatchAlbumImageOrderDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.MatchAlbumImageOrder;

@Repository
public class MatchAlbumImageOrderDAOImpl extends GenericDAOSupport<Long,MatchAlbumImageOrder> implements MatchAlbumImageOrderDAO {

	@Override
	protected Class<?> getObjectClass() {
		return MatchAlbumImageOrder.class;
	}

	@Override
	protected String getTableName() {
		return "match_album_image_order";
	}
	
	@Override
	public Integer updateOrderPay(Long orderId) {
		if(orderId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("orderId", orderId);
		return (Integer)getSqlMapClientTemplate().update(getNamespace() + ".updateOrderPay", map);
	}

}
