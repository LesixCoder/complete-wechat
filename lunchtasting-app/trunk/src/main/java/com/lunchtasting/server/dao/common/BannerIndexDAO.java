package com.lunchtasting.server.dao.common;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.BannerIndex;

public interface BannerIndexDAO extends GenericDAO<BannerIndex>  {

	/**
	 * 查询首页推荐banner图
	 * @param pageSize
	 * @return
	 */
	List queryBannerIndexList(Integer pageSize);
}
