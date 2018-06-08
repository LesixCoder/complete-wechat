package com.lunchtasting.server.biz.seller;

import java.util.List;

public interface SellerCommentBIZ {
	
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
	
	/**
	 * 创建一个评论
	 * @param sellerId
	 * @param userId
	 * @param orderId
	 * @param score
	 * @param content
	 * @param imgArray
	 * @return
	 */
	Boolean createComment(Long sellerId,Long userId,Long orderId
			,Integer score,String content,String imgArray) throws Exception;
}
