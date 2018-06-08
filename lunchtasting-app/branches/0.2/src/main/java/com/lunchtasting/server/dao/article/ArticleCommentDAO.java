package com.lunchtasting.server.dao.article;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.ArticleComment;

public interface ArticleCommentDAO extends GenericDAO<ArticleComment> {

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
