package com.lunchtasting.server.biz;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.Note;

public interface AdminNoteBIZ {
	HashMap queryNoteList(HashMap map);
	int updateNote(HashMap map);
	Long addNote(Note note);
	Long updateNote(Note note);
	Note queryNoteById(String id);
	int updateNoteGood(String id);
	Long saveComment(Long id,Long userId,Long noteId,Long noteUserId,String content);
	int updateFabulous(Long id,int fabulous);
	int updateNoteByUserId(Long userId);
}
