package com.lunchtasting.server.biz.seller;

import java.util.Map;

public interface SellerBIZ {

	/**
	 * 获得文章的分享详情
	 * @param sellerId
	 * @return
	 */
	Map getShareDetail(Long sellerId);
}
