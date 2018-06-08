package com.lunchtasting.server.dao.article.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.article.ArticleDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Article;

@Repository
public class ArticleDAOImpl extends GenericDAOSupport<Long,Article> implements ArticleDAO{

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return Article.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "article";
	}

	@Override
	public List queryArticleList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryArticleList", map);
	}

	@Override
	public Article queryArticleById(HashMap map) {
		// TODO Auto-generated method stub
		return (Article)getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryArticleById", map);
	}

}
