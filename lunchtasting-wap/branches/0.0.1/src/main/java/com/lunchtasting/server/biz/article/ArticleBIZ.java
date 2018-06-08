package com.lunchtasting.server.biz.article;

import com.lunchtasting.server.po.lt.Article;

public interface ArticleBIZ {
	/**
	 * 根据Id查询文章列表
	 * @param id
	 * @return
	 */
	Article queryArticleById(Long id);
}
