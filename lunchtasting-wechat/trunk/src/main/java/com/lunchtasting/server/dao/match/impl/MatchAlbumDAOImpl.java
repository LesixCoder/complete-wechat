package com.lunchtasting.server.dao.match.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.match.MatchAlbumDAO;
import com.lunchtasting.server.dao.match.MatchDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.MatchAlbum;

@Repository
public class MatchAlbumDAOImpl extends GenericDAOSupport<Long,MatchAlbum> implements MatchAlbumDAO {

	@Override
	protected Class<?> getObjectClass() {
		return MatchAlbum.class;
	}

	@Override
	protected String getTableName() {
		return "match_album";
	}

	@Override
	public List queryAlbumList(String name,Integer page,Integer pageSize) {
		Map map = new HashMap();
		map.put("name", name);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryAlbumList",map);
	}
	
	@Override
	public Integer getAlbumCount(String name) {
		Map map = new HashMap();
		map.put("name", name);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getAlbumCount",map);
	}

	@Override
	public List queryAlbumImageList(Long albumId, String code, Integer page,
			Integer pageSize) {
		if(albumId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("albumId", albumId);
		map.put("code", code);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryAlbumImageList", map);
	}

	@Override
	public Integer getAlbumImageCount(Long albumId, String code) {
		if(albumId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("albumId", albumId);
		map.put("code", code);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getAlbumImageCount", map);
	}
	
	@Override
	public List queryAlbumTagImageList(Long albumId, Long tagId, Integer page,
			Integer pageSize) {
		if(albumId == null || tagId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("albumId", albumId);
		map.put("tagId", tagId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryAlbumTagImageList", map);	
	}

	@Override
	public Integer getAlbumTagImageCount(Long albumId, Long tagId) {
		if(albumId == null ||tagId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("albumId", albumId);
		map.put("tagId", tagId);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getAlbumTagImageCount", map);
	}

	@Override
	public void createAlbumImage(Long id,Long albumId, Long matchId, String code,
			String imgUrl,Integer width,Integer height) {
		if(id == null || albumId == null || matchId == null || imgUrl == null){
			return;
		}
		Map map = new HashMap();
		map.put("id", id);
		map.put("albumId", albumId);
		map.put("matchId", matchId);
		map.put("code", code);
		map.put("imgUrl", imgUrl);
		map.put("width", width);
		map.put("height", height);
		getSqlMapClientTemplate().insert(getNamespace() + ".createAlbumImage", map);
	}

	@Override
	public Map getAlbumImageMap(Long imageId) {
		if(imageId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("imageId", imageId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getAlbumImageMap", map);
	}

}
