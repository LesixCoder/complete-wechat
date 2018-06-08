package com.lunchtasting.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.AdminArticleDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Article;
@Repository
public class AdminArticleDaoImpl extends GenericDAOSupport<Long,Article> implements AdminArticleDao{

	public List<Article> queryArticleList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryArticleList", map);
	}

	public int queryArticleListCount(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getArticleCount",map);
	}

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

	public Long addArticle(Article article) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create", article);
	}

	public Long updateArticle(Article article) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".update", article);
	}

	public Article queryArticleById(String id) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("id", id);
		return (Article) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryArticleById",map);
	}

	public int deleteArticle(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update(getNamespace() + ".deleteArticle", map);
	}

	@Override
	public int topArticle(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update(getNamespace() + ".topArticle",map);
	}

	@Override
	public List queryArticleNotLimit() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryArticleNotLimit");
	}

}
