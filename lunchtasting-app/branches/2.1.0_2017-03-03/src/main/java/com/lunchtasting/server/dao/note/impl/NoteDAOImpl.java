package com.lunchtasting.server.dao.note.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.note.NoteDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Note;
@Repository
public class NoteDAOImpl extends GenericDAOSupport<Long,Note> implements NoteDAO{
	@Override
	protected Class<?> getObjectClass() {
		return Note.class;
	}

	@Override
	protected String getTableName() {
		return "note";
	}

	@Override
	public List queryNoteList(Long userId,Integer pageSize, Integer page) {
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryNoteList", map);
	}

	@Override
	public Integer getNoteCount() {
		Map map = new HashMap();
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getNoteCount", map);
	}

	@Override
	public Integer getNoteByTopicCount(Long topicId) {
		Map map = new HashMap();
		map.put("topicId",topicId);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getNoteByTopicCount", map);
	}

	@Override
	public List queryNoteListByTopic(Long userId,Long topicId, Integer pageSize,
			Integer page) {
		Map map = new HashMap();
		map.put("page", page);
		map.put("pageSize", pageSize);
		map.put("topicId", topicId);
		map.put("userId", userId);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryNoteListByTopic", map);
	}

	@Override
	public Integer getUserNoteCount(Long userId) {
		if(userId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserNoteCount", map);
	}

	@Override
	public List queryUserNoteList(Long userId, Integer page, Integer pageSize) {
		if(userId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserNoteList", map);
	}

	@Override
	public Integer getUserLikeNoteCount(Long userId) {
		if(userId == null){
			return 0;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserLikeNoteCount", map);	
	}

	@Override
	public List queryUserLikeNoteList(Long userId, Integer page,
			Integer pageSize) {
		if(userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserLikeNoteList", map);
	}

	@Override
	public Map getNoteById(Long noteId,Long userId) {
		Map map = new HashMap();
		map.put("noteId", noteId);
		map.put("userId", userId);
		return  (Map) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getNoteById", map);
	}

	@Override
	public List queryUserFriendNoteList(Long userId,Integer pageSize,Integer page) {
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("pageSize", pageSize);
		map.put("page", page);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserFriendNoteList", map);
	}

	@Override
	public Integer getUserFriendNoteCount(Long userId) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("userId", userId);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserFriendNoteCount", map);
	}

	@Override
	public Integer updateNote(Integer flag, Long noteId) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("flag", flag);
		map.put("id", noteId);
		return  getSqlMapClientTemplate().update(getNamespace() + ".updateNote", map);
	}
}
