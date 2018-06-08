package com.lunchtasting.server.biz;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.Article;

public interface AdminArticleBIZ{
	HashMap queryArticle(HashMap map);
	Long addArticle(Article article);
	Long updateArticle(Article article);
	Article queryArticleById(String id);
	Long deleteArticle(HashMap map);
	int topArticle(HashMap map);
	List queryArticleNotLimit();
}
