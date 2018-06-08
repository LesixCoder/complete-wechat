package com.lunchtasting.server.biz.match;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.po.lt.MatchAlbum;

public interface MatchAlbumBIZ {
	
	MatchAlbum getById(Long id);
	
	/**
	 * 获取所有赛事相册列表
	 * @param name
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryAlbumList(String name,Integer page,Integer pageSize);
	
	/**
	 * 获得赛事相册总数
	 * @param name
	 * @return
	 */
	Integer getAlbumCount(String name);
	
	
	/**
	 * 获得相册图片列表
	 * @param albumId
	 * @param tagId
	 * @param code
	 * @param isLogo
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryAlbumImageList(Long albumId,Long tagId,String code,Integer isLogo,Integer page,Integer pageSize);
	
	/**
	 * 获得相册图片总数
	 * @param albumId
	 * @param tagId
	 * @param code
	 * @return
	 */
	Integer getAlbumImageCount(Long albumId,Long tagId,String code);
	
	/**
	 * 创建相册图片
	 * @param albumId
	 * @param matchId
	 * @param code
	 * @param imgUrl
	 * @param width
	 * @param height
	 */
	void createAlbumImage(Long albumId,Long matchId,String code,String imgUrl,Integer width,Integer height);
	
	/**
	 * 获得图片详情
	 * @param imageId
	 * @return
	 */
	Map getAlbumImageMap(Long imageId);
}
