package com.lunchtasting.server.dao.user;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.User;

public interface UserDAO extends GenericDAO<User> {

	/**
	 * 通过号码密码获得用户
	 * @param phone
	 * @param pwd
	 * @return
	 */
	User getByPhoneAndPwd(String phone,String pwd);
	
	/**
	 * 通过手机或邮箱和密码登录
	 * @param account
	 * @param pwd
	 * @return
	 */
	User getByAccountAndPwd(String account,String pwd);
	
	/**
	 * 通过号码查询用户id(可判断是否注册)
	 * @param phone
	 * @return
	 */
	Long getUserIdByPhone(String phone);
	
	/**
	 * 修改用户密码
	 * @param userId
	 * @param pwd
	 * @return
	 */
	Integer updatePwd(Long userId,String pwd);
	
	/**
	 * 修改用户登录时间
	 * @param userId
	 * @return
	 */
	Integer updateLoginTime(Long userId);
	
	/**
	 * 创建用户建议
	 * @param userId
	 * @param content
	 * @return
	 */
	void createSuggest(Long userId,String content);
}
