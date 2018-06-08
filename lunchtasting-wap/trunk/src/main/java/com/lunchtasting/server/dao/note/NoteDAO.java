package com.lunchtasting.server.dao.note;

import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Note;

public interface NoteDAO extends GenericDAO<Note>{
	/**
	 * 根据id查询note
	 * @param id
	 * @return
	 */
	public Map getNoteById(Long noteId);
	/**
	 * 增加运营数据run_info
	 * @param id
	 * @return
	 */
	public Integer addRunInfo(Long id);
}
