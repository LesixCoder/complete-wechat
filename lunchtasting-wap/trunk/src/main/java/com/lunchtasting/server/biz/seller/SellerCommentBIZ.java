package com.lunchtasting.server.biz.seller;

import java.util.HashMap;
import java.util.List;

public interface SellerCommentBIZ{
	/**
	 * 根据条件查询前2条评论
	 * @param map
	 * @return
	 */
	List getShareCommentByCourseTop2(HashMap map);
}
