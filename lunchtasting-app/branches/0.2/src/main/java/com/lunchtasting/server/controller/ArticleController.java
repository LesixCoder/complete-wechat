package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.article.ArticleBIZ;
import com.lunchtasting.server.biz.article.ArticleCommentBIZ;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.po.lt.Activity;
import com.lunchtasting.server.po.lt.Article;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private ArticleBIZ articleBIZ;
	@Autowired
	private ArticleCommentBIZ commentBIZ;
	
	/**
	 * 文章列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		
		try {
			//long userId = EncryptAuth.getUserId(request);
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", articleBIZ.queryArticleList((pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			dataMap.put("total_page",Utils.getTotalPage(articleBIZ.getArticleCount(),VariableConfig.PAGE_SIZE));
			dataMap.put("current_page", pPage);	
			
		} catch (Exception e) {
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, null, request);
		}
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, null, request);
	}
	
	/**
	 * 文章详情页面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/detail")
	@ResponseBody
	public Object detail(HttpServletRequest request) throws Exception {
		return null;
	}
	
	/**
	 * 添加获得评论/回复
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/comment/add")
	@ResponseBody
	public Object commentAdd(HttpServletRequest request) throws Exception {
		String aId = request.getParameter("article_id");
		String content = request.getParameter("content");
		String imgArray = request.getParameter("img_array");
		String parentId = request.getParameter("parent_id");
		String parentUserId = request.getParameter("parent_user_id");
		
		if(!ValidatorHelper.isNumber(aId) || ValidatorHelper.isEmpty(content) 
				|| ValidatorHelper.isEmpty(imgArray)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			long userId = EncryptAuth.getUserId(request);
			long articleId = Long.parseLong(aId);
			
			Article article = articleBIZ.getById(articleId);
			if(article == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
			}
			
			/**
			 * 添加评论
			 */
			Boolean result = commentBIZ.createComment(articleId,userId, content, imgArray, parentId, parentUserId);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, request);
			}
		} catch (Exception e) {
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
	
	/**
	 * 活动评论列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/comment/list")
	@ResponseBody
	public Object commentList(HttpServletRequest request) throws Exception {
		String aId = request.getParameter("article_id");
		String page = request.getParameter("page");
		if(!ValidatorHelper.isNumber(aId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		
		try {
			long userId = EncryptAuth.getUserId(request);
			long articleId = Long.parseLong(aId);
			
			Article article = articleBIZ.getById(articleId);
			if(article == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
			}
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", commentBIZ.queryCommentList(articleId, (pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			dataMap.put("total_page",Utils.getTotalPage(commentBIZ.getCommentCount(articleId),VariableConfig.PAGE_SIZE));
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE,dataMap, request);
		} catch (Exception e) {
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
}
