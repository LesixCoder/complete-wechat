package com.lunchtasting.server.dao.note;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.NoteComment;

public interface NoteCommentDAO extends GenericDAO<NoteComment> {
	/**
	 * 查询评论
	 * @param pageSize
	 * @param page
	 * @param noteId
	 * @return
	 */
	List queryNoteCommentList(Integer pageSize, Integer page,Long noteId);
	/**
	 * 查询评论总个数
	 * @param noteId
	 * @return
	 */
	Integer getNoteCommenCount(Long noteId);
}
