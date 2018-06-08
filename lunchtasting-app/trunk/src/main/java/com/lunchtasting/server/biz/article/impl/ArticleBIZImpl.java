package com.lunchtasting.server.biz.article.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.article.ArticleBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.article.ArticleDAO;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.po.lt.Article;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.Utils;
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
	public Integer getArticleCount(Integer type) {
		return articleDAO.getArticleCount(type);
	}
	
	@Override
	public Integer getArticleCountV2_0(Long categoryId) {
		return articleDAO.getArticleCountV2_0(categoryId);
	}

	@Override
	public List queryArticleList(Integer type,Integer page, Integer pageSize) {
		List list = articleDAO.queryArticleList(type,page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL1+"w/1080/h/460");
				
				/**
				 * 随机数，做颜色标签用
				 */
				map.put("random", CommonHelper.getRandomColor(map.get("biz_id").toString()));
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	}
	

	@Override
	public List queryArticleListV2_0(Long categoryId, Integer page,
			Integer pageSize) {
		List list = articleDAO.queryArticleListV2_0(categoryId,page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL1+"w/750/h/460");
				
				/**
				 * 随机数，做颜色标签用
				 */
				map.put("random", CommonHelper.getRandomColor(map.get("biz_id").toString()));
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	}

	@Override
	public Integer getDarenArticleCount() {
		return articleDAO.getDarenArticleCount();
	}

	@Override
	public List queryDarenArticleList(Integer page, Integer pageSize) {
		List list = articleDAO.queryDarenArticleList(page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL0+"w/640/h/460");
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	}

	@Override
	public Map getArticleDetail(Long articleId,Long userId) {
		Map map = articleDAO.getArticleDetail(articleId,userId);
		if(map != null){
			
			/**
			 * 图片
			 */
			map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
					+ QiNiuStorageHelper.MODEL1+"w/750/h/540");
			

			/**
			 * 判断是否收藏
			 */
			if(ValidatorHelper.isMapNotEmpty(map.get("is_collect"))){
				map.put("is_collect", 1);
			}else{
				map.put("is_collect", 0);
			}
		}
		return map;
	}

	@Override
	public List queryArticleCategoryList() {
		return articleDAO.queryArticleCategoryList();
	}

	@Override
	public String getArticleCategoryStr() {
		return articleDAO.getArticleCategoryStr();
	}


}
