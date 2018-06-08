package com.lunchtasting.server.dao.note.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.note.NoteLikeDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.NoteLike;

@Repository
public class NoteLikeDAOImpl extends GenericDAOSupport<Long,NoteLike> implements NoteLikeDAO {

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return NoteLike.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "note_like";
	}

	@Override
	public NoteLike getNoteLike(Long userId, Long noteId) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("noteId",noteId);
		return (NoteLike) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getNoteLike", map);
	}

	@Override
	public void deleteLike(Long id) {
		Map map = new HashMap();
		map.put("id", id);
		getSqlMapClientTemplate().delete(getNamespace() + ".deleteLike", map);
	}

}
