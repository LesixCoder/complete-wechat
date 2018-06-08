package com.lunchtasting.server.biz.note;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.lunchtasting.server.po.lt.Note;

public interface NoteBIZ {
	
	
	Note getById(Long id);
	
	/**
	 * 查询note列表
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List queryNoteList(Long userId,Long topicId,Integer page, Integer pageSize,Integer friend)throws ParseException;
	/**
	 * note个数
	 * @return
	 */
	public Integer getNoteCount(Long topicId);
	/**
	 * 新增喜爱
	 * @param userId
	 * @param noteId
	 * @return
	 */
	public Boolean createLike(Long userId,Long noteId);
	
	/**
	 * 取消喜爱
	 * @param userId
	 * @param noteId
	 * @return
	 */
	public Boolean deleteLike(Long userId,Long noteId);
	
	/**
	 * 发布一张帖子
	 * @param userId
	 * @param type
	 * @param content
	 * @param imgUrl
	 * @param videoUrl
	 * @return
	 */
	public Boolean publish(Long userId,Integer type,String content,String imgUrl,String videoUrl,Long topicId ,Integer imgHeight, Integer imgWidth);
	
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
	 * 查询帖子详细
	 * @param noteId
	 * @param userId
	 * @return
	 */
	public Map getNoteById(Long noteId,Long userId)throws ParseException;
	
	/**
	 * 关注好友帖子的个数
	 */
	public Integer getUserFriendNoteCount(Long userId);
/*	*//**
	 * 验证帖子是否存在 且 未删除
	 * @param id
	 * @return
	 *//*
	public Boolean checkNote(Long id);*/
	/**
	 * 删除帖子
	 * @param noteId
	 * @return
	 */
	public boolean removeNote(Long noteId);
	
	/**
	 * 精品帖子
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List queryGoodNoteList(Long userId, Integer page, Integer pageSize)throws ParseException;
	
	/**
	 * 精品帖子个数
	 * @return
	 */
	public Integer getGoodNoteCoint();
}
