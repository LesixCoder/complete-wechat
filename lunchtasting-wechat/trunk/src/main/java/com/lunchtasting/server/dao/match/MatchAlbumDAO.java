package com.lunchtasting.server.dao.match;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.MatchAlbum;

public interface MatchAlbumDAO extends GenericDAO<MatchAlbum>  {

	
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
	 * @param code
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryAlbumImageList(Long albumId,String code,Integer page,Integer pageSize);
	
	/**
	 * 获得相册图片总数
	 * @param albumId
	 * @param code
	 * @return
	 */
	Integer getAlbumImageCount(Long albumId,String code);
	
	/**
	 * 获得相册标签图片列表
	 * @param albumId
	 * @param code
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryAlbumTagImageList(Long albumId,Long tagId,Integer page,Integer pageSize);
	
	/**
	 * 获得相册标签图片总数
	 * @param albumId
	 * @param tagId
	 * @return
	 */
	Integer getAlbumTagImageCount(Long albumId,Long tagId);
	
	/**
	 * 创建相册图片
	 * @param id
	 * @param albumId
	 * @param matchId
	 * @param code
	 * @param imgUrl
	 * @param width
	 * @param height
	 */
	void createAlbumImage(Long id,Long albumId,Long matchId,String code
			,String imgUrl,Integer width,Integer height);
	
	/**
	 * 获得图片详情
	 * @param imageId
	 * @return
	 */
	Map getAlbumImageMap(Long imageId);
}
