package com.lunchtasting.server.dao.seller.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.seller.SellerCommentDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.SellerComment;
@Repository
public class SellerCommentDAOImpl extends GenericDAOSupport<Long,SellerComment> implements SellerCommentDAO{
	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return SellerComment.class;
	}
	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "seller_comment";
	}
	@Override
	public List getShareCommentByCourseTop2(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".getShareCommentByCourseTop2", map);
	}
}
