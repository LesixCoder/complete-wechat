package com.lunchtasting.server.biz;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.Album;

public interface AdminAlbumBIZ {
	HashMap queryAlbumList(HashMap map);
	int updateAlbum(HashMap map);
	Long saveAlbum(Album album);
	Long updateAlbum(Album album);
	Album queryAlbumById(String id);
	List queryAlbumNotLimit(Long id);
	List querySelect();
}
