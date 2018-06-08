package com.lunchtasting.server.biz.match.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.match.MatchAlbumTagBIZ;
import com.lunchtasting.server.dao.match.MatchAlbumTagDAO;
import com.lunchtasting.server.po.lt.MatchAlbumTag;

@Service
public class MatchAlbumTagBIZImpl implements MatchAlbumTagBIZ{

	@Autowired
	private MatchAlbumTagDAO tagDAO;
	
	@Override
	public MatchAlbumTag getById(Long id) {
		return tagDAO.getById(id);
	}

	@Override
	public List queryAlbumTagList(Long albumId, Long userId) {
		return tagDAO.queryAlbumTagList(albumId, userId);
	}

	@Override
	public Integer getAlbumTagCount(Long albumId, Long userId) {
		return tagDAO.getAlbumTagCount(albumId, userId);
	}

}
