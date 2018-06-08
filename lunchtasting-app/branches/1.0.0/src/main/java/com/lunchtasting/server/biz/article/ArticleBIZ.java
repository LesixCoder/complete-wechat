package com.lunchtasting.server.biz.article;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.po.lt.Article;

public interface ArticleBIZ {
	
	Article getById(Long id);

	/**
	 * 获得文章数量
	 * @return
	 */
	Integer getArticleCount();
	
	/**
	 * 获得文章列表
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryArticleList(Integer page,Integer pageSize);
	
	/**
	 * 获得达人文章总数
	 * @return
	 */
	Integer getDarenArticleCount();
	
	/**
	 * 获得达人专访文章
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryDarenArticleList(Integer page,Integer pageSize);
	
	/**
	 * 文章详情
	 * @param articleId
	 * @param userId
	 * @return
	 */
	Map getArticleDetail(Long articleId,Long userId);
}
