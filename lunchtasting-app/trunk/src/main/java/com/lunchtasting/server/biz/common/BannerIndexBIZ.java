package com.lunchtasting.server.biz.common;

import java.util.List;

public interface BannerIndexBIZ {
	
	/**
	 * 查询首页推荐banner图
	 * @param pageSize
	 * @return
	 */
	List queryBannerIndexList(Integer pageSize);
}
