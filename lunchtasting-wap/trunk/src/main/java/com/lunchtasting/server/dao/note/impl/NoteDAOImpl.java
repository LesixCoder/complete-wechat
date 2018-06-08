package com.lunchtasting.server.dao.note.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.note.NoteDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Note;
@Repository
public class NoteDAOImpl extends GenericDAOSupport<Long,Note>  implements NoteDAO{

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
	public Map getNoteById(Long noteId) {
		// TODO Auto-generated method stub
		if(noteId==null){
			return null;
		}
		Map map = new HashMap();
		map.put("noteId", noteId);
		return  (Map) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getNoteById", map);
	}

	@Override
	public Integer addRunInfo(Long id) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("id", id);
		return (Integer) getSqlMapClientTemplate().update(getNamespace() + ".addRunInfo", map);
	}

}
