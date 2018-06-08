package com.lunchtasting.server.dao.seller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.seller.SellerCommentDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.SellerComment;

@Repository
public class SellerCommentDAOImpl extends GenericDAOSupport<Long, SellerComment> 
	implements SellerCommentDAO {

	@Override
	protected Class<?> getObjectClass() {
		return SellerComment.class;
	}

	@Override
	protected String getTableName() {
		return "seller_comment";
	}

	@Override
	public Integer getSellerCommentCount(Long sellerId) {
		if(sellerId == null){
			return 0;
		}
		
		Map map = new HashMap();
		map.put("sellerId",sellerId);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getSellerCommentCount", map);
	}

	@Override
	public List querySellerCommentList(Long sellerId, Integer page,
			Integer pageSize) {
		Map map = new HashMap();
		map.put("sellerId", sellerId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".querySellerCommentList", map);
	}

}
