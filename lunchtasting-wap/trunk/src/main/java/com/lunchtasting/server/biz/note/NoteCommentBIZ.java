package com.lunchtasting.server.biz.note;

import java.text.ParseException;
import java.util.List;

public interface NoteCommentBIZ {
	/**
	 * 查询评论列表
	 * @param pageSize
	 * @param page
	 * @param noteId
	 * @return
	 */
	public List queryNoteCommentList(Integer pageSize, Integer page, Long noteId)throws ParseException;
	/**
	 * 查询评论数目
	 * @param noteId
	 * @return
	 */
	public Integer getNoteCommenCount(Long noteId);
	
}
