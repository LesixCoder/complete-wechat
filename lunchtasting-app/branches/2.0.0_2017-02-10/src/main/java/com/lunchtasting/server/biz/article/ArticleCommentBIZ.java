package com.lunchtasting.server.biz.article;

import java.util.List;

public interface ArticleCommentBIZ {

	/**
	 * 创建文章评论
	 * @param articleId
	 * @param userId
	 * @param content
	 * @param imgArray
	 * @param parentId
	 * @param parentUserId
	 * @return
	 */
	Boolean createComment(Long articleId,Long userId,String content,String imgArray
			,String parentId,String parentUserId);
	
	/**
	 * 文章评论总数
	 * @param articleId
	 * @return
	 */
	Integer getCommentCount(Long articleId);
	
	/**
	 * 文章评论列表
	 * @param articleId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryCommentList(Long articleId,Integer page,Integer pageSize);
}
