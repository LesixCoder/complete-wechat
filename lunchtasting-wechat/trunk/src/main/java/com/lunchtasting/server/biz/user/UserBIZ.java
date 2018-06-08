package com.lunchtasting.server.biz.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.po.lt.User;


public interface UserBIZ {
	
	User getById(Long userId);
	
	/**
	 * 微信登录
	 * @param atObject
	 * @param userObject
	 * @param userId
	 * @param inviteUserId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	Map loginWeChat(JSONObject atObject,JSONObject userObject,Long userId,HttpServletRequest request) throws Exception;
	
	
	/**
	 * 修改用户个人信息
	 * @param userId
	 * @param name
	 * @param feeling
	 * @param signature
	 * @param hobby
	 * @param tags
	 * @return
	 */
	Boolean updateUserInfo(Long userId,String name,String feeling,String signature,String hobby,String tags);
	
	/**
	 * 创建用户建议
	 * @param userId
	 * @param content
	 * @return
	 */
	void createSuggest(Long userId,String content);
	
	/**
	 * 获得用户详情(自己)
	 * @param userId
	 * @return
	 */
	Map getUserDetail(Long userId);
	
	/**
	 * 获得编辑时用户信息
	 * @param userId
	 * @return
	 */
	Map getEditInfo(Long userId);
	
	/**
	 * 获得某个用户主页的访客列表
	 * @param desUserId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryUserVisitorList(Long desUserId,Integer page,Integer pageSize);
	
	/**
	 * 查询用户留言列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryUserCommentList(Long userId,Integer page,Integer pageSize);
	
	/**
	 * 查询用户留言总数
	 * @param userId
	 * @return
	 */
	Integer getUserCommentCount(Long userId);
	
	/**
	 * 创建一个留言回复
	 * @param userId
	 * @param srcUserId
	 * @param content
	 * @param parentId
	 * @param parentSrcUserId
	 * @return
	 */
	Long createUserComment(Long userId,Long srcUserId,String content
			,Long parentId,Long parentSrcUserId);
	
	/**
	 * 获得用户赛事信息
	 * @param userId
	 * @param matchId
	 * @return
	 */
	Map getMatchUser(Long userId,Long matchId);
	
	/**
	 * 修改用户图片
	 * @param userId
	 * @param imgUrl
	 */
	void updateImgUrl(Long userId,String imgUrl);
	
	/**
	 * 修改用户是被谁邀请进来的
	 * @param userId
	 * @param inviteUserId
	 */
	void updateInviteUserId(Long userId,Long inviteUserId);
}
