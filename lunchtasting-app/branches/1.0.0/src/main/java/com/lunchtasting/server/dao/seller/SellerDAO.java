package com.lunchtasting.server.dao.seller;

import java.util.List;
import java.util.Map;
import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.Seller;

public interface SellerDAO extends GenericDAO<Seller>  {


	/**
	 * 通过账号或邮箱和密码登录
	 * @param account
	 * @param pwd
	 * @return
	 */
	Seller getByAccountAndPwd(String account,String pwd);
	
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
