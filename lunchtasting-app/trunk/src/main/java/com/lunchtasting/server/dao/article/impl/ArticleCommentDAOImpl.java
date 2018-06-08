package com.lunchtasting.server.dao.article.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.article.ArticleCommentDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.ArticleComment;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class ArticleCommentDAOImpl extends GenericDAOSupport<Long, ArticleComment> implements ArticleCommentDAO {

	@Override
	protected Class<?> getObjectClass() {
		return ArticleComment.class;
	}

	@Override
	protected String getTableName() {
		return "article_comment";
	}
	
	@Override
	public Integer getCommentCount(Long articleId) {
		if(ValidatorHelper.isEmpty(articleId)){
			return null;
		}
		Map map = new HashMap();
		map.put("articleId", articleId);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getCommentCount",map);	
	}

	@Override
	public List queryCommentList(Long articleId, Integer page, Integer pageSize) {
		if(ValidatorHelper.isEmpty(articleId)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("articleId", articleId);
		map.put("page", page);
		map.put("pageSize", pageSize);
        return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryCommentList", map);
	}

}
