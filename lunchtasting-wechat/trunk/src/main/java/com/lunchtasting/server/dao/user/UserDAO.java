package com.lunchtasting.server.dao.user;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.User;

public interface UserDAO extends GenericDAO<User> {

	/**
	 * 判断用户是否存在
	 * @param userId
	 * @return
	 */
	Long getByUserId(Long userId);
	
	/**
	 * 通过号码查询用户id(可判断是否注册)
	 * @param phone
	 * @return
	 */
	Long getUserIdByPhone(String phone);
	
	/**
	 * 修改用户登录时间
	 * @param userId
	 * @return
	 */
	Integer updateLoginTime(Long userId);
	
	/**
	 * 创建用户建议
	 * @param id
	 * @param userId
	 * @param content
	 */
	void createSuggest(Long id,Long userId,String content);
	
	/**
	 * 修改用户信息
	 * @param userId
	 * @param name
	 * @param feeling
	 * @param signature
	 * @param hobby
	 * @param tags
	 * @return
	 */
	Integer updateUserInfo(Long userId,String name,String feeling,String signature,String hobby,String tags);
	
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
	 * 创建用户留言
	 * @param id
	 * @param userId
	 * @param srcUserId
	 * @param content
	 * @param parentId
	 * @param parentSrcUserId
	 */
	void createUserComment(Long id,Long userId, Long srcUserId,
			String content, Long parentId, Long parentSrcUserId);
	
	/**
	 * 创建手机设备信息（app登录需要，其他信息默认为空）
	 * @param id
	 * @param userId
	 */
	void createUserDevice(Long id,Long userId);
	
	/**
	 * 获得用户赛事信息
	 * @param userId
	 * @param matchId
	 * @return
	 */
	Map getMatchUser(Long userId,Long matchId);
	
	/**
	 * 修改用户生日
	 * @param userId
	 * @param birth
	 */
	void updateUser(Long userId,String birth,String name);
	
	/**
	 * 修改用户图片
	 * @param userId
	 * @param imgUrl
	 */
	void updateImgUrl(Long userId,String imgUrl);
	
	/**
	 * 增加用户存款数
	 * @param userId
	 * @param money
	 * @return
	 */
	Integer updateAddDeposit(Long userId,Double money);
	
	/**
	 * 减少用户存款数
	 * @param userId
	 * @param money
	 * @return
	 */
	Integer updateReduceDeposit(Long userId,Double money);
	
	/**
	 * 修改用户是被谁邀请进来的
	 * @param userId
	 * @param inviteUserId
	 */
	void updateInviteUserId(Long userId,Long inviteUserId);
}
