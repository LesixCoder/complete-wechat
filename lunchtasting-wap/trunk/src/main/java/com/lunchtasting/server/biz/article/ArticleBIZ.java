package com.lunchtasting.server.biz.article;

import java.util.Map;

import com.lunchtasting.server.po.lt.Article;

public interface ArticleBIZ {
	/**
	 * 根据Id查询文章列表
	 * @param id
	 * @return
	 */
	Article queryArticleById(Long id);
	
	/**
	 * 获得文章的分享详情
	 * @param activityId
	 * @return
	 */
	Map getShareDetail(Long articleId);
}
