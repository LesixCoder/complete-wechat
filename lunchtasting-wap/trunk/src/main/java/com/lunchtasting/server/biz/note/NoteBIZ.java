package com.lunchtasting.server.biz.note;

import java.text.ParseException;
import java.util.Map;

public interface NoteBIZ {
	/**
	 * 查询帖子详细
	 * @param noteId
	 * @param userId
	 * @return
	 */
	public Map getNoteById(Long noteId)throws ParseException;
	
	/**
	 * 增加runinfo
	 * @param id
	 * @return
	 */
	public boolean addRunInfo(Long id);
}
