package com.lunchtasting.server.biz.article.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.article.ArticleBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.article.ArticleDAO;
import com.lunchtasting.server.po.lt.Article;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class ArticleBIZImpl implements ArticleBIZ {
	
	@Autowired
	private ArticleDAO articleDAO;
	
	@Override
	public Article getById(Long id) {
		return articleDAO.getById(id);
	}

	@Override
	public Integer getArticleCount() {
		return articleDAO.getArticleCount();
	}

	@Override
	public List queryArticleList(Integer page, Integer pageSize) {
		List list = articleDAO.queryArticleList(page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url"));
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	}


}
