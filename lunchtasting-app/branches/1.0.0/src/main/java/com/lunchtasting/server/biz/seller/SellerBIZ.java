package com.lunchtasting.server.biz.seller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lunchtasting.server.po.lt.Seller;


public interface SellerBIZ {

	/**
	 * 通过账号或邮箱和密码登录
	 * @param account
	 * @param pwd
	 * @return
	 */
	Seller getByAccountAndPwd(String account,String pwd);
	
	/**
	 * 商家用户登录
	 * @param sellerId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	Map login(Long sellerId,HttpServletRequest request) throws Exception;
	
	/**
	 * 查询商家总数
	 * @param areaId
	 * @return
	 */
	Integer getSellerCount(Long areaId);
	
	/**
	 * 查询商家列表
	 * @param areaId
	 * @param longitude
	 * @param latitude
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List querySellerList(Long areaId,Double longitude,Double latitude, Integer page,Integer pageSize);
	
	/**
	 * 商家详情页
	 * @param sellerId
	 * @param userId
	 * @return
	 */
	Map getSellerDetail(Long sellerId,Long userId);
}
