package com.lunchtasting.server.biz.article.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.article.ArticleBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.article.ArticleDAO;
import com.lunchtasting.server.po.lt.Article;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.ValidatorHelper;

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
	@Override
	public Map getShareDetail(Long articleId) {
		Map map = articleDAO.getShareDetail(articleId);
		if(map != null){
			
			/**
			 * 图片
			 */
			map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
					+ QiNiuStorageHelper.MODEL0+"w/640/h/540");
			
		}
		return map;
	}
}
