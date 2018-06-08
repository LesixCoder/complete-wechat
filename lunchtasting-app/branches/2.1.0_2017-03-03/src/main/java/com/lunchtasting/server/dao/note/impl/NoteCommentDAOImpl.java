package com.lunchtasting.server.dao.note.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.note.NoteCommentDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.NoteComment;
@Repository
public class NoteCommentDAOImpl extends GenericDAOSupport<Long,NoteComment> implements NoteCommentDAO{

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return NoteComment.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "note_comment";
	}

	@Override
	public List queryNoteCommentList(Integer pageSize, Integer page, Long noteId) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("pageSize",pageSize);
		map.put("page",page);
		map.put("noteId",noteId);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryNoteCommentList", map);
	}

	@Override
	public Integer getNoteCommenCount(Long noteId) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("noteId",noteId);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getNoteCommenCount", map);
	}

}
