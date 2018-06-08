package com.lunchtasting.server.dao.note;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.NoteLike;

public interface NoteLikeDAO extends GenericDAO<NoteLike>{
	/**
	 * 查找Like
	 * @param userId
	 * @param noteId
	 * @return
	 */
	NoteLike getNoteLike(Long userId,Long noteId);
	/**
	 * 删除喜爱
	 * @param id
	 */
	void deleteLike(Long id);
}
