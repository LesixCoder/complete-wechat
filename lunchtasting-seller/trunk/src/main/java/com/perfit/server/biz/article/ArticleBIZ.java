package com.perfit.server.biz.article;

import java.util.HashMap;

import com.lunchtasting.server.po.lt.Article;

public interface ArticleBIZ{
	HashMap queryArticle(HashMap map);
	Long addArticle(Article article);
	Long updateArticle(Article article);
	Article queryArticleById(String id);
	Long deleteArticle(HashMap map);
}
