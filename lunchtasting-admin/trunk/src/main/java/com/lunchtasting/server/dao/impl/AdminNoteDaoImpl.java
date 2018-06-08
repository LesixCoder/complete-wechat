package com.lunchtasting.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.AdminNoteDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Note;
@Repository
public class AdminNoteDaoImpl extends GenericDAOSupport<Long,Note> implements AdminNoteDao{

	@Override
	public List queryNoteList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryNoteList",map);
	}

	@Override
	public int getNoteCount(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() +".queryNoteCount",map);
	}

	@Override
	public int updateNote(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().update(getNamespace() + ".updateNote",map);
	}

	@Override
	public Long addNote(Note note) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create",note);
	}

	@Override
	public Long updateNote(Note note) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".update",note);
	}

	@Override
	public Note queryNoteById(String id) {
		Map map = new HashMap();
		map.put("id", id);
		return (Note) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryNoteById",map);
	}

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return Note.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "note";
	}

	@Override
	public int updateNoteGood(String id) {
		HashMap map = new HashMap();
		map.put("id", id);
		return getSqlMapClientTemplate().update(getNamespace() + ".updateNoteGood",map);
	}

	@Override
	public Long saveComment(Long id, Long userId, Long noteId, Long noteUserId,
			String content) {
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("userId", userId);
		map.put("noteId", noteId);
		map.put("noteUserId", noteUserId);
		map.put("content", content);
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".insertComment",map);
	}

	@Override
	public int updateFabulous(Long id,int fabulous) {
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("fabulous", fabulous);
		return getSqlMapClientTemplate().update(getNamespace() + ".updateFabulous",map);
	}

	@Override
	public int updateNoteByUserId(Long userId) {
		HashMap map = new HashMap();
		map.put("userId", userId);
		return (Integer) getSqlMapClientTemplate().update(getNamespace() + ".updateNoteByUserId",map);
	}

}
