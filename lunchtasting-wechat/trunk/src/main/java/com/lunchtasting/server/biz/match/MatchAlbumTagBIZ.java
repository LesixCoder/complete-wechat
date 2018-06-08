package com.lunchtasting.server.biz.match;

import java.util.List;

import com.lunchtasting.server.po.lt.MatchAlbumTag;

public interface MatchAlbumTagBIZ {
	
	
	MatchAlbumTag getById(Long id);
	
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
