package com.lunchtasting.server.dao.article;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Article;

public interface ArticleDAO extends GenericDAO<Article> {

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
}
