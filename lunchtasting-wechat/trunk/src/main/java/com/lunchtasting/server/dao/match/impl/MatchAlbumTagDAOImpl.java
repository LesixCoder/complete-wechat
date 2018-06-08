package com.lunchtasting.server.dao.match.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.match.MatchAlbumTagDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.MatchAlbumTag;

@Repository
public class MatchAlbumTagDAOImpl extends GenericDAOSupport<Long,MatchAlbumTag> 
	implements MatchAlbumTagDAO {

	@Override
	protected Class<?> getObjectClass() {
		return MatchAlbumTag.class;
	}

	@Override
	protected String getTableName() {
		return "match_album_tag";
	}

	@Override
	public List queryAlbumTagList(Long albumId, Long userId) {
		if(albumId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("albumId", albumId);
		map.put("userId", userId);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryAlbumTagList", map);
	}

	@Override
	public Integer getAlbumTagCount(Long albumId, Long userId) {
		if(albumId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("albumId", albumId);
		map.put("userId", userId);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getAlbumTagCount", map);
	}

}
