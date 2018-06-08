package com.lunchtasting.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.AdminAlbumDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Album;
@Repository
public class AdminAlbumDaoImpl extends GenericDAOSupport<Long,Album> implements AdminAlbumDao{

	@Override
	public List queryAlbumList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryAlbumList",map);
	}

	@Override
	public int queryAlbumCount(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryAlbumCount",map);
	}

	@Override
	public int updateAlbum(HashMap map) {
		return getSqlMapClientTemplate().update(getNamespace() + ".updateAlbum",map);
	}

	@Override
	public Long saveAlbum(Album album) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create",album);
	}

	@Override
	public Long updateAlbum(Album album) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".update",album);
	}

	@Override
	public Album queryAlbumById(String id) {
		Map map = new HashMap();
		map.put("id", id);
		return (Album) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryAlbumById",map);
	}

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return Album.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "album";
	}

	@Override
	public List queryAlbumNotLimit(Long id) {
		HashMap map = new HashMap();
		map.put("bizId", id);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryAlbumNotLimit",map);
	}

	@Override
	public List querySelect() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".querySelect");
	}

}
