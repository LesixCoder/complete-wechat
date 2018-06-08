package com.lunchtasting.server.dao;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Album;

public interface AdminAlbumDao extends GenericDAO<Album>{
	List queryAlbumList(HashMap map);
	int queryAlbumCount(HashMap map);
	int updateAlbum(HashMap map);
	Long saveAlbum(Album album);
	Long updateAlbum(Album album);
	Album queryAlbumById(String id);
	List queryAlbumNotLimit(Long id);
	List querySelect();
}
