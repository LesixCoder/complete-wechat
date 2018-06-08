package com.lunchtasting.server.biz.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.AdminArticleBIZ;
import com.lunchtasting.server.dao.AdminArticleDao;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.po.lt.Article;
@Service
public class AdminArticleBIZImpl implements AdminArticleBIZ{
	@Autowired
	private AdminArticleDao adminArticleDao;
	public HashMap queryArticle(HashMap map) {
		HashMap result = new HashMap();
	    List<Article> articleList;
	    int totalCount;
    	 try{
    		 articleList = adminArticleDao.queryArticleList(map);
    		 for(int i = 0;i<articleList.size();i++){
    			 articleList.get(i).setNewId(articleList.get(i).getId().toString());
    		 }
    		 totalCount = adminArticleDao.queryArticleListCount(map);
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
		return adminArticleDao.addArticle(article);
	}
	public Long updateArticle(Article article) {
		// TODO Auto-generated method stub
		return adminArticleDao.updateArticle(article);
	}
	public Article queryArticleById(String id) {
		// TODO Auto-generated method stub
		return adminArticleDao.queryArticleById(id);
	}
	public Long deleteArticle(HashMap map) {
		// TODO Auto-generated method stub
		return (long) adminArticleDao.deleteArticle(map);
	}
	@Override
	public int topArticle(HashMap map) {
		// TODO Auto-generated method stub
		return adminArticleDao.topArticle(map);
	}
	@Override
	public List queryArticleNotLimit() {
		// TODO Auto-generated method stub
		return adminArticleDao.queryArticleNotLimit();
	}

}
