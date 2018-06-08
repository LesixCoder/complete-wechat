package com.lunchtasting.server.dao.article.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.article.ArticleDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Article;

@Repository
public class ArticleDAOImpl extends GenericDAOSupport<Long,Article> implements ArticleDAO{

	@Override
	protected Class<?> getObjectClass() {
		return Article.class;
	}

	@Override
	protected String getTableName() {
		return "article";
	}

	@Override
	public List queryArticleList(HashMap map) {
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryArticleList", map);
	}

	@Override
	public Article queryArticleById(HashMap map) {
		return (Article)getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryArticleById", map);
	}

	@Override
	public Map getShareDetail(Long articleId) {
		if(articleId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("articleId", articleId);
        return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getShareDetail", map);
	}

}
