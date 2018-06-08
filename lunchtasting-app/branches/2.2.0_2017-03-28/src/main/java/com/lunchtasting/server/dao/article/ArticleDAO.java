package com.lunchtasting.server.dao.article;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Article;

public interface ArticleDAO extends GenericDAO<Article> {

	/**
	 * 获得文章数量
	 * @param type
	 * @return
	 */
	Integer getArticleCount(Integer type);
	
	/**
	 * 获得文章数量
	 * @param categoryId
	 * @return
	 */
	Integer getArticleCountV2_0(Long categoryId);
	
	/**
	 * 获得文章列表
	 * @param type
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryArticleList(Integer type,Integer page,Integer pageSize);
	
	/**
	 * 获得文章列表
	 * @param categoryId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryArticleListV2_0(Long categoryId,Integer page,Integer pageSize);
	
	/**
	 * 获得达人文章总数
	 * @return
	 */
	Integer getDarenArticleCount();
	
	/**
	 * 获得达人专访文章
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryDarenArticleList(Integer page,Integer pageSize);
	
	/**
	 * 文章详情
	 * @param articleId
	 * @param userId
	 * @return
	 */
	Map getArticleDetail(Long articleId,Long userId);
	
	/**
	 * 查询文章类目列表
	 * @return
	 */
	List queryArticleCategoryList();
	
	/**
	 * 获得所有文章类别字符串（","号分隔）
	 * @return
	 */
	String getArticleCategoryStr();
}
