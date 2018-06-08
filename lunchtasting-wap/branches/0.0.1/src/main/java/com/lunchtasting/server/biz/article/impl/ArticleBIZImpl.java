package com.lunchtasting.server.biz.article.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.article.ArticleBIZ;
import com.lunchtasting.server.dao.article.ArticleDAO;
import com.lunchtasting.server.po.lt.Article;

@Service
public class ArticleBIZImpl implements ArticleBIZ{
	@Autowired
	private ArticleDAO articleDAO;
	@Override
	public Article queryArticleById(Long id) {
		if(id==null || id ==0){
			return null;
		}
		HashMap map = new HashMap();
		map.put("id", id);
		return articleDAO.queryArticleById(map);
	}
}
