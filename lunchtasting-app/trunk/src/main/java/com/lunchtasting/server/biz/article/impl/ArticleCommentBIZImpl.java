package com.lunchtasting.server.biz.article.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.article.ArticleCommentBIZ;
import com.lunchtasting.server.dao.article.ArticleCommentDAO;
import com.lunchtasting.server.po.lt.ArticleComment;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class ArticleCommentBIZImpl implements ArticleCommentBIZ {
	
	@Autowired
	private ArticleCommentDAO commentDAO;

	@Override
	public Boolean createComment(Long articleId,Long userId, String content, String imgArray,
			String parentId,String parentUserId) {
		
		ArticleComment comment = new ArticleComment();
		comment.setArticleId(articleId);
		comment.setUserId(userId);
		comment.setContent(content);
		comment.setImgUrl(imgArray);
		if(ValidatorHelper.isNotEmpty(parentId)){
			comment.setParentId(Long.parseLong(parentId));
		}
		if(ValidatorHelper.isNotEmpty(parentUserId)){
			comment.setParentUserId(Long.parseLong(parentId));
		}

		commentDAO.create(comment);
		if(ValidatorHelper.isNotEmpty(comment.getId())){
			return true;
		}
		return false;
	}

	@Override
	public Integer getCommentCount(Long articleId) {
		return commentDAO.getCommentCount(articleId);
	}

	@Override
	public List queryCommentList(Long articleId, Integer page, Integer pageSize) {
		List list = commentDAO.queryCommentList(articleId, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
//				if(ValidatorHelper.isMapNotEmpty(map.get("img_url"))){
//					String imgUrl = map.get("img_url").toString();
//					StringBuffer sb = new StringBuffer();
//					for(String img : imgUrl.split(",")){
//						sb.append(img).append(",");
//					}
//					if(sb.substring(sb.length()-1, sb.length()).equals(",")){
//						sb.delete(sb.length()-1, sb.length());
//					}		
//					map.put("img_url", sb.toString());
//				}
				
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	}

}
