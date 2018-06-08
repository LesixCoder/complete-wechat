package com.lunchtasting.server.dao.seller;

import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Seller;

public interface SellerDAO extends GenericDAO<Seller> {

	/**
	 * 获得文章的分享详情
	 * @param sellerId
	 * @return
	 */
	Map getShareDetail(Long sellerId);
}
