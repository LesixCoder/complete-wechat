package com.lunchtasting.server.dao;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Article;

public interface AdminArticleDao extends GenericDAO<Article>{
	List<Article> queryArticleList(HashMap map);
	int queryArticleListCount(HashMap map);
	Long addArticle(Article article);
	Long updateArticle(Article article);
	Article queryArticleById(String id);
	int deleteArticle(HashMap map);
	int topArticle(HashMap map);
	List queryArticleNotLimit();
}
