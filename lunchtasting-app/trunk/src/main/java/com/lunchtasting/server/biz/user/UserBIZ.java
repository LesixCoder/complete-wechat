package com.lunchtasting.server.biz.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.po.lt.User;


public interface UserBIZ {
	
	User getById(Long userId);
	
	/**
	 * 判断号码是否已经注册
	 * @param phone
	 * @return
	 */
	Boolean checkRegisterPhone(String phone);
	
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
	 * 普通用户注册
	 * @param phone
	 * @param password
	 * @param deviceToken
	 * @param smsId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	Map registerUser(String phone,String password,String deviceToken,Long smsId,HttpServletRequest request) throws Exception;
	
	/**
	 * 普通用户登录
	 * @param userId
	 * @param deviceToken
	 * @param request
	 * @return
	 * @throws Exception
	 */
	Map login(Long userId,String deviceToken,HttpServletRequest request) throws Exception;
	
	/**
	 * 微信用户登录，是否登录(否 注册本地用户  是 更新微信授权信息和客户端设备信息)
	 * @param atObject
	 * @param userObject
	 * @param userId
	 * @param deviceToken
	 * @param request
	 * @return
	 * @throws Exception
	 */
	Map loginWeChat(JSONObject atObject,JSONObject userObject,Long userId,String deviceToken,HttpServletRequest request) throws Exception;
	
	/**
	 * 通过号码查询用户id(可判断是否注册)
	 * @param phone
	 * @return
	 */
	Long getUserIdByPhone(String phone);
	
	/**
	 * 修改密码
	 * @param userId
	 * @param password
	 * @param codeId
	 * @return
	 */
	Boolean updatePwd(Long userId,String password,Long codeId) throws Exception;
	
	/**
	 * 修改用户个人信息
	 * @param userId
	 * @param name
	 * @param imgUrl
	 * @param sex
	 * @param birth
	 * @param profession
	 * @param feeling
	 * @param signature
	 * @return
	 */
	Boolean updateUserInfo(Long userId,String name,String imgUrl
				,String sex,String birth,String profession,String feeling,String signature);
	
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
	 * 获得初始化请求的用户对象
	 * @param userId
	 * @return
	 */
	Map getUserInit(Long userId);
}
