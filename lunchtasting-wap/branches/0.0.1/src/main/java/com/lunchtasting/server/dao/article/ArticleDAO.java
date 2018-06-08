package com.lunchtasting.server.dao.article;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Article;

public interface ArticleDAO extends GenericDAO<Article>{
	/**
	 * 查询文章列表
	 * @param map
	 * @return
	 */
	List queryArticleList(HashMap map);
	/**
	 * 根据Id查询文章
	 * @param map
	 * @return
	 */
	Article queryArticleById(HashMap map);
	
}
