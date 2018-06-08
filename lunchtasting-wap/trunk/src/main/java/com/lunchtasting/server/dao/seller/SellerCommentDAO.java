package com.lunchtasting.server.dao.seller;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.SellerComment;

public interface SellerCommentDAO extends GenericDAO<SellerComment>{
	/**
	 * 查询前2条评论
	 * @param map
	 * @return
	 */
	List getShareCommentByCourseTop2(HashMap map);
}
