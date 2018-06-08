package com.perfit.server.biz.article.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lunchtasting.server.po.lt.Article;
import com.perfit.server.biz.article.ArticleBIZ;
import com.perfit.server.dao.article.ArticleDao;
import com.perfit.server.helper.PageBean;
@Service
public class ArticleBIZImpl implements ArticleBIZ{
	@Autowired
	private ArticleDao articleDao;
	public HashMap queryArticle(HashMap map) {
		HashMap result = new HashMap();
	    List<Article> articleList;
	    int totalCount;
    	 try{
    		 articleList = articleDao.queryArticleList(map);
    		 totalCount = articleDao.queryArticleListCount(map);
    	 }catch(Exception e){
			 e.printStackTrace();
			 result.put("result","1");
		     result.put("descript","查询失败");
		     result.put("totalCount",0);
		     result.put("page",new PageBean(null, Integer.parseInt(map.get("curPage").toString()), 0, Integer.parseInt(map.get("pageSize").toString())));
		     return result;
    	 }
    	 if(articleList != null && articleList.size() > 0){
    		 result.put("result","0");
		     result.put("descript","查询成功");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(articleList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }else{
    		 result.put("result","0");
		     result.put("descript","没有更多");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(articleList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }
		return result;
	}
	public Long addArticle(Article article) {
		// TODO Auto-generated method stub
		return articleDao.addArticle(article);
	}
	public Long updateArticle(Article article) {
		// TODO Auto-generated method stub
		return articleDao.updateArticle(article);
	}
	public Article queryArticleById(String id) {
		// TODO Auto-generated method stub
		return articleDao.queryArticleById(id);
	}
	public Long deleteArticle(HashMap map) {
		// TODO Auto-generated method stub
		return (long) articleDao.deleteArticle(map);
	}
}
