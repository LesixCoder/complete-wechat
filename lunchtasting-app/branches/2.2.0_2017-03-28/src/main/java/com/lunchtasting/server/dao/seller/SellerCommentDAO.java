package com.lunchtasting.server.dao.seller;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.SellerComment;

public interface SellerCommentDAO extends GenericDAO<SellerComment> {

	/**
	 * 查询商家评论总数
	 * @param sellerId
	 * @return
	 */
	Integer getSellerCommentCount(Long sellerId);
	
	/**
	 * 查询商家评论列表
	 * @param sellerId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List querySellerCommentList(Long sellerId,Integer page,Integer pageSize);
}
