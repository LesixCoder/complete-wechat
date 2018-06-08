package com.lunchtasting.server.dao.note;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Note;

public interface NoteDAO extends GenericDAO<Note>{
	/**
	 * 获取note列表
	 * @param pageSize
	 * @param page
	 * @return
	 */
	List queryNoteList(Long userId,Integer pageSize,Integer page);
	
	/**
	 * 获取note列表topic
	 * @param pageSize
	 * @param page
	 * @return
	 */
	List queryNoteListByTopic(Long userId,Long topicId,Integer pageSize,Integer page);
	
	/**
	 * 查询note个数
	 * @return
	 */
	Integer getNoteCount();
	
	/**
	 * 根据topicId查询个数
	 * @param topicId
	 * @return
	 */
	Integer getNoteByTopicCount(Long topicId);
	
	
	/**
	 * 获得用户的帖子总数
	 * @param userId
	 * @return
	 */
	public Integer getUserNoteCount(Long userId);
	
	/**
	 * 获得用户的帖子列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List queryUserNoteList(Long userId,Integer page, Integer pageSize);
	
	/**
	 * 获得用户喜好过的帖子总数
	 * @param userId
	 * @return
	 */
	public Integer getUserLikeNoteCount(Long userId);
	
	/**
	 * 获得用户喜好过的帖子列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List queryUserLikeNoteList(Long userId,Integer page, Integer pageSize);
	/**
	 * 根据id查询note
	 * @param id
	 * @return
	 */
	public Map getNoteById(Long noteId,Long userId);
	/**
	 * 关注好友动态
	 * @param userId
	 * @return
	 */
	public List queryUserFriendNoteList(Long userId,Integer pageSize,Integer page);
	/**
	 * 关注好友动态个数
	 * @return
	 */
	public Integer getUserFriendNoteCount(Long userId);
	/**
	 * 修改状态
	 * @param flag
	 * @param noteId
	 * @return
	 */
	public Integer updateNote(Integer flag,Long noteId);
}
