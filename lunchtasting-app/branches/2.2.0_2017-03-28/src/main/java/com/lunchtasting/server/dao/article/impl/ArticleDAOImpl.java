package com.lunchtasting.server.dao.article.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.article.ArticleDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Article;

@Repository
public class ArticleDAOImpl extends GenericDAOSupport<Long, Article> implements ArticleDAO {

	@Override
	protected Class<?> getObjectClass() {
		return Article.class;
	}

	@Override
	protected String getTableName() {
		return "article";
	}

	@Override
	public Integer getArticleCount(Integer type) {
		Map map = new HashMap();
		map.put("type", type);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getArticleCount",map);	
	}
	
	@Override
	public Integer getArticleCountV2_0(Long categoryId) {
		Map map = new HashMap();
		map.put("categoryId", categoryId);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getArticleCountV2_0",map);	
	}


	@Override
	public List queryArticleList(Integer type,Integer page, Integer pageSize) {
		Map map = new HashMap();
		map.put("type", type);
		map.put("page", page);
		map.put("pageSize", pageSize);
        return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryArticleList", map);
	}
	
	@Override
	public List queryArticleListV2_0(Long categoryId, Integer page,
			Integer pageSize) {
		Map map = new HashMap();
		map.put("categoryId", categoryId);
		map.put("page", page);
		map.put("pageSize", pageSize);
        return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryArticleListV2_0", map);
	}

	@Override
	public Integer getDarenArticleCount() {
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getDarenArticleCount");	
	}

	@Override
	public List queryDarenArticleList(Integer page, Integer pageSize) {
		Map map = new HashMap();
		map.put("page", page);
		map.put("pageSize", pageSize);
        return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryDarenArticleList", map);
	}

	@Override
	public Map getArticleDetail(Long articleId,Long userId) {
		if(articleId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("articleId", articleId);
		map.put("userId", userId);
        return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getArticleDetail", map);
	}
	
	@Override
	public List queryArticleCategoryList() {
        return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryArticleCategoryList");
	}

	@Override
	public String getArticleCategoryStr() {
        return (String)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getArticleCategoryStr");
	}

}
