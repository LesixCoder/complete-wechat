package com.lunchtasting.server.dao.match;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.MatchAlbumTag;

public interface MatchAlbumTagDAO extends GenericDAO<MatchAlbumTag> {

	/**
	 * 查询相册标签列表
	 * @param albumId
	 * @param userId
	 * @return
	 */
	List queryAlbumTagList(Long albumId,Long userId);
	
	/**
	 * 查询相册标签总数
	 * @param albumId
	 * @param userId
	 * @return
	 */
	Integer getAlbumTagCount(Long albumId,Long userId);
}
